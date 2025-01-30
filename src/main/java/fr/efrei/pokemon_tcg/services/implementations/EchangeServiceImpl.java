package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Echange;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.DresseurRepository;
import fr.efrei.pokemon_tcg.repositories.EchangeRepository;
import fr.efrei.pokemon_tcg.repositories.PokemonRepository;
import fr.efrei.pokemon_tcg.services.IEchangeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EchangeServiceImpl implements IEchangeService {

    private final EchangeRepository echangeRepository;
    private final DresseurRepository dresseurRepository;
    private final PokemonRepository pokemonRepository;

    public EchangeServiceImpl(EchangeRepository echangeRepository, DresseurRepository dresseurRepository, PokemonRepository pokemonRepository) {
        this.echangeRepository = echangeRepository;
        this.dresseurRepository = dresseurRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public boolean echangerPokemon(String dresseur1Uuid, String dresseur2Uuid, String pokemon1Uuid, String pokemon2Uuid) {
        Optional<Echange> echangeExistant = echangeRepository.findByDresseur1_UuidAndDresseur2_UuidAndDateEchange(dresseur1Uuid, dresseur2Uuid, LocalDate.now());

        if (echangeExistant.isPresent()) {
            return false;
        }

        Optional<Dresseur> dresseur1Opt = dresseurRepository.findById(dresseur1Uuid);
        Optional<Dresseur> dresseur2Opt = dresseurRepository.findById(dresseur2Uuid);
        Optional<Pokemon> pokemon1Opt = pokemonRepository.findById(pokemon1Uuid);
        Optional<Pokemon> pokemon2Opt = pokemonRepository.findById(pokemon2Uuid);

        if (dresseur1Opt.isEmpty() || dresseur2Opt.isEmpty() || pokemon1Opt.isEmpty() || pokemon2Opt.isEmpty()) {
            return false;
        }

        Dresseur dresseur1 = dresseur1Opt.get();
        Dresseur dresseur2 = dresseur2Opt.get();
        Pokemon pokemon1 = pokemon1Opt.get();
        Pokemon pokemon2 = pokemon2Opt.get();

        Echange echange = new Echange();
        echange.setDresseur1(dresseur1);
        echange.setDresseur2(dresseur2);
        echange.setPokemon1(pokemon1);
        echange.setPokemon2(pokemon2);
        echange.setDateEchange(LocalDate.now());

        echangeRepository.save(echange);
        return true;
    }
}
