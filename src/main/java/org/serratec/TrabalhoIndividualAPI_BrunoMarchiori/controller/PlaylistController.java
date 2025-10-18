package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Musica;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Playlist;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Usuario;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.repository.MusicaRepository;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.repository.PlaylistRepository;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    @Operation(summary = "Listar Playlists", description = "Retorna todas as playlists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Playlists encontradas com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @GetMapping
    public ResponseEntity<List<Playlist>> listarTodos() {
        return ResponseEntity.ok(playlistRepository.findAll());
    }

    @Operation(summary = "Listar Playlist por ID", description = "Retorna playlist específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Playlist encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Playlist não encontrada para o ID fornecido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> buscarPorId(@PathVariable Long id) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist com id " + id + " não encontrada"));
        return ResponseEntity.ok(playlist);
    }

    @Operation(summary = "Criar Playlist", description = "Cria uma nova playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Playlist criada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @PostMapping
    public ResponseEntity<Playlist> criar(@Valid @RequestBody Playlist playlist) {
        Long usuarioId = playlist.getUsuario().getId();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        playlist.setUsuario(usuario);

        if (playlist.getMusicas() != null) {
            List<Musica> musicas = playlist.getMusicas().stream()
                    .map(m -> musicaRepository.findById(m.getId())
                            .orElseThrow(() -> new RuntimeException("Música não encontrada")))
                    .collect(Collectors.toList());
            playlist.setMusicas(musicas);
        }

        return ResponseEntity.ok(playlistRepository.save(playlist));
    }

    @Operation(summary = "Atualizar Playlist por ID", description = "Atualiza playlist específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Playlist atualizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Playlist não encontrada para o ID fornecido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @PutMapping("/{id}")
    public ResponseEntity<Playlist> atualizar(@PathVariable Long id, @Valid @RequestBody Playlist playlistAtualizada) {
        Playlist playlistExistente = playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist com id " + id + " não encontrada"));

        if (playlistAtualizada.getNome() != null)
            playlistExistente.setNome(playlistAtualizada.getNome());

        if (playlistAtualizada.getMusicas() != null && !playlistAtualizada.getMusicas().isEmpty()) {
            List<Long> idsMusicas = playlistAtualizada.getMusicas().stream()
                    .map(Musica::getId)
                    .toList();
            playlistExistente.setMusicas(musicaRepository.findAllById(idsMusicas));
        }

        return ResponseEntity.ok(playlistRepository.save(playlistExistente));
    }

    @Operation(summary = "Deletar Playlist por ID", description = "Deleta playlist específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Playlist deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Playlist não encontrada para o ID fornecido."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist com id " + id + " não encontrada"));

        playlistRepository.delete(playlist);
        return ResponseEntity.noContent().build();
    }
}
