import java.time.LocalDate;

public record Book(
        String title,
        String author,
        String genre,
        LocalDate datePublication,
        int nbPages,
        boolean estDisponible,
        double prix) {
}
