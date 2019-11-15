package jbc.oct21.jindanupajit.SchoolSystem.model;

public class Student extends Person {
    public Student() {
    }

    public Student(Person person) {
        super(person);
    }

    public Student(long id, String name, String email, String password) {
        super(id, name, email, password);
    }

    @Override
    public String toString() {
        return "Student "+super.toString();
    }
}
