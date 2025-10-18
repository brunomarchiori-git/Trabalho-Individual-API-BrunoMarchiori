package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.controller;

import java.util.List;

import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Artista;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/artistas")
public class ArtistasController {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Operation(summary = "Listar Artistas", description = "Retorna Todos Artistas")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Artistas encontrados com sucesso."),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @GetMapping
    public ResponseEntity<List<Artista>> listarTodos() {
        return ResponseEntity.ok(artistaRepository.findAll());
    }

    @Operation(summary = "Listar Artistas por ID", description = "Retorna Artista Especifico")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Artista encontrado com sucesso."),
    		@ApiResponse(responseCode = "404", description = "Artista nao encontrado para o ID fornecido."),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @GetMapping("/{id}")
    public ResponseEntity<Artista> buscarPorId(@PathVariable Long id) {
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artista com id " + id + " não encontrado"));
        return ResponseEntity.ok(artista);
    }

    @Operation(summary = "Cria Artistas", description = "Cria Artista")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Artista criado com sucesso."),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @PostMapping
    public ResponseEntity<Artista> criar(@Valid @RequestBody Artista artista) {
        return ResponseEntity.ok(artistaRepository.save(artista));
    }

    @Operation(summary = "Atualizar Artistas por ID", description = "Atualiza Artista Especifico")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Artista atualizado com sucesso."),
    		@ApiResponse(responseCode = "404", description = "Artista nao encontrado para o ID fornecido."),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @PutMapping("/{id}")
    public ResponseEntity<Artista> atualizar(@PathVariable Long id, @Valid @RequestBody Artista artistaAtualizado) {
        Artista artistaExistente = artistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artista com id " + id + " não encontrado"));

        artistaExistente.setNome(artistaAtualizado.getNome());
        artistaExistente.setNacionalidade(artistaAtualizado.getNacionalidade());

        return ResponseEntity.ok(artistaRepository.save(artistaExistente));
    }

    @Operation(summary = "Deleta Artistas por ID", description = "Deleta Artista Especifico")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Artista deletado com sucesso."),
    		@ApiResponse(responseCode = "404", description = "Artista nao encontrado para o ID fornecido."),
    		@ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artista com id " + id + " não encontrado"));

        artistaRepository.delete(artista);
        return ResponseEntity.noContent().build();
    }
}
