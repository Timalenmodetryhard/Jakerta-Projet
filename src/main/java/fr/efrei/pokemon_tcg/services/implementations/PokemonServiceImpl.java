package fr.efrei.pokemon_tcg.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.efrei.pokemon_tcg.constants.TypePokemon;
import fr.efrei.pokemon_tcg.dto.CreatePokemon;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.PokemonRepository;
import fr.efrei.pokemon_tcg.services.IPokemonService;

@Service
public class PokemonServiceImpl implements IPokemonService {

	private final PokemonRepository repository;

	public PokemonServiceImpl(PokemonRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Pokemon> findAll(TypePokemon typePokemon) {
		if(typePokemon == null) {
			return repository.findAll();
		}
		return repository.findAllByType(typePokemon);
	}

	@Override
	public void create(CreatePokemon pokemon) {
		Pokemon pokemonACreer = new Pokemon();
		pokemonACreer.setNom(pokemon.getNom());
		pokemonACreer.setVie(pokemon.getVie());
		pokemonACreer.setEtoile(pokemon.getEtoile());
		pokemonACreer.setType(pokemon.getType());
		pokemonACreer.setAttackSet(pokemon.getAttackSet());
		repository.save(pokemonACreer);
	}

	@Override
	public Pokemon findById(String uuid) {
		return repository.findById(uuid).orElse(null);
	}

	@Override
	public boolean update(String uuid, Pokemon pokemon) {
		Pokemon pokemonAModifier = findById(uuid);
		if(pokemonAModifier == null) {
			return false;
		}
		pokemonAModifier.setNom(pokemon.getNom());
		pokemonAModifier.setVie(pokemon.getVie());
		pokemonAModifier.setEtoile(pokemon.getVie());
		pokemonAModifier.setType(pokemon.getType());
		pokemonAModifier.setAttackSet(pokemon.getAttackSet());
		repository.save(pokemonAModifier);
		return true;
	}

	@Override
	public boolean delete(String uuid) {
		Pokemon pokemonAModifier = findById(uuid);
		if(pokemonAModifier == null) {
			return false;
		}
		repository.deleteById(uuid);
		return true;
	}
}
