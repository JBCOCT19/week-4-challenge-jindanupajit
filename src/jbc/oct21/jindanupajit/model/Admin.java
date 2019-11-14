package jbc.oct21.jindanupajit.model;

import jbc.oct21.jindanupajit.helper.AutoIncrement;

public class Admin extends Person {
    public Admin() {
    }

    public Admin(Person person) {
        super(person);
    }

    public Admin(long id, String name, String email, String password) {
        super(id, name, email, password);
    }

    public static long getAutoId() {
        return AutoIncrement.getAdminId();
    }

    @Override
    public String toString() {
        return "Admin "+super.toString();
    }
}
