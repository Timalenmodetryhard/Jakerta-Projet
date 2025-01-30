package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.models.Attack;
import fr.efrei.pokemon_tcg.services.IAttackService;
import fr.efrei.pokemon_tcg.services.IItemService;
import fr.efrei.pokemon_tcg.services.implementations.AttackServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attacks")
public class AttackController {

	private final IAttackService attackService;

	public AttackController(AttackServiceImpl attackService) {
		this.attackService = attackService;
	}

	@GetMapping
	public ResponseEntity<List<Attack>> findAll() {
		return new ResponseEntity<>(attackService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Attack attack) {
		attackService.save(attack);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
