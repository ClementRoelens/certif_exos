import factory.AnimalFactory;
import factory.CatFactory;
import factory.DogFactory;
import model.Animal;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnimalFactory catFactory = new CatFactory();
        AnimalFactory dogFactory = new DogFactory();

        Animal cat = catFactory.createAnimal();
        Animal dog = dogFactory.createAnimal();

        List<Animal> animals = Arrays.asList(cat, dog);

        for (Animal animal : animals) {
            System.out.println("Oh, mais quelle est cette boule de poils ?");
            animal.makeSound();
            System.out.println("Approchons l'animal");
            animal.attack();
            System.out.println("Appelons l'animal");
            animal.call();
            System.out.println();
        }
    }
}
