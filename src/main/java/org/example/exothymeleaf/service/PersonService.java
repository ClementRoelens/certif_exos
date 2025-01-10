package org.example.exothymeleaf.service;

import org.example.exothymeleaf.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonService {
    private final List<Person> persons;

    public PersonService() {
        persons = new ArrayList<>();
        persons.add(new Person("Clément","Roelens",33));
        persons.add(new Person("Nassim","Sakhri",28));
        persons.add(new Person("Olivia","Pigani",29));
    }

    public List<Person> getPersons() {
        return persons;
    }
}
