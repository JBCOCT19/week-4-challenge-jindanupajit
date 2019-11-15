package jbc.oct21.jindanupajit.SchoolSystem.model;


public class Person {
    private long id = 0;
    private String name = "";
    private String email = "";
    private String password = "";

    public Person() {
    }

    public Person(Person person) {
        this.id = person.id;
        this.name = person.name;
        this.email = person.email;
        this.password = person.password;
    }

    public Person(long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void importValue(Person person) {
        this.id = person.id;
        this.name = person.name;
        this.email = person.email;
        this.password = person.password;
    }

    @Override
    public String toString() {
        return  "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
