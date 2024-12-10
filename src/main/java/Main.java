import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner IRM = new Scanner(System.in);

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
        List<Movie> movies = new ArrayList<>();
        // Juste pour afficher les entrées de façon lisible
        NumberFormat formatter = NumberFormat.getInstance(Locale.FRANCE);

        try (BufferedReader reader = new BufferedReader(
                new FileReader("C:\\Users\\Administrateur\\Documents\\Exos\\exo-stream-films\\src\\main\\resources\\films_with_genres 1.csv"))) {
            movies = reader.lines()
                    .skip(1)
                    .map(movie -> {
                        String[] fields = movie.split(",");
                        return new Movie(
                                fields[0],
                                LocalDate.parse(fields[1]),
                                fields[2],
                                fields[3],
                                Integer.parseInt(fields[4]));
                    })
                    .toList();
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("\nAppuyez sur Entrée pour afficher la réponse de chaque énoncé");

        // 1 - Affichage du titre
        System.out.println("\nCombien des premiers films vous voulez voir ?");
        int nbChoice = scanInt();
        movies.stream()
                .limit(nbChoice)
                .map(Movie::title)
                .forEach(System.out::println);

        // 2 - Filtrer
        System.out.println("\nDe quel genre voulez-vous voir les films ainsi que leur année de sortie ?");
        movies.stream()
                .collect(Collectors.groupingBy(Movie::genre))
                .keySet()
                .forEach(System.out::println);
        String genreChoice = IRM.nextLine();
        movies.stream()
                .filter(m -> m.genre().equalsIgnoreCase(genreChoice))
                .forEach(m -> System.out.println(m.title() + " - " + m.releaseDate().getYear()));

        System.out.println("\nÀ partir de quelle année voulez-vous voir les films ?");
        final int year = scanInt();
        movies.stream()
                .filter(m -> m.releaseDate().getYear() >= year)
                .map(Movie::title)
                .forEach(System.out::println);

        System.out.println("\nDe quel réalisateur voulez-vous voir les films ?");
        movies.stream()
                .collect(Collectors.groupingBy(Movie::director))
                .keySet()
                .forEach(System.out::println);
        final String director = IRM.nextLine();
        movies.stream()
                .filter(m -> m.director().equalsIgnoreCase(director))
                .map(Movie::title)
                .forEach(System.out::println);

        //3 - Tri et limitation
        System.out.println("\nCombien des plus gros succès voulez-vous voir ?");
        nbChoice = scanInt();
        movies.stream()
                .sorted(Comparator.comparingInt(Movie::ticketsSalesNumber).reversed())
                .limit(nbChoice)
                .forEach(m -> {
                    System.out.println(m.title() + " - " + formatter.format(m.ticketsSalesNumber()));
                });

        System.out.println("\nVoici les films du plus vieux au plus récent\n");
        IRM.nextLine();
        movies.stream()
                .sorted(Comparator.comparing(Movie::releaseDate))
                .forEach(m -> {
                    System.out.println(m.title() + " - " + m.releaseDate());
                });

        System.out.println("\nCombien des moins gros succès voulez-vous voir ?");
        nbChoice = scanInt();
        movies.stream()
                .sorted(Comparator.comparingInt(Movie::ticketsSalesNumber))
                .limit(nbChoice)
                .forEach(m -> {
                    System.out.println(m.title() + " - " + formatter.format(m.ticketsSalesNumber()));
                });

        //4 - Regroupement
        System.out.println("\nVoici le nombre de films dans chaque genre\n");
        IRM.nextLine();
        movies.stream()
                .collect(Collectors.groupingBy(Movie::genre))
                .forEach((key, value) -> {
                    System.out.println(key + " - " + value.size() + " films");
                });

        System.out.println("\nVoici la liste des films par réalisateur\n");
        IRM.nextLine();
        movies.stream()
                .collect(Collectors.groupingBy(Movie::director))
                .forEach((key, value) -> {
                    System.out.println("\nFilms de " + key);
                    value.forEach(v -> System.out.println("    " + v.title()));
                });

        // 5 - Calculs
        System.out.println("\nVoici le nombre total d'entrées pour tous les films\n");
        IRM.nextLine();
        System.out.println(formatter.format(movies.stream()
                .mapToLong(Movie::ticketsSalesNumber)
                .sum())
        );

        System.out.println("\nVoici le genre avec le plus grand nombre d'entrées\n");
        IRM.nextLine();
        movies.stream()
                .collect(Collectors.groupingBy(
                        Movie::genre,
                        Collectors.summingLong(Movie::ticketsSalesNumber)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(e -> System.out.println(e.getKey() + " - " + formatter.format(e.getValue()) + " entrées"));

        System.out.println("\nDe quel réalisateur voulez-vous voir la moyenne des entrées ?\n");
        movies.stream()
                .collect(Collectors.groupingBy(Movie::director))
                .keySet()
                .forEach(System.out::println);
        final String directorAverage = IRM.nextLine();
        movies.stream()
                .filter(m -> m.director().equalsIgnoreCase(directorAverage))
                .mapToInt(Movie::ticketsSalesNumber)
                .average()
                .ifPresent(res -> {
                    System.out.println(formatter.format(res)+ "\n");
                });

        // 6 - Transformation
        System.out.println("Voici les films affichés sous le modèle  \"Titre (Genre) - Réalisé par Réalisateur en Année\"\n");
        IRM.nextLine();
        movies.forEach(m -> System.out.printf(
                "%s (%s) - Réalisé par %s en %s \n",
                m.title(),m.genre(),m.director(), m.releaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        System.out.println("\nLes genres présents sont :\n");
        IRM.nextLine();
        movies.stream()
                .collect(Collectors.groupingBy(Movie::genre))
                .keySet()
                .forEach(System.out::println);

        // 7 - Avancées

        System.out.println("\nVoici les plus vieux films de chaque genre\n");
        IRM.nextLine();
        movies.stream()
                .collect(Collectors.groupingBy(Movie::genre))
                .forEach((key, value) -> {
                    value.stream()
                            .min(Comparator.comparing(Movie::releaseDate))
                            .ifPresent(m ->
                                    System.out.println("Le plus vieux film du genre " + key + " est " + m.title() +
                                            " sorti en " + m.releaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                });

        System.out.println("\nÀ partir de combien de films réalisés voulez-vous voir les réalisateurs ?");
        final int directedLimit = scanInt();
        movies.stream()
                .collect(Collectors.groupingBy(Movie::director))
                .entrySet().stream()
                .filter(e -> e.getValue().size() > directedLimit)
                .forEach(e -> {
                    System.out.println(e.getKey() + " - " + e.getValue().size() + " films");
                });

        System.out.println("\nÀ partir de quelle année voulez-vous compter les entrées ?");
        final int beginYear = scanInt();
        System.out.println("Jusqu'à quelle année voulez-vous compter les entrées ?");
        final int endYear = scanInt();
        System.out.println(formatter.format(movies.stream()
                .filter(m -> m.releaseDate().getYear() >= beginYear && m.releaseDate().getYear() <= endYear)
                .mapToLong(Movie::ticketsSalesNumber)
                .sum()));
    }
}
