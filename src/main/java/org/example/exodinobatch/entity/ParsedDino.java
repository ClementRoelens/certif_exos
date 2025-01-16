package org.example.exodinobatch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ParsedDino {
    @Id
    private int id;
    private String name;
    private String species;
    private double ageInCenturies;


    public ParsedDino() {
    }

    public ParsedDino(int id, String name, String species, double ageInCenturies) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.ageInCenturies = ageInCenturies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double getAgeInCenturies() {
        return ageInCenturies;
    }

    public void setAgeInCenturies(double ageInCenturies) {
        this.ageInCenturies = ageInCenturies;
    }
}


