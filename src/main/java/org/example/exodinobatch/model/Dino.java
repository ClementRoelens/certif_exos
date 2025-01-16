package org.example.exodinobatch.model;

public class Dino {
    private int id;
    private String name;
    private String species;
    private double ageInMillionsYears;


    public Dino() {
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

    public double getAgeInMillionsYears() {
        return ageInMillionsYears;
    }

    public void setAgeInMillionsYears(double ageInMillionsYears) {
        this.ageInMillionsYears = ageInMillionsYears;
    }
}
