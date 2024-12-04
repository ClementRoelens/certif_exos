package org.example.zoo.model;

import java.util.ArrayList;
import java.util.List;

public class Enclosure {
    private int id;
    private String name;
    private final List<Animal> animals;
    private static int count = 1;

    public Enclosure(String name) {
        this.name = name;
        animals = new ArrayList<>();
        id = count++;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
