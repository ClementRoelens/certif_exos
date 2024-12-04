package org.example;

import org.example.ihm.ConsoleMenu;
import org.example.zoo.ZooAction;
import org.example.zoo.model.Animal;
import org.example.zoo.model.Enclosure;

import java.util.ArrayList;
import java.util.List;

public class ZooManager implements ZooAction {
    private static List<Enclosure> enclosures = new ArrayList<Enclosure>();

    public static void main(String[] args) {
        enclosures.add(new Enclosure("Savane"));
        enclosures.add(new Enclosure("Sea"));
        enclosures.add(new Enclosure("Forest"));
        ConsoleMenu consoleMenu = new ConsoleMenu();

        consoleMenu.menu();
    }

    public static void addAnimal(String name, String species, int animalClass, int enclosureId) {
        Enclosure enclosure = enclosures.stream()
                .filter(e -> e.getId() == enclosureId)
                .findFirst()
                .orElse(null);
        if (enclosure == null) {
            throw new IllegalArgumentException("Choix non valide");
        } else {
            enclosure.addAnimal(new Animal(name,species) {
                @Override
                public String getDetails() {
                    String details;
                    if (animalClass == 1){
                        details = "Je suis un mammifère";
                    } else {
                        details = "Je suis un oiseau";
                    }
                    details += ", je m'appelle " + name + " et je suis un " + species;

                    return details;
                }

                @Override
                public void eat() {
                    System.out.println(name + " a été nourri !");
                }
            });
        }
    }

    public static void listAnimals() {
        for (Enclosure enclosure : enclosures) {
            for (Animal animal : enclosure.getAnimals()) {
                System.out.println(animal.getDetails());
            }
        }
    }

    public static List<Enclosure> getEnclosures() {
        return enclosures;
    }

   public static void feedAnimals() {
        for (Enclosure enclosure : enclosures) {
            for (Animal animal : enclosure.getAnimals()) {
                animal.eat();
            }
        }
    }
}