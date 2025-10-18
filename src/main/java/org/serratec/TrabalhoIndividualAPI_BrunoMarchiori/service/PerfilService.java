package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.service;

import java.util.List;

import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Perfil;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;
	
	public List<Perfil> listarTodos() {
		return perfilRepository.findAll();
	}
	
	public Perfil buscarPorId(Long id) {
		return perfilRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Perfil com id " + id + " não encontrado"));
	}
	
	public Perfil criar(Perfil perfil) {
		return perfilRepository.save(perfil);
	}
	
	public Perfil atualizar(Long id, Perfil perfilAtualizado) {
		Perfil perfilExistente = buscarPorId(id);
		perfilExistente.setTelefone(perfilAtualizado.getTelefone());
		perfilExistente.setDataNascimento(perfilAtualizado.getDataNascimento());
		perfilExistente.setUsuario(perfilAtualizado.getUsuario());
		return perfilRepository.save(perfilExistente);
	}
	
	public void deletar(Long id) {
		Perfil perfilExistente = buscarPorId(id);
		perfilRepository.delete(perfilExistente);
	}
	
}
