package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.controller;

import java.util.List;

import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Musica;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.service.MusicaService;
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
@RequestMapping("/musicas")
public class MusicasController {

	@Autowired
	private MusicaService musicaService;
	
	@GetMapping
	public ResponseEntity<List<Musica>> listarTodos() {
		return ResponseEntity.ok(musicaService.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Musica> buscarPorId(@PathVariable Long id) {
		Musica musica = musicaService.buscarPorId(id);
		return ResponseEntity.ok(musica);
	}
	
	@PostMapping
	public ResponseEntity<Musica> criar(@Valid @RequestBody Musica musica) {
		Musica novaMusica = musicaService.criar(musica);
		return ResponseEntity.ok(novaMusica);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Musica> atualizar(@PathVariable Long id, @Valid @RequestBody Musica musicaAtualizada) {
		Musica musica = musicaService.atualizar(id, musicaAtualizada);
		return ResponseEntity.ok(musica);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		musicaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
