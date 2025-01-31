package fr.efrei.pokemon_tcg.services;

import java.util.List;

import fr.efrei.pokemon_tcg.dto.DrawPokemon;
import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.models.Dresseur;

public interface IDresseurService {

	List<Dresseur> findAll();
	Dresseur findById(String uuid);
	void create(DresseurDTO dresseurDTO);

	boolean update(String uuid, DresseurDTO dresseurDTO);
	boolean delete(String uuid);
	
	void capturerPokemon(String uuid, DrawPokemon capturePokemon);
	boolean echangerCartes(String uuid1, String uuid2, String pokemonUuid1, String pokemonUuid2);


	List<fr.efrei.pokemon_tcg.models.Pokemon> DrawPokemon(String uuid);
	void echangerCartes(String dresseurUuid, String pokemonUuid, boolean versPrincipal);
	void defierDresseur(String dresseur1Uuid, String dresseur2Uuid);

}
