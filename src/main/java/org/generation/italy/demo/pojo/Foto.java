package org.generation.italy.demo.pojo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Foto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotEmpty(message = "Dai un titolo")
	@Column(length = 128)
	private String titolo;
	
	@NotNull
	@NotEmpty(message = "aggiungi una descrizione")
	@Lob
	private String descrizione;
	
	@NotNull
	@NotEmpty(message = "aggiungi un url")
	@Column(length = 300)
	private String url;
	
	@NotEmpty(message = "Dai un titolo")
	@Column(length = 100)
	private String tag;
	
	@Column(length = 100)
	private boolean visibile;
	
	@ManyToMany( cascade = CascadeType.DETACH)
	private List<Categoria> categorie;
	
	@OneToMany(mappedBy = "foto",  cascade = CascadeType.REMOVE)
	private List<Commento> commenti;
	
	public Foto() { }
	
	public Foto(String titolo, String descrizione, String url, String tag, boolean visibile, List<Categoria> categorie) {
		setTitolo(titolo);
		setDescrizione(descrizione);
		setUrl(url);
		setTag(tag);
		setVisibile(visibile);
		setCategorie(categorie);
	}
	
	public Foto(String titolo, String descrizione, String url, String tag, boolean visibile, Categoria categorie) {
		setTitolo(titolo);
		setDescrizione(descrizione);
		setUrl(url);
		setTag(tag);
		setVisibile(visibile);
		addCategoria(categorie);
	}
	
	// id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	// titolo
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	// descrizione
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	// url
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	// tag
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	// visibility
	public boolean isVisibile() {
		return visibile;
	}
	public void setVisibile(boolean visibile) {
		this.visibile = visibile;
	}
	//catogorie
	public List<Categoria> getCategorie() {
		return categorie;
	}
	public void setCategorie(List<Categoria> categorie) {
		this.categorie = categorie;
	}
	public void addCategoria(Categoria categoria) {
		
		boolean finded = false;
		for (Categoria i : getCategorie()) 
			if (i.getId() == categoria.getId())
				finded = true;
		
		if (!finded) 
			getCategorie().add(categoria);
	}
	
	public List<Commento> getCommenti() {
		return commenti;
	}

	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}

	@Override
	public String toString() {
		return (getId()) + getTitolo(); 
	}
	
	

}
