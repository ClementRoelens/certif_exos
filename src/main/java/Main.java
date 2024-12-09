import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Map<String, Calculator> operations = new HashMap<>();
        operations.put("add", (x,y) -> x + y);
        operations.put("sub", (x,y) -> x - y);
        operations.put("multiply", (x,y) -> x * y);
        operations.put("divide", (x,y) -> x / y);
        Consumer<String> ihm = System.out::println;
        Scanner IRM = new Scanner(System.in);
        int choice = 0, continueChoice;
        double firstInput, secondInput, result;
        Supplier<Integer> receiveIntegerInput = () -> {
            while (true){
                try {
                    return IRM.nextInt();
                } catch (InputMismatchException e){
                    System.out.println("Entrez un nombre entier");
                } finally {
                    IRM.nextLine();
                }
            }
        };
        Supplier<Double> receiveDoubleInput = () -> {
            while (true){
                try {
                    return IRM.nextDouble();
                } catch (InputMismatchException e){
                    System.out.println("Entrez une valeur numérique avec une virgule pour les décimales");
                } finally {
                    IRM.nextLine();
                }
            }
        };


        ihm.accept("Bienvenue dans Calculatrice 2000 +");

        while (true){
            while (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
                ihm.accept("""
                               
                Choisissez l'opération à effectuer
                1 - Addition
                2 - Soustraction
                3 - Multiplication
                4 - Division
                """);
                choice = receiveIntegerInput.get();
            }

            ihm.accept("Entrez la première valeur");
            firstInput = receiveDoubleInput.get();
            ihm.accept("Entrez la seconde valeur");
            secondInput = receiveDoubleInput.get();

            result = switch (choice) {
                case 1 -> operations.get("add").calculate(firstInput, secondInput);
                case 2 -> operations.get("sub").calculate(firstInput, secondInput);
                case 3 -> operations.get("multiply").calculate(firstInput, secondInput);
                case 4 -> operations.get("divide").calculate(firstInput, secondInput);
                default -> 0;
            };

            ihm.accept(String.valueOf(result));

            ihm.accept("Appuyez sur 0 pour arrêter. Sinon, vous ferez un nouveau calcul");
            continueChoice = receiveIntegerInput.get();

            if (continueChoice == 0){
                break;
            }
            choice = 0;
        }


    }
}
