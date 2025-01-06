package checkout;

import java.util.List;
import java.util.ArrayList;
import book.Book;
import inventory.BookInventory;

public class CheckoutSystem {
    private final List<Book> borrowedBooks;
    private final BookInventory bookInventory;

    public CheckoutSystem(BookInventory bookInventory) {
        this.borrowedBooks = new ArrayList<>();
        this.bookInventory = bookInventory;
    }

    public String borrowBook(String title) {
        Book book = bookInventory.getBook(title);

        if (book == null) {
            return "Ce livre n'est pas disponible";
        }

        if (borrowedBooks.stream().anyMatch(b -> b.getTitle().equals(title))) {
            return "Ce livre est déjà emprunté";
        }

        borrowedBooks.add(book);
        return "Emprunt enregistré";
    }

    public String returnBook(String title) {
        Book book = bookInventory.getBook(title);

        if (book == null) {
            return "Nous ne connaissons pas ce livre";
        }

        if (borrowedBooks.stream().noneMatch(b -> b.getTitle().equalsIgnoreCase(title))) {
            return "Ce livre n'était pas emprunté";
        }

        borrowedBooks.remove(book);
        return "Retour enregistré";
    }
}
