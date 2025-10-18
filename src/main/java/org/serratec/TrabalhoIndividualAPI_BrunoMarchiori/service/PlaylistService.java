package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.service;

import java.util.List;
import java.util.stream.Collectors;

import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Musica;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Playlist;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Usuario;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.repository.MusicaRepository;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.repository.PlaylistRepository;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    public List<Playlist> listarTodos() {
        return playlistRepository.findAll();
    }

    public Playlist buscarPorId(Long id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist com id " + id + " não encontrada"));
    }

    @PostMapping("/playlists")
    public Playlist criarPlaylist(@RequestBody Playlist playlist) {
        
        Long usuarioId = playlist.getUsuario().getId();
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        playlist.setUsuario(usuario);

        
        if (playlist.getMusicas() != null) {
            List<Musica> musicas = playlist.getMusicas().stream()
                .map(m -> musicaRepository.findById(m.getId())
                    .orElseThrow(() -> new RuntimeException("Musica não encontrada")))
                .collect(Collectors.toList());
            playlist.setMusicas(musicas);
        }

        return playlistRepository.save(playlist);
    }


    public Playlist atualizar(Long id, Playlist playlistAtualizada) {
        Playlist playlistExistente = buscarPorId(id);

        if (playlistAtualizada.getNome() != null)
            playlistExistente.setNome(playlistAtualizada.getNome());

        if (playlistAtualizada.getMusicas() != null && !playlistAtualizada.getMusicas().isEmpty()) {
            List<Long> idsMusicas = playlistAtualizada.getMusicas()
                    .stream()
                    .map(Musica::getId)
                    .toList();

            playlistExistente.setMusicas(musicaRepository.findAllById(idsMusicas));
        }

        return playlistRepository.save(playlistExistente);
    }

    public void deletar(Long id) {
        Playlist playlistExistente = buscarPorId(id);
        playlistRepository.delete(playlistExistente);
    }
}
