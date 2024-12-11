import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private final static Scanner IRM = new Scanner(System.in);
    
    private static int scanInt(){
        while (true){
            try {
                return IRM.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Entrez un nombre entier");
            } finally {
                IRM.nextLine();
            }
        }
    }
    
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        NumberFormat formatter = NumberFormat.getInstance(Locale.FRANCE);

        try (BufferedReader reader = new BufferedReader(
                new FileReader("src\\main\\resources\\books_dataset.csv"))){
            books = reader.lines()
                    .skip(1)
                    .map(line -> {
                        String[] fields = line.split(",");
                        return new Book(
                                fields[0],
                                fields[1],
                                fields[2],
                                LocalDate.parse(fields[3]),
                                Integer.parseInt(fields[4]),
                                Boolean.parseBoolean(fields[5]),
                                Double.parseDouble(fields[6])
                        );
                    })
                    .toList();
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("\nAppuyez sur n'importe quelle touche pour lancer chaque affichage quand rien n'est demandé");

        System.out.println("\nVoici les livres disponibles");
        IRM.nextLine();
        books.stream()
                .filter(Book::estDisponible)
                .forEach(System.out::println);


        System.out.println("\nAvant quelle année voulez-vous voir les titres des livres et les auteurs ?");
        final int yearChoice = scanInt();
        books.stream()
                .filter(b -> b.datePublication().getYear() < yearChoice)
                .forEach(b -> System.out.printf("%s - %s\n",b.title(),b.author()));


        System.out.println("\nVoici les livres regroupés par genre");
        IRM.nextLine();
        books.stream()
                .collect(Collectors.groupingBy(Book::genre))
                .forEach((key, value) -> {
                    System.out.println("\n           " + key);
                    value.forEach(v -> System.out.println(" - " + v.title()));
                });


        System.out.println("\nVoici le livre le plus ancien");
        IRM.nextLine();
        System.out.println(books.stream()
                .min(Comparator.comparing(Book::datePublication))
                .get()
        );


        System.out.println("\nQuel est le mot dont il faut trouver si au moins un livre commence par lui ?");
        final String wordChoice = IRM.nextLine();
        boolean isThereAnyBookStartingWithHarry = books.stream()
                .anyMatch(b -> b.title().toLowerCase().startsWith(wordChoice.toLowerCase()));
        System.out.println(isThereAnyBookStartingWithHarry ? "Oui" : "Non");


        System.out.println("\nVoici les livres triés par nombre de pages, et par prix en cas d'égalité");
        IRM.nextLine();
        books.stream()
                .sorted(Comparator.comparing(Book::prix))
                .sorted(Comparator.comparingInt(Book::nbPages))
                .forEach(book ->
                        System.out.printf("%s, %d pages, %.2f€\n",
                                book.title(),book.nbPages(),book.prix()));


        System.out.println("\nDe quel genre voulez-vous avoir le nombre total de page ?" +
                "\nGenres disponibles : ");
        books.stream()
                .collect(Collectors.groupingBy(Book::genre))
                .forEach((key, value) -> System.out.println("- " +key));
        final String genreChoice = IRM.nextLine();
        System.out.println(formatter.format(books.stream()
                        .filter(b -> b.genre().equalsIgnoreCase(genreChoice))
                        .mapToInt(Book::nbPages)
                        .sum()
                )
        );

        System.out.println("\nLe livre le plus cher à la vente est");
        IRM.nextLine();
        System.out.println(books.stream()
                .max(Comparator.comparing(Book::prix))
                .get()
        );

        System.out.println("\nÀ partir de combien de livres voulez-vous voir les auteurs et leurs livres ?");
        final int booksNumberChoice = scanInt();
        books.stream()
                .collect(Collectors.groupingBy(Book::author))
                .entrySet().stream()
                .filter(e -> e.getValue().size() >= booksNumberChoice)
                .forEach(e -> {
                    System.out.println("        Livres de " + e.getKey());
                    e.getValue().forEach(v -> System.out.println(v.title()));
                });

        System.out.println("\nVoici le nombre de livres dans chaque genre");
        IRM.nextLine();
        books.stream()
                .collect(Collectors.groupingBy(Book::genre, Collectors.counting()))
                .forEach((key, value) -> System.out.printf("%s : %d livres\n",
                        key, value));

        System.out.println("\nJusqu'à quel prix voulez-vous voir les livres ?");
        final int priceThreshold = scanInt();
        books.stream()
                .filter(b -> b.estDisponible() && b.prix() <= priceThreshold)
                .forEach(System.out::println);

        System.out.println("\nVoici le nombre de pages publiées chaque année");
        IRM.nextLine();
        books.stream()
                .collect(Collectors.groupingBy(
                        b -> b.datePublication().getYear(),
                        Collectors.summingInt(Book::nbPages)
                ))
                .forEach((key,value) -> System.out.printf("%d : %d pages\n", key, value));
    }
}