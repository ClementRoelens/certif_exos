package org.example.ihm;

import org.example.ZooManager;
import org.example.zoo.model.Animal;
import org.example.zoo.model.Enclosure;

import java.util.Scanner;

public class ConsoleMenu {
    Scanner scanner = new Scanner(System.in);

    public void menu() {
        int choice;

        System.out.print("""
                
                === Menu Zoo ===
                1. Ajouter un animal
                2. Lister les animaux
                3. Nourrir les animaux
                4. Quitter
                Choix :
                """);
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> addAnimal();
            case 2 -> listAnimals();
            case 3 -> feedAnimals();
        }

        System.out.println("Au revoir");
        scanner.close();
    }

    private void addAnimal() {
        String name, species;
        boolean isInputCorrect = false;
        int enclosureChoice = 0, animalClass = 0;

        System.out.print("Entrez le nom de l'animal :");
        name = scanner.nextLine();

        System.out.print("Entrez l'espèce : ");
        species = scanner.nextLine();

        while (!isInputCorrect){
            System.out.print("Tapez 1 si l'animal est un mammifère, 2 si c'est un oiseau");
            animalClass = scanner.nextInt();
            scanner.nextLine();

            isInputCorrect = animalClass == 1 || animalClass == 2;
        }
        for (Enclosure enclosure : ZooManager.getEnclosures()){
            System.out.println(enclosure.getId() + " : " + enclosure.getName());
        }
        isInputCorrect = false;
        while (!isInputCorrect){
            System.out.print("Choisissez l'enclos : ");
            enclosureChoice = scanner.nextInt();
            scanner.nextLine();
            if (ZooManager.getEnclosures().stream().map(Enclosure::getId).toList().contains(enclosureChoice)){
                isInputCorrect = true;
            }
        }

        ZooManager.addAnimal(name, species, animalClass,enclosureChoice);
        menu();
    }
    private void listAnimals() {
        ZooManager.listAnimals();
        menu();
    }
    private void feedAnimals() {
        ZooManager.feedAnimals();
        menu();
    }


}
