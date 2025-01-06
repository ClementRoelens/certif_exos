package app;

import book.Book;
import inventory.BookInventory;
import checkout.CheckoutSystem;
import notification.service.NotificationService;
import email.notification.EmailNotification;
import reports.InventoryReport;

public class LibraryApp {
    public static void main(String[] args) {
        BookInventory bookInventory = new BookInventory();
        bookInventory.addBook(new Book(1,"Java pour les nuls", "Christophe Delory"));
        bookInventory.addBook(new Book(2,"React Native : un guide vers la souffrance", "Christophe Delory"));
        bookInventory.addBook(new Book(3,"Le tir à l'arc ou la douleur dans l'épaule", "Clément Roelens"));
        bookInventory.addBook(new Book(4,"Résoudre un Rubik's Cube en décollant et recollant les stickers", "Clément Roelens"));
        bookInventory.addBook(new Book(5,"Metroid : du pire au meilleur", "Clément Roelens"));
        bookInventory.addBook(new Book(6, "C'est la pause", "Nassim Sakhri"));

        CheckoutSystem checkoutSystem = new CheckoutSystem(bookInventory);
        NotificationService notificationService = new EmailNotification();
        InventoryReport report = new InventoryReport(bookInventory);
        
        report.report();        
        
        System.out.println("\nNotre client veut emprunter \"Java pour les nuls\"");
        notificationService.notify(
            checkoutSystem.borrowBook("Java pour les nuls")
        );
        
        System.out.println("Notre client veut emprunter \"Livre inexistant\"");
        notificationService.notify(
                checkoutSystem.borrowBook("Livre inexistant")
        );
        
        System.out.println("Notre client veut retourner \"Java pour les nuls\"");
        notificationService.notify(
                checkoutSystem.returnBook("java pour les nuls")
        );
        
        System.out.println("Notre client veut retourner \"Résoudre un Rubik's Cube en décollant et recollant les stickers\"");
        notificationService.notify(
                checkoutSystem.returnBook("Résoudre un Rubik's Cube en décollant et recollant les stickers")
        );
        
        System.out.println("Notre client nous a fait remarquer qu'on devrait supprimer ce livre car il n'est pas très sérieux");
        notificationService.notify(
            bookInventory.removeBook("Résoudre un Rubik's Cube en décollant et recollant les stickers")
        );

        System.out.println("Notre client nous conseille de faire pareil avec \"Livre horrible inexistant\"");
        notificationService.notify(
                bookInventory.removeBook("Livre horrible inexistant")
        );
        
        report.report();
    }
}