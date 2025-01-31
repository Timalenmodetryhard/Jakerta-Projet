package fr.efrei.pokemon_tcg.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Echange {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "dresseur1_uuid")
    private Dresseur dresseur1;

    @ManyToOne
    @JoinColumn(name = "dresseur2_uuid")
    private Dresseur dresseur2;

    @ManyToOne
    @JoinColumn(name = "pokemon1_uuid")
    private Pokemon pokemon1;

    @ManyToOne
    @JoinColumn(name = "pokemon2_uuid")
    private Pokemon pokemon2;

    private LocalDate dateEchange;

    public String getUuid() {
        return uuid;
    }

    public Dresseur getDresseur1() {
        return dresseur1;
    }

    public void setDresseur1(Dresseur dresseur1) {
        this.dresseur1 = dresseur1;
    }

    public Dresseur getDresseur2() {
        return dresseur2;
    }

    public void setDresseur2(Dresseur dresseur2) {
        this.dresseur2 = dresseur2;
    }

    public Pokemon getPokemon1() {
        return pokemon1;
    }

    public void setPokemon1(Pokemon pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public Pokemon getPokemon2() {
        return pokemon2;
    }

    public void setPokemon2(Pokemon pokemon2) {
        this.pokemon2 = pokemon2;
    }

    public LocalDate getDateEchange() {
        return dateEchange;
    }

    public void setDateEchange(LocalDate dateEchange) {
        this.dateEchange = dateEchange;
    }
}

