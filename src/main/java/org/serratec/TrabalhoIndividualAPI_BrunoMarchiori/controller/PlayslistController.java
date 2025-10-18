package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.controller;

import java.util.List;

import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Playlist;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.service.PlaylistService;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/playlists")
public class PlayslistController {

	@Autowired
	private PlaylistService playlistService;
	
	@GetMapping
	public ResponseEntity<List<Playlist>> listarTodos() {
		return ResponseEntity.ok(playlistService.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Playlist> buscarPorId(@PathVariable Long id) {
		Playlist playlist = playlistService.buscarPorId(id);
		return ResponseEntity.ok(playlist);
	}
	
	@PostMapping
	public ResponseEntity<Playlist> criar(@Valid @RequestBody Playlist playlist) {
		Playlist novaPlaylist = playlistService.criarPlaylist(playlist);
		return ResponseEntity.ok(novaPlaylist);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Playlist> atualizar(@PathVariable Long id, @Valid @RequestBody Playlist playlistAtualizada) {
		Playlist playlist = playlistService.atualizar(id, playlistAtualizada);
		return ResponseEntity.ok(playlist);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		playlistService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
}
