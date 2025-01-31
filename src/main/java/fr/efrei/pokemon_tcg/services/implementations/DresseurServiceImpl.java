package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.dto.DrawPokemon;
import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.DresseurRepository;
import fr.efrei.pokemon_tcg.repositories.PokemonRepository;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import fr.efrei.pokemon_tcg.services.IPokemonService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class DresseurServiceImpl implements IDresseurService {
	private final DresseurRepository dresseurRepository;

	private final PokemonRepository pokemonRepository;

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
	public DresseurServiceImpl(DresseurRepository repository, PokemonServiceImpl pokemonService, DresseurRepository dresseurRepository, PokemonRepository pokemonRepository) {
		this.repository = repository;
		this.pokemonService = pokemonService;
		this.dresseurRepository = dresseurRepository;
		this.pokemonRepository = pokemonRepository;
	}

	@Override
	public List<Dresseur> findAll() {
		return dresseurRepository.findAllByDeletedAtIsNull();
	}

	@Override
	public Dresseur findById(String uuid) {
		return repository.findById(uuid).orElse(null);
	}

	public void capturerPokemon(String uuid, DrawPokemon capturePokemon) {
		Dresseur dresseur = findById(uuid);
		Pokemon pokemon = pokemonService.findById(capturePokemon.getUuid());
		dresseur.getPokemonList().add(pokemon);
		repository.save(dresseur);
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

	@Override
	public void echangerCartes(String dresseurUuid, String pokemonUuid, boolean versPrincipal) {
		Dresseur dresseur = dresseurRepository.findById(dresseurUuid)
				.orElseThrow(() -> new IllegalArgumentException("Dresseur non trouvé"));

		Pokemon pokemon = pokemonRepository.findById(pokemonUuid)
				.orElseThrow(() -> new IllegalArgumentException("Pokemon non trouvé"));

		if (versPrincipal) {
			if (dresseur.getPaquetPrincipal().size() >= 5) {
				throw new IllegalStateException("Le paquet principal est déjà plein.");
			}
			dresseur.getPaquetSecondaire().remove(pokemon);
			dresseur.getPaquetPrincipal().add(pokemon);
		} else {
			dresseur.getPaquetPrincipal().remove(pokemon);
			dresseur.getPaquetSecondaire().add(pokemon);
		}

		dresseurRepository.save(dresseur);
	}

	@Override
	public void defierDresseur(String dresseur1Uuid, String dresseur2Uuid) {
		Dresseur dresseur1 = dresseurRepository.findById(dresseur1Uuid)
				.orElseThrow(() -> new IllegalArgumentException("Dresseur non trouvé"));
		Dresseur dresseur2 = dresseurRepository.findById(dresseur2Uuid)
				.orElseThrow(() -> new IllegalArgumentException("Dresseur non trouvé"));

		if (dresseur1.getPaquetPrincipal().isEmpty() || dresseur2.getPaquetPrincipal().isEmpty()) {
			throw new IllegalStateException("Un des dresseurs n'a pas de cartes en paquet principal.");
		}

		Dresseur gagnant = Math.random() > 0.5 ? dresseur1 : dresseur2;
		Dresseur perdant = gagnant == dresseur1 ? dresseur2 : dresseur1;

		Pokemon meilleureCarte = perdant.getPaquetPrincipal().stream()
				.max(Comparator.comparing(Pokemon::getNiveau))
				.orElseThrow(() -> new IllegalStateException("Le perdant n'a pas de cartes valides"));

		perdant.getPaquetPrincipal().remove(meilleureCarte);
		gagnant.getPaquetPrincipal().add(meilleureCarte);

		dresseurRepository.save(dresseur1);
		dresseurRepository.save(dresseur2);
	}


}
