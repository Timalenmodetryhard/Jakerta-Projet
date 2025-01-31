package fr.efrei.pokemon_tcg.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import fr.efrei.pokemon_tcg.constants.TypePokemon;
import fr.efrei.pokemon_tcg.models.Attack;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;

public class CreatePokemon {

	@Length(min = 3, max = 20)
	private String nom;

	private TypePokemon type;

	@Positive
	private Integer vie;

	@Positive
	private Integer etoile;

	@OneToMany()
	private List<Attack> attackSet;

	public String getNom() {
		return nom;
	}

	public Integer getVie() {
		return vie;
	}

	public TypePokemon getType() {
		return type;
	}

	public Integer getEtoile() {
		return etoile;
	}

	public List<Attack> getAttackSet() {
		return attackSet;
	}
}
