package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.controller;

import java.util.List;

import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Perfil;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.service.PerfilService;
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
@RequestMapping("/perfis")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;
	
	@GetMapping
	public ResponseEntity<List<Perfil>> listarTodos() {
		return ResponseEntity.ok(perfilService.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Perfil> buscarPorId(@PathVariable Long id) {
		Perfil perfil = perfilService.buscarPorId(id);
		return ResponseEntity.ok(perfil);
	}
	
	@PostMapping
	public ResponseEntity<Perfil> criar(@Valid @RequestBody Perfil perfil) {
		Perfil novoPerfil = perfilService.criar(perfil);
		return ResponseEntity.ok(novoPerfil);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Perfil> atualizar(@PathVariable Long id, @Valid @RequestBody Perfil perfilAtualizado) {
		Perfil perfil = perfilService.atualizar(id, perfilAtualizado);
		return ResponseEntity.ok(perfil);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		perfilService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
}
