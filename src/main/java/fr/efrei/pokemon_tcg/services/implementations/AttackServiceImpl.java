package fr.efrei.pokemon_tcg.services.implementations;

import java.util.List;

import fr.efrei.pokemon_tcg.models.Attack;
import fr.efrei.pokemon_tcg.repositories.AttackRepository;
import fr.efrei.pokemon_tcg.services.IAttackService;

public class AttackServiceImpl implements IAttackService {
    private final AttackRepository repository;

	public AttackServiceImpl(AttackRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Attack> findAll() {
		return repository.findAll();
	}

	@Override
	public Attack findById(String uuid) {
		return repository.findById(uuid).orElse(null);
	}

	@Override
	public void save(Attack attack) {
		repository.save(attack);
	}
}
