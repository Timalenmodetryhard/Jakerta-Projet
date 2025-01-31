package fr.efrei.pokemon_tcg.services.implementations;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.efrei.pokemon_tcg.dto.DrawPokemon;
import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.DresseurRepository;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import fr.efrei.pokemon_tcg.services.IPokemonService;

@Service
public class DresseurServiceImpl implements IDresseurService {
	@Override
	public List<Pokemon> DrawPokemon(String uuid) {
		return List.of();
	}

	@Override
	public boolean echangerCartes(String uuid1, String uuid2, String pokemonUuid1, String pokemonUuid2) {
		return false;
	}

	private final DresseurRepository repository;
	private final IPokemonService pokemonService;
	public DresseurServiceImpl(DresseurRepository repository, PokemonServiceImpl pokemonService) {
		this.repository = repository;
		this.pokemonService = pokemonService;
	}

	@Override
	public List<Dresseur> findAll() {
		return repository.findAllByDeletedAtNull();
	}

	@Override
	public Dresseur findById(String uuid) {
		return repository.findById(uuid).orElse(null);
	}

	@Override
	public void capturerPokemon(String uuid, DrawPokemon capturePokemon) {
		Dresseur dresseur = findById(uuid);
		Pokemon pokemon = pokemonService.findById(capturePokemon.getUuid());
		if (dresseur == null || pokemon == null) {
			throw new IllegalStateException("Dresseur ou Pokémon introuvable !");
		}
	
		dresseur.getPokemonList().add(pokemon);
		repository.save(dresseur);
	
		System.out.println("Pokémon capturé ! Dresseur: " + dresseur.getNom() + " a capturé " + pokemon.getNom());
	}

	@Override
	public void create(DresseurDTO dresseurDTO) {
		Dresseur dresseur = new Dresseur();
		dresseur.setNom(dresseurDTO.getNom());
		dresseur.setPrenom(dresseurDTO.getPrenom());
		dresseur.setDeletedAt(null);
		repository.save(dresseur);
	}

	@Override
	public boolean update(String uuid, DresseurDTO dresseurDTO) {
		return false;
	}

	@Override
	public boolean delete(String uuid) {
		Dresseur dresseur = findById(uuid);
		dresseur.setDeletedAt(LocalDateTime.now());
		repository.save(dresseur);
		return true;
	}
}
