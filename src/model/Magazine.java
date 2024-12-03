package model;

public final class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(String title, int publicationYear, int issueNumber) {
        super(title, publicationYear);
        this.issueNumber = issueNumber;
    }

    @Override
    public String getDetails() {
        return String.format("Titre : %s, Numéro : %d, Année : %d",
                title, issueNumber, publicationYear);
    }
}
