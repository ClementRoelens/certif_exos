package org.example.zoo.model;

public abstract class Animal {
    private int id;
    protected String name;
    protected String species;
    private static int count = 1;

    public Animal(String name, String species) {
        this.id = count++;
        this.name = name;
        this.species = species;
    }

    public abstract String getDetails();
    public abstract void eat();
}
