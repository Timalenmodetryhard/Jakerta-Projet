package fr.efrei.pokemon_tcg.models;

import fr.efrei.pokemon_tcg.constants.TypePokemon;
import jakarta.persistence.*;

@Entity
public class Attack {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String nom;
    private String description;
    private Integer degat;

    @Enumerated(EnumType.STRING)
    private TypePokemon type;

    @ManyToOne
    @JoinColumn(name = "pokemon_uuid", nullable = false)
    private Pokemon pokemon;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDegat() {
        return degat;
    }

    public void setDegat(Integer degat) {
        this.degat = degat;
    }

    public TypePokemon getType() {
        return type;
    }

    public void setType(TypePokemon type) {
        this.type = type;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
