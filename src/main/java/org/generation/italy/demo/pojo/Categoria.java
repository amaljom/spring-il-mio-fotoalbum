package org.generation.italy.demo.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotEmpty(message = "Dai un titolo")
	@Column(length = 128)
	private String nome;
	/*
	@ManyToMany(mappedBy = "categorie")
	private List<Foto> foto;
	*/
	public Categoria() { }
	public Categoria(String nome) {
		setNome(nome);
	}
	//id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//nome
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
