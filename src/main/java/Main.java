import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Command> commands = Arrays.asList(
                new Command(1, "Alice", Arrays.asList("Ordinateur", "Souris"), 1200.50, true),
                new Command(2, "Bob", Arrays.asList("Clavier", "Écran"), 300.75, false),
                new Command(3, "Charlie", Arrays.asList("Imprimante"), 150.00, true),
                new Command(4, "Alice", Arrays.asList("USB", "Casque"), 75.50, false),
                new Command(5, "Bob", Arrays.asList("Tablette"), 200.00, true)
        );

        System.out.println("\nCommandes\n");
        commands.forEach(System.out::println);

        System.out.println("\nNom des clients de chaque commande\n");
        commands.stream()
                .map(Command::getClient)
                .forEach(System.out::println);

        System.out.println("\nMontant total des commandes\n" +
                commands.stream()
                        .mapToDouble(Command::getMontantTotal)
                        .sum());

        System.out.println("\nArticles et prix total de chaque commande\n");
        commands.forEach(c -> System.out.println(c.getArticles() + " pour " + c.getMontantTotal() +"€"));

        System.out.println("\nCommandes livrées\n");
        commands.stream()
                .filter(Command::isEstLivree)
                .forEach(System.out::println);

        System.out.println("\nMontant total par client :\n");
        commands.stream()
                .collect(Collectors.groupingBy(
                        Command::getClient,
                        Collectors.summingDouble(Command::getMontantTotal)
                ))
                .forEach((key, value) ->
                        System.out.printf("Montant total pour %s : %,.2f€\n",
                        key, value));

        System.out.println("\nArticles\n");
        commands.stream()
                .map(c -> new HashSet<>(c.getArticles()))
                .forEach(System.out::println);

        System.out.println("\nEst-ce que tous les clients ont une commande livrée?\n");
        boolean haveAllClientsOneDeliveredCommand = commands.stream()
                .collect(Collectors.groupingBy(Command::getClient))
                .values().stream()
                    .allMatch(l ->
                            l.stream()
                                    .anyMatch(Command::isEstLivree));
        System.out.println(haveAllClientsOneDeliveredCommand ? "Oui" : "Non");

    }
}
