package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.controller;

import java.util.List;

import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Artista;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.service.ArtistaService;
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
@RequestMapping("/artistas")
public class ArtistasController {

    @Autowired
    private ArtistaService artistaService;

    @GetMapping
    public ResponseEntity<List<Artista>> listarTodos() {
        return ResponseEntity.ok(artistaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> buscarPorId(@PathVariable Long id) {
        Artista artista = artistaService.buscarPorId(id);
        return ResponseEntity.ok(artista);
    }

    @PostMapping
    public ResponseEntity<Artista> criar(@Valid @RequestBody Artista artista) {
        Artista novoArtista = artistaService.criar(artista);
        return ResponseEntity.ok(novoArtista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> atualizar(@PathVariable Long id, @Valid @RequestBody Artista artistaAtualizado) {
        Artista artista = artistaService.atualizar(id, artistaAtualizado);
        return ResponseEntity.ok(artista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        artistaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
