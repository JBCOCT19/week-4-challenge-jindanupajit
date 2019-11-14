package jbc.oct21.jindanupajit.helper;

import com.sun.istack.internal.NotNull;
import jbc.oct21.jindanupajit.model.Person;

import java.util.List;
import java.util.Optional;

public class PersonCrudRepository {
    private List<? extends Person> database = null;

    public PersonCrudRepository(@NotNull List<? extends Person> database) {
        this.database = database;
    }

    public Optional<Person> findById(long id) {
        for (Person record : database) {
            if (record.getId() == id)
                return Optional.of(record);
        }

        return Optional.empty();
    }

    public Optional<Person> findByName(String name) {
        for (Person record : database) {
            if (record.getName().equals(name))
                return Optional.of(record);
        }

        return Optional.empty();
    }

    public Optional<Person> findByEmail(String email) {
        for (Person record : database) {
            if (record.getEmail().equals(email))
                return Optional.of(record);
        }

        return Optional.empty();
    }

    public Optional<Person> findByCredential(String email, String password ) {
        for (Person record : database) {
            if (record.getEmail().equals(email)) {
                if (record.getPassword().equals(password))
                    return Optional.of(record);
                else
                    break;
            }
        }

        return Optional.empty();
    }

    public List<? extends Person> getDatabase() {
        return database;
    }
}
