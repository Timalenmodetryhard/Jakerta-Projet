package fr.efrei.pokemon_tcg.repositories;

import fr.efrei.pokemon_tcg.models.Attack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttackRepository extends JpaRepository<Attack, String> {
}
