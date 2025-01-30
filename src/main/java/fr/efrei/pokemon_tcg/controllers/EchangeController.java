package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.services.IEchangeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/echanges")
public class EchangeController {

    private final IEchangeService echangeService;

    public EchangeController(IEchangeService echangeService) {
        this.echangeService = echangeService;
    }

    @PostMapping
    public ResponseEntity<?> echangerPokemon(
            @RequestParam String dresseur1Uuid,
            @RequestParam String dresseur2Uuid,
            @RequestParam String pokemon1Uuid,
            @RequestParam String pokemon2Uuid
    ) {
        boolean succes = echangeService.echangerPokemon(dresseur1Uuid, dresseur2Uuid, pokemon1Uuid, pokemon2Uuid);
        if (!succes) {
            return new ResponseEntity<>("Échange impossible (déjà effectué ou erreur).", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
