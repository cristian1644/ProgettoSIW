package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;
	private List <String> images;

	private List<IngredienteInPizza> ingredientiInPizza;

	private String descrizione;

	//setter e getter
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
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public List<IngredienteInPizza> getIngredientiInPizza() {
		return ingredientiInPizza;
	}
	public void setIngredienti(List<IngredienteInPizza> ingredientiInPizza) {
		this.ingredientiInPizza = ingredientiInPizza;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String desc) {
		this.descrizione = desc;
	}

	//equals e hashcode
	@Override
	public int hashCode() {
		return Objects.hash(descrizione, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		return Objects.equals(descrizione, other.descrizione) && Objects.equals(nome, other.nome);
	}

}
