package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.service;

import java.util.List;

import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain.Usuario;
import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuário com id " + id + " não encontrado"));
	}
	
	public Usuario criar(Usuario usuario) {
        if (usuario.getPerfil() != null) {
            usuario.getPerfil().setUsuario(usuario);
        }
        return usuarioRepository.save(usuario);
    }
	
	public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
		Usuario usuarioExistente = buscarPorId(id);
		usuarioExistente.setNome(usuarioAtualizado.getNome());
		usuarioExistente.setEmail(usuarioAtualizado.getEmail());
		usuarioExistente.setPerfil(usuarioAtualizado.getPerfil());
		usuarioExistente.setPlaylists(usuarioAtualizado.getPlaylists());
		return usuarioRepository.save(usuarioExistente);
	}
	
	public void deletar(Long id) {
		Usuario usuarioExistente = buscarPorId(id);
		usuarioRepository.delete(usuarioExistente);
	}
}
