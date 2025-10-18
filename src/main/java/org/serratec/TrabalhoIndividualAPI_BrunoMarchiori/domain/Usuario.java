package org.serratec.TrabalhoIndividualAPI_BrunoMarchiori.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Usuario.class)
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
	@NotBlank(message = "O nome do usuário não pode ser vazio")
	private String nome;
	
	@Column(nullable = false, unique = true)
	@NotBlank(message = "O email do usuário não pode ser vazio")
	private String email;
	
	@OneToMany(mappedBy = "usuario")
	@JsonManagedReference
	private List<Playlist> playlists;
	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Perfil perfil;

	public Usuario(Long id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public Usuario() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Usuario(Long id, String nome, String email, List<Playlist> playlists, Perfil perfil) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.playlists = playlists;
		this.perfil = perfil;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	
	
}
