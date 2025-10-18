package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.service;

import java.util.List;

import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Musica;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicaService {

	@Autowired
	private MusicaRepository musicaRepository;
	
	public List<Musica> listarTodos() {
		return musicaRepository.findAll();
	}
	
	public Musica buscarPorId(Long id) {
		return musicaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Música com id " + id + " não encontrada"));
	}
	
	public Musica criar(Musica musica) {
		return musicaRepository.save(musica);
	}
	
	public Musica atualizar(Long id, Musica musicaAtualizada) {
		Musica musicaExistente = buscarPorId(id);
		musicaExistente.setTitulo(musicaAtualizada.getTitulo());
		musicaExistente.setSegundos(musicaAtualizada.getSegundos());
		musicaExistente.setGenero(musicaAtualizada.getGenero());
		musicaExistente.setArtistas(musicaAtualizada.getArtistas());
		return musicaRepository.save(musicaExistente);
	}
	
	public void deletar(Long id) {
		Musica musicaExistente = buscarPorId(id);
		musicaRepository.delete(musicaExistente);
	}
		
}
