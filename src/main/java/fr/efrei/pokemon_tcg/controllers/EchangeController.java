package fr.efrei.pokemon_tcg.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.efrei.pokemon_tcg.services.IEchangeService;

@RestController
@RequestMapping("/echanges")
public class EchangeController {

    private final IEchangeService echangeService;

    public EchangeController(IEchangeService echangeService) {
        this.echangeService = echangeService;
    }

    @PostMapping
    public ResponseEntity<?> echangerPokemon(
            @RequestParam String dresseur1_uuid,
            @RequestParam String dresseur2_uuid,
            @RequestParam String pokemon1_uuid,
            @RequestParam String pokemon2_uuid
    ) {
        boolean succes = echangeService.echangerPokemon(dresseur1_uuid, dresseur2_uuid, pokemon1_uuid, pokemon2_uuid);
        if (!succes) {
            return new ResponseEntity<>("Échange impossible (déjà effectué ou erreur).", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
