package model;

public final class Book extends LibraryItem {
    private String author;
    private String genre;

    public Book( String title, int publicationYear, String author, String genre) {
        super(title, publicationYear);
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String getDetails() {
        return String.format("Titre : %s, Auteur : %s, Genre : %s, Année : %d",
                title, author, genre, publicationYear);
    }
}
