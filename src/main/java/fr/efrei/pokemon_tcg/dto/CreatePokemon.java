package fr.efrei.pokemon_tcg.dto;

import fr.efrei.pokemon_tcg.constants.TypePokemon;
import fr.efrei.pokemon_tcg.models.Attack;
import jakarta.validation.constraints.Positive;

import java.util.List;

import org.hibernate.validator.constraints.Length;

public class CreatePokemon {

	@Length(min = 3, max = 20)
	private String nom;

	@Positive

	private TypePokemon type;

	private Integer vie;

	private Integer etoile;

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
