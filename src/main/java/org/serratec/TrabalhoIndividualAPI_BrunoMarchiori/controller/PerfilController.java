package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.controller;

import java.util.List;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Perfil;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @Operation(summary = "Listar Perfis", description = "Retorna todos os perfis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfis encontrados com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @GetMapping
    public ResponseEntity<List<Perfil>> listarTodos() {
        return ResponseEntity.ok(perfilRepository.findAll());
    }

    @Operation(summary = "Listar Perfil por ID", description = "Retorna perfil específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Perfil não encontrado para o ID fornecido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @GetMapping("/{id}")
    public ResponseEntity<Perfil> buscarPorId(@PathVariable Long id) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil com id " + id + " não encontrado"));
        return ResponseEntity.ok(perfil);
    }

    @Operation(summary = "Criar Perfil", description = "Cria um novo perfil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil criado com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @PostMapping
    public ResponseEntity<Perfil> criar(@Valid @RequestBody Perfil perfil) {
        return ResponseEntity.ok(perfilRepository.save(perfil));
    }

    @Operation(summary = "Atualizar Perfil por ID", description = "Atualiza perfil específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Perfil não encontrado para o ID fornecido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @PutMapping("/{id}")
    public ResponseEntity<Perfil> atualizar(@PathVariable Long id, @Valid @RequestBody Perfil perfilAtualizado) {
        Perfil perfilExistente = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil com id " + id + " não encontrado"));

        perfilExistente.setTelefone(perfilAtualizado.getTelefone());
        perfilExistente.setDataNascimento(perfilAtualizado.getDataNascimento());
        perfilExistente.setUsuario(perfilAtualizado.getUsuario());

        return ResponseEntity.ok(perfilRepository.save(perfilExistente));
    }

    @Operation(summary = "Deletar Perfil por ID", description = "Deleta perfil específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Perfil não encontrado para o ID fornecido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil com id " + id + " não encontrado"));

        perfilRepository.delete(perfil);
        return ResponseEntity.noContent().build();
    }
}
