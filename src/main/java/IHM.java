import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class IHM {
    private final Scanner scanner;
    private EventCollection eventCollection;

    public IHM() {
        scanner = new Scanner(System.in);
        try {
            eventCollection = new EventCollection();
        } catch (IOException e) {
            printAndJournalize("Les événements n'ont pas pu être chargées : \n" + e.getMessage());
            eventCollection = null;
        }
    }

    public void menu(){
        if (eventCollection != null) {
            int choice;
            Character character;

            System.out.print("""
                
                --- Bienvenue dans le jeu d'aventure textuelle ! ---
                1. Créer un nouveau personnage
                2. Charger un personnage existant
                Choisissez une option :
                """);

            choice = ensureIntInput();

            character = (choice == 2) ? loadCharacter() : createCharacter();

            if (character != null){
                List<String> events = eventCollection.getEvents();
                Random random = new Random();
                int i = 0;

                printAndJournalize("--- Début de l'aventure avec " + character.getName() + " ---");

                while (i < 10 && character.getHealth() > 0) {
                    String event = events.get(random.nextInt(events.size()));
                    events.remove(event);

                    printAndJournalize("Événement : " + event);

                    if (event.equals("Vous rencontrez un monstre dans la forêt.")) {
                        try {
                            Character monster = MonsterUtils.getRandomMonster();

                            System.out.println("C'est un " + monster.getName());

                            while (monster.getHealth() > 0 && character.getHealth() > 0){
                                monster.setHealth(monster.getHealth() - character.getStrength());
                                character.setHealth(character.getHealth() - monster.getStrength());
                            }

                            if (monster.getHealth() <= 0){
                                if (character.getHealth() > 0){
                                    printAndJournalize("Succès !");
                                } else {
                                    printAndJournalize("Fin de l'aventure, mais au moins vous avez emporté le monstre avec vous");
                                }
                            } else {
                                printAndJournalize("Fin de l'aventure, le " + monster.getName() + " vous a eu.");
                            }
                        } catch (IOException e) {
                            printAndJournalize("Le monstre n'a pas pu être chargé : \n" + e.getMessage());
                            return;
                        }
                    } else {
                        if (random.nextBoolean()){
                            printAndJournalize("Succès !");
                        } else {
                            printAndJournalize("Échec, vous perdez " + random.nextInt(0,30) + " points de vie");
                        }
                    }
                    i++;
                }

                printAndJournalize("--- Fin de l'aventure de " + character.getName() + " ---\n" +
                        "Votre santé restante : " + character.getHealth() + "\n");
                System.out.println("Un journal de votre aventure a été généré : journal.txt");
            }
        } else {
            printAndJournalize("Vous ne pouvez pas jouer,les événements sont introuvables");
        }
    }

    private Character createCharacter() {
        String name;
        int strength, health;

        System.out.print("\nEntrez le nom de votre héros : ");
        name = scanner.nextLine();
        System.out.println("\nEntrez la force de votre personnage (1-100) : ");
        strength = ensureOneToHundredInput();
        System.out.println("\nEntrez la santé de votre personnage (1-100) : ");
        health = ensureOneToHundredInput();

        Character character = new Character(name, strength, health);
        try {
            SerialUtils.serialize("src/main/resources/characters/" + character.getName() + ".ser", character);
            System.out.println("Personnage enregistré");
        } catch (IOException e) {
            System.out.println("\nVotre personnage n'a pas pu être enregistré : \n" + e.getMessage());
        }

        return character;
    }

    private Character loadCharacter() {
        File file = new File("src/main/resources/characters");
        File[] serializedCharacters = file.listFiles();
        Character[] characters;
        int choice;

        if (serializedCharacters.length == 0) {
            System.out.println("Il n'y a aucun personnage enregistré");
            return null;
        }

        characters = new Character[serializedCharacters.length];

        for (int i = 0; i < serializedCharacters.length; i++) {
            try {
                characters[i] = (Character) SerialUtils.deserialize(serializedCharacters[i].getPath());
                System.out.printf("%d - %s\n", i, characters[i]);
            } catch (Exception e) {
                System.out.println("Le personnage n'a pas pu être récupéré : \n" + e.getMessage());
            }
        }

        while (true ){
            System.out.print("Quel personnage choisis-tu ?");
            choice = ensureIntInput();

            try {
                return characters[choice];
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nTon personnage est hors limite");
            }
        }
    }

    private int ensureIntInput(){
        while (true){
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e){
                System.out.println("\nEntrez un nombre entier entre 1 et 2");
            } finally {
                scanner.nextLine();
            }
        }
    }

    private int ensureOneToHundredInput(){
        int value = -1;
        while (value < 1 || value > 100 ){
            try {
                value = scanner.nextInt();
            } catch (InputMismatchException e){
                System.out.println("\nEntrez un nombre entier entre 1 et 100");
            } finally {
                scanner.nextLine();
            }
        }

        return value;
    }

    private void printAndJournalize(String message){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/journal.txt", true))) {
            writer.write(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + ": " + message);
            writer.newLine();
        } catch (IOException e) {
            printAndJournalize("La ligne n'a pas pu être journalisée");
        }
        System.out.println(message);
    }
}
