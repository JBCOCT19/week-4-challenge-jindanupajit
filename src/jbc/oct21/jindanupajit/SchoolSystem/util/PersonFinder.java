package jbc.oct21.jindanupajit.SchoolSystem.util;

import jbc.oct21.jindanupajit.SchoolSystem.model.Person;

import java.util.List;
import java.util.Optional;

public class PersonFinder extends Finder<Person> {

    public PersonFinder(List<? extends Person> database) {
        super(database);
    }

    public Optional<Person> findById(long id) {
        for (Person record : super.getDatabase()) {
            if (record.getId() == id)
                return Optional.of(record);
        }

        return Optional.empty();
    }

    public Optional<Person> findByName(String name) {
        for (Person record : super.getDatabase()) {
            if (record.getName().equals(name))
                return Optional.of(record);
        }

        return Optional.empty();
    }

    public Optional<Person> findByEmail(String email) {
        for (Person record : super.getDatabase()) {
            if (record.getEmail().equals(email))
                return Optional.of(record);
        }

        return Optional.empty();
    }

    public Optional<Person> findByCredential(String email, String password ) {
        for (Person record : super.getDatabase()) {
            if (record.getEmail().equals(email)) {
                if (record.getPassword().equals(password))
                    return Optional.of(record);
                else
                    break;
            }
        }

        return Optional.empty();
    }

}
