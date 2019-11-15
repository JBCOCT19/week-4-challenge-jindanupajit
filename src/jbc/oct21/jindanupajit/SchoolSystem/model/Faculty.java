package jbc.oct21.jindanupajit.SchoolSystem.model;

public class Faculty extends Person {
    public Faculty() {
    }

    public Faculty(Person person) {
        super(person);
    }

    public Faculty(long id, String name, String email, String password) {
        super(id, name, email, password);
    }

    @Override
    public String toString() {
        return "Faculty "+super.toString();
    }
}
