package reports;

import inventory.BookInventory;
import book.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryReport {
    private BookInventory inventory;

    public InventoryReport(BookInventory inventory) {
        this.inventory = inventory;
    }

    public void report() {
        List<String> authors = new ArrayList<>();
        List<Book> books = inventory.getBooks();

        System.out.println("\nRapport d'inventaire\n");
        System.out.println("Nombre de livres : " + books.size());
        System.out.println("Auteurs et nombres de livres : ");
        books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.counting()))
                .forEach((key, value) -> System.out.println("  - " + key + " : " + value));
    }
}