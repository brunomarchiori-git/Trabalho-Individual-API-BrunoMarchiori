package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.controller;

import java.util.List;

import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Usuario;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Listar Usuários", description = "Retorna todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @Operation(summary = "Listar Usuário por ID", description = "Retorna usuário específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para o ID fornecido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com id " + id + " não encontrado"));
        return ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Criar Usuário", description = "Cria um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @PostMapping
    public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario) {
        if (usuario.getPerfil() != null) {
            usuario.getPerfil().setUsuario(usuario);
        }
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @Operation(summary = "Atualizar Usuário por ID", description = "Atualiza usuário específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para o ID fornecido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com id " + id + " não encontrado"));

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setPerfil(usuarioAtualizado.getPerfil());
        usuarioExistente.setPlaylists(usuarioAtualizado.getPlaylists());

        return ResponseEntity.ok(usuarioRepository.save(usuarioExistente));
    }

    @Operation(summary = "Deletar Usuário por ID", description = "Deleta usuário específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para o ID fornecido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com id " + id + " não encontrado"));

        usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}
