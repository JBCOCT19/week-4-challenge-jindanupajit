package jbc.oct21.jindanupajit.model;

import jbc.oct21.jindanupajit.helper.AutoIncrement;

public class Student extends Person {
    public Student() {
    }

    public Student(Person person) {
        super(person);
    }

    public Student(long id, String name, String email, String password) {
        super(id, name, email, password);
    }


    public static long getAutoId() {
        return AutoIncrement.getPersonId();
    }

    @Override
    public String toString() {
        return "Student "+super.toString();
    }
}
