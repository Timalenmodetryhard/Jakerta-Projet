package fr.efrei.pokemon_tcg.services;

public interface IEchangeService {
    boolean echangerPokemon(String dresseur1Uuid, String dresseur2Uuid, String pokemon1Uuid, String pokemon2Uuid);
}
