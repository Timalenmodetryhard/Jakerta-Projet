package fr.efrei.pokemon_tcg.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.services.IPokemonService;

public class DrawPokemon {
	private String uuid;
	private LocalDate lastDrawDate;
	private final IPokemonService pokemonService;

	public DrawPokemon(IPokemonService pokemonService) {
		this.pokemonService = pokemonService;
		this.lastDrawDate = null;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public List<Pokemon> drawDailyPokemons() {
		LocalDate today = LocalDate.now();
		if (lastDrawDate != null && lastDrawDate.isEqual(today)) {
			throw new IllegalStateException("Vous avez déjà tiré vos cartes aujourd'hui !");
		}

		lastDrawDate = today;
		List<Pokemon> allPokemons = pokemonService.findAll(null);
		List<Pokemon> drawnPokemons = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < 5; i++) {
			int index = random.nextInt(allPokemons.size());
			drawnPokemons.add(allPokemons.get(index));
		}

		return drawnPokemons;
	}
}