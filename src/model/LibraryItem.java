package model;

public abstract sealed class LibraryItem permits Book, Magazine {
    protected int id;
    protected String title;
    protected int publicationYear;
    private static int count = 1;

    public LibraryItem(String title, int publicationYear) {
        this.id = count++;
        this.title = title;
        this.publicationYear = publicationYear;
    }

    public abstract String getDetails();

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }
}
