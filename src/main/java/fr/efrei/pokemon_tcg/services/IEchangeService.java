package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.models.Echange;

public interface IEchangeService {
    boolean echangerPokemon(String dresseur1Uuid, String dresseur2Uuid, String pokemon1Uuid, String pokemon2Uuid);
}
