import model.Book;
import model.LibraryItem;
import model.Magazine;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        System.out.println("Ajout des éléments à la bibliothèque");
        library.addItem(new Book("Effective Java",2018,"Joshua Bloch","Programmation"));
        library.addItem(new Magazine("National Geographic",2023,42));

        System.out.println("Liste des éléments disponibles : ");
        listItems(library.getItems());

        System.out.println("Emprunt d'un élément...");
        library.borrowItem(library.getItems().get(1), "John Doe");

        System.out.println("Liste des éléments empruntés :  ");
        listItems(library.getBorrowedItems());

        System.out.println("Liste des éléments disponibles : ");
        listItems(library.getAvailableItems());

    }

    private static void listItems(List<LibraryItem> items){
        for (LibraryItem item : items){
            if (item instanceof Book){
                System.out.print("Livre : ");
            } else {
                System.out.print("Magazine : ");
            }
            System.out.println(item.getDetails());
        }
    }
}