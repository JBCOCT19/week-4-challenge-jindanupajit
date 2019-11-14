package jbc.oct21.jindanupajit.model;

import jbc.oct21.jindanupajit.helper.AutoIncrement;

public class Faculty extends Person {
    public Faculty() {
    }

    public Faculty(Person person) {
        super(person);
    }

    public Faculty(long id, String name, String email, String password) {
        super(id, name, email, password);
    }

    public static long getAutoId() {
        return AutoIncrement.getFacultyId();
    }

    @Override
    public String toString() {
        return "Faculty "+super.toString();
    }
}
