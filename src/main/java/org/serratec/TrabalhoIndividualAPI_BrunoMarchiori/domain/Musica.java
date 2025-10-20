package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain;

import java.util.List;

import org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.enums.GeneroMusical;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "musica")
public class Musica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Size(min = 2, max = 100, message = "O título deve ter entre 2 e 100 caracteres")
	@NotBlank(message = "O título da música não pode ser vazio")
	private String titulo;

	@Column(nullable = false)
	@NotNull(message = "A duração da música em segundos não pode ser nula")
	private Integer segundos;

	@Enumerated(jakarta.persistence.EnumType.STRING)
	private GeneroMusical genero;

	@ManyToMany
	@JoinTable(name = "musica_artista", joinColumns = @JoinColumn(name = "musica_id"), inverseJoinColumns = @JoinColumn(name = "artista_id"))
	private List<Artista> artistas;

	@ManyToMany(mappedBy = "musicas")
	@JsonIgnore
	private List<Playlist> playlists;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getSegundos() {
		return segundos;
	}

	public void setSegundos(Integer segundos) {
		this.segundos = segundos;
	}

	public GeneroMusical getGenero() {
		return genero;
	}

	public void setGenero(GeneroMusical genero) {
		this.genero = genero;
	}

	public Musica(Long id, String titulo, Integer segundos, GeneroMusical genero) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.segundos = segundos;
		this.genero = genero;
	}

	public Musica() {
	}

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Musica(Long id, String titulo, Integer segundos, GeneroMusical genero, List<Artista> artistas,
			List<Playlist> playlists) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.segundos = segundos;
		this.genero = genero;
		this.artistas = artistas;
		this.playlists = playlists;
	}

}
