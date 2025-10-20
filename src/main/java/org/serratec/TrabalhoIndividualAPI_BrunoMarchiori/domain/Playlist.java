package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "playlist")
public class Playlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
	@NotBlank(message = "O nome da playlist não pode ser vazio")
	private String nome;
	
	@Column(nullable = false)
	@Size(min = 5, max = 255, message = "A descrição deve ter entre 5 e 255 caracteres")
	@NotBlank(message = "A descrição da playlist não pode ser vazia")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@JsonBackReference
	private Usuario usuario;

	@ManyToMany
	@JoinTable(
	    name = "playlist_musica",
	    joinColumns = @JoinColumn(name = "playlist_id"),
	    inverseJoinColumns = @JoinColumn(name = "musica_id")
	)
	private List<Musica> musicas;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Playlist(Long id, String nome, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public Playlist() {
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public Playlist(Long id, String nome, String descricao, Usuario usuario, List<Musica> musicas) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.usuario = usuario;
		this.musicas = musicas;
	}
	
	
	
}
