package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.dto.DrawPokemon;
import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Echange;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.EchangeRepository;


import java.util.List;

public interface IDresseurService<Pokemon> {

	List<Dresseur> findAll();
	Dresseur findById(String uuid);
	void create(DresseurDTO dresseurDTO);

	boolean update(String uuid, DresseurDTO dresseurDTO);
	boolean delete(String uuid);
	
	void capturerPokemon(String uuid, DrawPokemon capturePokemon);
	boolean echangerCartes(String uuid1, String uuid2, String pokemonUuid1, String pokemonUuid2);


	List<fr.efrei.pokemon_tcg.models.Pokemon> DrawPokemon(String uuid);
}
