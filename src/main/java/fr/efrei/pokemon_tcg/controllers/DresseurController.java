package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.dto.DrawPokemon;
import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import fr.efrei.pokemon_tcg.services.implementations.DresseurServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dresseurs")
public class DresseurController {

	private final IDresseurService dresseurService;

	public DresseurController(DresseurServiceImpl dresseurService) {
		this.dresseurService = dresseurService;
	}

	@GetMapping
	public ResponseEntity<List<Dresseur>> findAll() {
		return new ResponseEntity<>(dresseurService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody DresseurDTO dresseurDTO) {
		dresseurService.create(dresseurDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<?> delete(@PathVariable String uuid) {
		dresseurService.delete(uuid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping("/{uuid}/capturer")
	public ResponseEntity<?> capturer(
			@PathVariable String uuid,
			@RequestBody DrawPokemon capturePokemon
	) {
		dresseurService.capturerPokemon(uuid, capturePokemon);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping("/{uuid}/acheter")
	public ResponseEntity<?> acheter() {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping("/{uuid}/piocher")
	public ResponseEntity<?> piocher(@PathVariable String uuid) {
		try {
			List<Pokemon> drawnPokemons = dresseurService.DrawPokemon(uuid);
			return new ResponseEntity<>(drawnPokemons, HttpStatus.OK);
		} catch (IllegalStateException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@PatchMapping("/{uuid}/echanger")
	public ResponseEntity<?> echangerCarte(
			@PathVariable String uuid,
			@RequestParam String pokemonUuid,
			@RequestParam boolean versPrincipal
	) {
		dresseurService.echangerCartes(uuid, pokemonUuid, versPrincipal);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping("/{uuid1}/defier/{uuid2}")
	public ResponseEntity<?> defier(
			@PathVariable String uuid1,
			@PathVariable String uuid2
	) {
		try {
			dresseurService.defierDresseur(uuid1, uuid2);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IllegalStateException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
