package contacts;

import java.util.List;
import java.util.ArrayList;

public class Directory {
    private List<Contact> contacts;

    public Directory(){
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact){
        contacts.add(contact);
    }

    public List<Contact> getContacts(){
        return contacts;
    }
}