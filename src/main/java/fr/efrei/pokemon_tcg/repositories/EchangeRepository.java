package fr.efrei.pokemon_tcg.repositories;

import fr.efrei.pokemon_tcg.models.Echange;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface EchangeRepository extends JpaRepository<Echange, String> {
	Optional<Echange> findByDresseur1_UuidAndDresseur2_UuidAndDateEchange(String dresseur1Uuid, String dresseur2Uuid, LocalDate date);
}
