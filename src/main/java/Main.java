import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Movie> movies = new ArrayList<>();
        // Juste pour afficher les entrées de façon lisible
        NumberFormat formatter = NumberFormat.getInstance(Locale.FRANCE);
        Scanner IRM = new Scanner(System.in);

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
        System.out.println("\nVoici le titre des 10 premiers films\n");
        IRM.nextLine();
        movies.stream()
                .limit(10)
                .map(Movie::title)
                .forEach(System.out::println);

        // 2 - Filtrer
        System.out.println("\nVoici les films d'action et leur année de sortie\n");
        IRM.nextLine();
        movies.stream()
                .filter(m -> m.genre().equals("Action"))
                .forEach(m -> {
                    System.out.println(m.title() + " - " + m.releaseDate().getYear());
                });

        System.out.println("\nVoici les films sortis après 2000\n");
        IRM.nextLine();
        movies.stream()
                .filter(m -> m.releaseDate().getYear() > 2000)
                .map(Movie::title)
                .forEach(System.out::println);

        System.out.println("\nVoici les films de Michael Webster\n");
        IRM.nextLine();
        movies.stream()
                .filter(m -> m.director().equals("Michael Webster"))
                .map(Movie::title)
                .forEach(System.out::println);

        //3 - Tri et limitation
        System.out.println("\nVoici les 5 plus gros succès\n");
        IRM.nextLine();
        movies.stream()
                .sorted(Comparator.comparingInt(Movie::ticketsSalesNumber).reversed())
                .limit(5)
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

        System.out.println("\nVoici les 10 films avec le plus petit nombre d'entrée\n");
        IRM.nextLine();
        movies.stream()
                .sorted(Comparator.comparingInt(Movie::ticketsSalesNumber))
                .limit(10)
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
        Map<String, List<Movie>> moviesByGenre = movies.stream().collect(Collectors.groupingBy(Movie::genre));
        Map<String, Long> entriesCountByGenre = new HashMap<>();
        moviesByGenre.forEach((key, value) -> {
            long entryCount = value.stream()
                    .mapToLong(Movie::ticketsSalesNumber).sum();
            entriesCountByGenre.put(key, entryCount);
        });
        entriesCountByGenre.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(e -> System.out.println(e.getKey() + " - " + formatter.format(e.getValue()) + " entrées"));

        System.out.println("\nLa moyenne d'entrée des films de Taylor Romero est :\n");
        IRM.nextLine();
        movies.stream()
                .filter(m -> m.director().equals("Taylor Romero"))
                .mapToInt(Movie::ticketsSalesNumber)
                .average()
                .ifPresent(res -> {
                    System.out.println(formatter.format(res)+ "\n");
                });

        // 6 - Transformation
        System.out.println("Voici les films affichés sous le modèle  \"Titre (Genre) - Réalisé par Réalisateur en Année\"");
        IRM.nextLine();
        movies.forEach(m -> System.out.printf(
                "%s (%s) - Réalisé par %s en %s \n",
                m.title(),m.genre(),m.director(), m.releaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        System.out.println("\nLes genres présents sont : \n");
        IRM.nextLine();
        List<String> uniqueGenres = moviesByGenre.keySet().stream()
                .toList();
        uniqueGenres.forEach(System.out::println);

        // 7 - Avancées

        System.out.println("\nVoici les plus vieux films de chaque genre\n");
        IRM.nextLine();
        moviesByGenre.forEach((key, value) -> {
            value.stream()
                    .min(Comparator.comparing(Movie::releaseDate))
                    .ifPresent(m ->
                            System.out.println("Le plus vieux film du genre " + key + " est " + m.title() +
                                    " sorti en " + m.releaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        });

        System.out.println("\nVoici les réalisateurs ayant réalisé plus de 50 films\n");
        IRM.nextLine();
        movies.stream()
                .collect(Collectors.groupingBy(Movie::director))
                .entrySet().stream()
                .filter(e -> e.getValue().size() > 50)
                .forEach(e -> {
                    System.out.println(e.getKey() + " - " + e.getValue().size() + " films");
                });

        System.out.println("\nVoici le nombre total d'entrées réalisées entre 1990 et 2000 (compris) :\n");
        IRM.nextLine();
        System.out.println(formatter.format(movies.stream()
                .filter(m -> m.releaseDate().getYear() >= 1990 && m.releaseDate().getYear() <= 2000)
                .mapToLong(Movie::ticketsSalesNumber)
                .sum()));
    }
}
