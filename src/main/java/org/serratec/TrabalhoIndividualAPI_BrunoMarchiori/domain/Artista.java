package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "artista")
public class Artista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome do artista não pode ser vazio")
	@Column(nullable = false)
	@Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
	@Schema(description = "Nome do artista", example = "Michael Jackson")
	private String nome;
	
	@NotBlank(message = "A nacionalidade do artista não pode ser vazia")
	@Column(nullable = false)
	@Size(min = 2, max = 30, message = "A nacionalidade deve ter entre 2 e 30 caracteres")
	@Schema(description = "Nacionalidade do artista.", example = "Canadense")
	private String nacionalidade;
	
	@ManyToMany(mappedBy = "artistas")
	@JsonIgnore
	private List<Musica> musicas;

	public Artista(Long id, String nome, String nacionalidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
	}

	public Artista() {
		super();
	}

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

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}
	
	
	
}
