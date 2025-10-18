package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.controller;

import java.util.List;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Musica;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/musicas")
public class MusicasController {

    @Autowired
    private MusicaRepository musicaRepository;

    @Operation(summary = "Listar Músicas", description = "Retorna todas as músicas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Músicas encontradas com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @GetMapping
    public ResponseEntity<List<Musica>> listarTodos() {
        return ResponseEntity.ok(musicaRepository.findAll());
    }

    @Operation(summary = "Listar Música por ID", description = "Retorna música específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Música encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Música não encontrada para o ID fornecido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @GetMapping("/{id}")
    public ResponseEntity<Musica> buscarPorId(@PathVariable Long id) {
        Musica musica = musicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Música com id " + id + " não encontrada"));
        return ResponseEntity.ok(musica);
    }

    @Operation(summary = "Criar Música", description = "Cria nova música")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Música criada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @PostMapping
    public ResponseEntity<Musica> criar(@Valid @RequestBody Musica musica) {
        return ResponseEntity.ok(musicaRepository.save(musica));
    }

    @Operation(summary = "Atualizar Música por ID", description = "Atualiza música específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Música atualizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Música não encontrada para o ID fornecido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @PutMapping("/{id}")
    public ResponseEntity<Musica> atualizar(@PathVariable Long id, @Valid @RequestBody Musica musicaAtualizada) {
        Musica musicaExistente = musicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Música com id " + id + " não encontrada"));

        musicaExistente.setTitulo(musicaAtualizada.getTitulo());
        musicaExistente.setSegundos(musicaAtualizada.getSegundos());
        musicaExistente.setGenero(musicaAtualizada.getGenero());
        musicaExistente.setArtistas(musicaAtualizada.getArtistas());

        return ResponseEntity.ok(musicaRepository.save(musicaExistente));
    }

    @Operation(summary = "Deletar Música por ID", description = "Deleta música específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Música deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Música não encontrada para o ID fornecido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Musica musica = musicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Música com id " + id + " não encontrada"));

        musicaRepository.delete(musica);
        return ResponseEntity.noContent().build();
    }
}
