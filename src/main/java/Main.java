import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Journal journal = new Journal("journal.txt", "journal_backup.dat");
        Scanner IRM = new Scanner(System.in);
        int choice = 0;

        while (choice != 5){
            while (choice == 0){
                System.out.print("""
                --- Menu ---
                1. Ajouter une activité
                2. Afficher les activités
                3. Sauvegarder en binaire
                4. Lire le fichier binaire
                5. Quitter
                Choisissez une option :
                """);
                try {
                    choice = IRM.nextInt();
                } catch (InputMismatchException e){
                    System.out.println("Saisissez un des nombres proposés");
                } finally {
                    IRM.nextLine();
                }
            }

            switch (choice){
                case 1 -> {
                    try {
                        System.out.println("Voici des exemples d'activités :");
                        for (String example : ActivitiesProvider.getActivities()){
                            System.out.println(" - " + example);
                        }
                    } catch (IOException e) {
                        System.err.println("Impossible de lire les exemples d'activités" + e.getMessage());
                    }
                    System.out.print("Entrez une description de l'activité : ");
                    try {
                        journal.addActivity(IRM.nextLine());
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("""
                        --- Journal des activités ---
                        """);
                    try {
                        System.out.println(journal.getActivities());
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        journal.saveBinary();
                        System.out.println("La sauvegarde a été faite dans : " + journal.getBinaryPath());
                    } catch (IOException e) {
                        System.err.println("La sauvegarde n'a pas pu être faite\n" + e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        System.out.println("--- Contenu du fichier binaire ---");
                        System.out.println(journal.readBinary());
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }

            if (choice != 5){
                choice = 0;
            }
        }
    }
}
