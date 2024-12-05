import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        Display display = new Display();
        Writer writer = new Writer();
        int choice = 0;
        Scanner IRM = new Scanner(System.in);
        String message;

        eventManager.addObserver(writer);
        eventManager.addObserver(display);

        while (choice != 4){
            try {
                System.out.println("""
                        
                        1 - Entrer un message
                        2 - Ajouter un observer
                        3 - Retirer un observer
                        4 - Quitter
                        """);
                choice = IRM.nextInt();
                IRM.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.println("Entrez votre message");
                        message = IRM.nextLine();
                        eventManager.sendMessage(message);
                    }
                    case 2 -> {
                        int secondChoice = 0;
                        System.out.println("""
                                
                                1 - Ajouter l'observer console
                                2 - Ajouter l'observer fichier
                                
                                """);
                        try {
                            secondChoice = IRM.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Entrez un nombre entre 1 et 2");
                        } finally {
                            IRM.nextLine();
                        }
                        if (secondChoice == 1) {
                            eventManager.addObserver(display);
                        } else {
                            eventManager.addObserver(writer);
                        }
                    }
                    case 3 -> {
                        int secondChoice = 0;
                        System.out.println("""
                                
                                1 - Retirer l'observer console
                                2 - Retirer l'observer fichier
                                
                                """);
                        try {
                            secondChoice = IRM.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Entrez un nombre entre 1 et 2");
                        } finally {
                            IRM.nextLine();
                        }
                        if (secondChoice == 1) {
                            eventManager.removeObserver(display);
                        } else {
                            eventManager.removeObserver(writer);
                        }
                    }
                    case 4 -> {
                        System.out.println("\nMerci d'avoir utiliser notre service");
                        System.out.println("N'oubliez pas de nous laisser 5 étoiles sur CodeAdvisor\n");
                        return;
                    }
                }
            } catch (InputMismatchException e){
                System.out.println("Entrez un nombre entre 1 et 4");
                IRM.nextLine();
            }
        }
    }
}
