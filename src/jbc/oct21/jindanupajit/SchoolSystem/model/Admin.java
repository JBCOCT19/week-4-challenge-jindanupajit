package jbc.oct21.jindanupajit.SchoolSystem.model;

public class Admin extends Person {
    public Admin() {
    }

    public Admin(Person person) {
        super(person);
    }

    public Admin(long id, String name, String email, String password) {
        super(id, name, email, password);
    }

    @Override
    public String toString() {
        return "Admin "+super.toString();
    }
}
