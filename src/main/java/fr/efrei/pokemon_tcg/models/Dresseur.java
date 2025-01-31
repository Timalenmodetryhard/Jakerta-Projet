package fr.efrei.pokemon_tcg.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Dresseur {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String uuid;

	private String nom;

	private String prenom;

	private LocalDateTime deletedAt;

	@ManyToMany
	List<Pokemon> pokemonList;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public List<Pokemon> getPokemonList() {
		return pokemonList;
	}

	public void setPokemonList(List<Pokemon> pokemonList) {
		this.pokemonList = pokemonList;
	}
	@OneToMany
	@JoinColumn(name = "dresseur_uuid")
	private List<Pokemon> paquetPrincipal = new ArrayList<>();

	@OneToMany
	@JoinColumn(name = "dresseur_uuid")
	private List<Pokemon> paquetSecondaire = new ArrayList<>();

	public List<Pokemon> getPaquetPrincipal() {
		return paquetPrincipal;
	}

	public void setPaquetPrincipal(List<Pokemon> paquetPrincipal) {
		this.paquetPrincipal = paquetPrincipal;
	}

	public List<Pokemon> getPaquetSecondaire() {
		return paquetSecondaire;
	}

	public void setPaquetSecondaire(List<Pokemon> paquetSecondaire) {
		this.paquetSecondaire = paquetSecondaire;
	}

}
