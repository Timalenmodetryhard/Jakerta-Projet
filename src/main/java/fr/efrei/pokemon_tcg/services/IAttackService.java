package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.models.Attack;

import java.util.List;

public interface IAttackService {
    
    List<Attack> findAll();

	Attack findById(String uuid);

	void save(Attack attack);
}
