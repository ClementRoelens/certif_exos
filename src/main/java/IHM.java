import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class IHM {
    Console console;
    Library library;

    public IHM(){
        console = System.console();
        library = new Library();
    }

    public void menu(){
        int choice;

        console.printf("""
                
                1 - Ajouter un livre
                2 - Afficher la liste des livres
                3 - Enregistrer un livre
                4 - Afficher les livres enregistrés
                5 - Quitter
                """);

       choice = ensureReadInt();

        switch (choice) {
            case 1 -> addBook();
            case 2 -> showBooks();
            case 3 -> serializeBook();
            case 4 -> deserializeBook();
            default -> console.printf("Au revoir");
        }

        if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
            menu();
        }
    }

    private void addBook(){
        String name, author;

        console.printf("Entrez le nom du livre\n");
        name = console.readLine();
        console.printf("Entrez l'auteur du livre\n");
        author = console.readLine();

        library.addBook(new Book(name, author));

        console.printf("Livre ajouté à la bibliothèque\n");
    }

    private void showBooks(){
        List<Book> books = library.getBooks();

        console.printf("Liste des livres\n\n");
        for (int i = 0; i < books.size(); i++ ){
            console.printf("%d - %s\n", i+1, books.get(i));
        }
    }

    private void serializeBook(){
        int choice;
        List<Book> books = library.getBooks();

        console.printf("Quel livre voulez-vous enregistrer ?\n");
        showBooks();
        choice = ensureReadInt();

        try {
            SerialUtils.serialize(String.format("book-%d.ser", SerialUtils.getSerialNo()), books.get(choice-1));
            console.printf("Enregistrement réussi");
        } catch (IOException e) {
            console.printf("Le livre n'a pas pu être sérialisé : \n%s", e.getMessage());
        }
    }

    private void deserializeBook(){
        int i = 1;

        while (true){
            File file = new File("book-" + i + ".ser");
            if (!file.exists()){
                return;
            } else {
                try {
                    Book book = (Book) SerialUtils.deserialize(file.getPath());
                    console.printf("%s : %s\n", file.getPath(), book.toString());
                } catch (Exception e) {
                    console.printf("Le livre %d n'a pas pu être affiché : \n%s", i, e.getMessage());
                }
            }

            i++;
        }
    }


    private int ensureReadInt(){
        while (true){
            try {
                return Integer.parseInt(console.readLine());
            } catch (NumberFormatException e) {
                console.printf("Entrez un nombre entier)\n");
            } finally {
                console.printf("\n");
            }
        }
    }
}
