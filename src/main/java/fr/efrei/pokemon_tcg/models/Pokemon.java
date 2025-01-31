package fr.efrei.pokemon_tcg.models;

import java.util.List;

import fr.efrei.pokemon_tcg.constants.TypePokemon;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false)
    private String uuid;

    private String nom;
    private Integer vie;
    private Integer etoile;

    @Enumerated(EnumType.STRING)
    private TypePokemon type;

    @ManyToMany()
    private List<Attack> attackSet;

    public String getUuid() {
        return uuid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getVie() {
        return vie;
    }

    public void setVie(Integer vie) {
        this.vie = vie;
    }

    public Integer getEtoile() {
        return etoile;
    }

    public void setEtoile(Integer etoile) {
        this.etoile = etoile;
    }

    public TypePokemon getType() {
        return type;
    }

    public void setType(TypePokemon type) {
        this.type = type;
    }

    public List<Attack> getAttackSet() {
        return attackSet;
    }

    public void setAttackSet(List<Attack> attackSet) {
        this.attackSet = attackSet;
    }
}
