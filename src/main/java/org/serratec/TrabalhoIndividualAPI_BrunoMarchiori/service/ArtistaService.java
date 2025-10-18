package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.service;

import java.util.List;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Artista;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    public List<Artista> listarTodos() {
        return artistaRepository.findAll();
    }

    public Artista buscarPorId(Long id) {
        return artistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artista com id " + id + " não encontrado"));
    }

    public Artista criar(Artista artista) {
        return artistaRepository.save(artista);
    }

    public Artista atualizar(Long id, Artista artistaAtualizado) {
        Artista artistaExistente = buscarPorId(id);
        artistaExistente.setNome(artistaAtualizado.getNome());
        artistaExistente.setNacionalidade(artistaAtualizado.getNacionalidade());
        return artistaRepository.save(artistaExistente);
    }

    public void deletar(Long id) {
        Artista artistaExistente = buscarPorId(id);
        artistaRepository.delete(artistaExistente);
    }
}
