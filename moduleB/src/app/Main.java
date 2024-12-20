package app;

import myPackage.Person;
import myPackage.subPackage.Animal;
import mySecondPackage.Voiture;
import music.Album;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Person> people;

    private static Voiture voiture;

    private static Animal animal;

    public static void main(String[] args) {


//        people = new ArrayList<>(List.of(new Person("toto",42),new Person("tata",43)));
//
//        for (Person person : people) {
//            System.out.println(person);
//        }
        Album album = new Album("The dark side of the moon", "Pink FLoyd", 1973);
        System.out.println(album);
    }
}
