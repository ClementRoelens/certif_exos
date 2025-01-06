package inventory;

import java.util.ArrayList;
import java.util.List;
import book.Book;

public class BookInventory {
    private final List<Book> books;

    public BookInventory() {
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public String removeBook(String title) {
        Book book = getBook(title);

        if (book != null) {
            return "Ce livre n'existe pas";
        }

        books.remove(book);
        return "\"title "+"\" a été supprimé";
    }

    public Book getBook(String title) {
        return books.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }
}
