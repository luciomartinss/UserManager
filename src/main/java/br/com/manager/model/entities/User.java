package br.com.manager.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(nullable = false)
	String nome;
	
	@Column(nullable = false, unique = true)
	String email;
	
	@Column(nullable = false, unique = true)
	String telefone;
	
	@Column(nullable = false)
	String cidade;
	
	@Column(nullable = false)
	String uf;
	
	@Column(nullable = false)
	String genero;
	
	public User() {
		
	}

	public User(String id, String nome, String email, String telefone, String cidade, String uf,
			String genero) {
		
		this.id = Integer.parseInt(id);
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.cidade = cidade;
		this.uf = uf;
		this.genero = genero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
	
	

}
