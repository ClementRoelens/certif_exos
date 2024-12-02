import contacts.Contact;
import contacts.Directory;
import java.lang.System.*;
import java.util.Scanner;
import java.util.InputMismatchException;

class IHM {
    Scanner scanner = new Scanner(System.in);
    int choice;
    boolean isChoiceCorrect;
    Directory directory = new Directory();

    public void run(){
        choice = 0;
        isChoiceCorrect = false;

        System.out.println(
            "1. Ajouter un contact\n2. Lister les contacts\n3. Quitter");

            while (!isChoiceCorrect){
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice != 1 && choice != 2 && choice != 3){
                        System.out.println("Répondez par 1, 2 ou 3");
                    }
                    else {
                        isChoiceCorrect = true;
                    }
                } catch (InputMismatchException e){
                    System.out.println("Répondez par 1, 2 ou 3");
                }
            }

        switch (choice){
            case 1 -> addContact();
            case 2-> printContacts();
            default -> System.out.println("Au revoir");
        }
    }

    private void addContact(){
        String name;
        String phoneNumber;

        System.out.println("Entrez le nom du contact");
        name = scanner.nextLine();
        System.out.println("Entrez le numéro de téléphone du contact");
        phoneNumber = scanner.nextLine();

        Contact contact = new Contact(name,phoneNumber);
        directory.addContact(contact);

        run();
    }

    private void printContacts(){
        for (Contact contact : directory.getContacts()){
            System.out.println(contact.getName() + " : " + contact.getPhoneNumber());
        }
        run();
    }
}