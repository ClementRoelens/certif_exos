import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner IRM = new Scanner(System.in);
        String input;
        boolean isInputCorrect = false;

        // 1
        while (!isInputCorrect){
            System.out.println("Entrez un nombre entier");
            input = IRM.nextLine();
            try {
                Integer.parseInt(input);
                isInputCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println("Vous n'avez pas entré un nombre entier.\nRecommencez");
            }
        }

        // 2
        double inputValue;
        double squareRoot;
        isInputCorrect = false;
        while (!isInputCorrect){
            System.out.println("Entrez un nombre pour calculer sa racine carrée");
            input = IRM.nextLine();
            try {
                inputValue = Double.parseDouble(input);
                if (inputValue <= 0) {
                    throw new NegativeSquareException();
                }
                squareRoot = Math.sqrt(inputValue);
                isInputCorrect = true;
                System.out.println("La racine carrée de " + inputValue + " est " + squareRoot);
            } catch (NumberFormatException e) {
                System.out.println("Vous n'avez pas entré un nombre");
            } catch (NegativeSquareException e){
                System.out.println(e.getMessage());
            }
        }

        // 3

        int[] intArray = new int[]{1,2,3,4,5};
        try {
            System.out.println("Le 5ème nombre est : " + intArray[4]);
            System.out.println("Le 6ème nombre est : " + intArray[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("N'essayez pas d'accéder à un élément hors de la limite d'un tableau");
        }

        // 4
        final List<Student> students = new ArrayList<>();
        String name, choice;
        int age = 0;

        System.out.println("\nLa liste des étudiants est vide ! Ajoutez-en !");
        while (true){
            isInputCorrect = false;
            System.out.println("Entrez le nom de l'étudiant");
            name = IRM.nextLine();
            while (!isInputCorrect){
                System.out.println("Entrez l'âge de l'étudiant");
                try {
                    age = IRM.nextInt();
                    if (age <= 0) {
                        throw new InvalidAgeException();
                    }
                    isInputCorrect = true;
                } catch (InputMismatchException e){
                    System.out.println("L'âge doit être un nombre entier");
                } catch (InvalidAgeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    IRM.nextLine();
                }
            }
            students.add(new Student(name,age));

            System.out.println("\nLes étudiants sont : ");
            students.forEach(System.out::println);

            System.out.println("\nAjouter un étudiant ? o pour oui");
            choice = IRM.nextLine();

            if (!choice.equalsIgnoreCase("o")){
                return;
            }
        }
    }
}
