import java.time.LocalDate;

public record Movie(String title, LocalDate releaseDate, String director, String genre, int ticketsSalesNumber) {
}
