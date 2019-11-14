package jbc.oct21.jindanupajit.helper;

import com.sun.deploy.security.SelectableSecurityManager;
import jbc.oct21.jindanupajit.model.Course;
import jbc.oct21.jindanupajit.model.Person;

import java.util.Optional;
import java.util.Scanner;

public class Console {
    private static Scanner scanner = new Scanner(System.in);

    private static Person currentUser;

    public static String ask(String prompt) {
        return ask(prompt,null);
    }
    public static String ask(String prompt, String current) {
        if (current == null) {
            System.out.print(prompt);
            return scanner.nextLine();
        }

        System.out.printf(prompt, current);
        String input = scanner.nextLine();

        if (input.equals(""))
            return current;
        else
            return input;
    }

    public static boolean YesNo(String prompt) {
        while(true) {
            switch (ask(prompt+"(Y/N) : ").toLowerCase()) {
                case "y":
                case "yes":
                    return true;
                case "n":
                case "no":
                    return false;
                default:
                    System.out.println("Please answer 'yes' or 'no'.\n");
            }
        }
    }

    public static Optional<Person> login(PersonCrudRepository databaseQuery) {
        String email = Console.ask("Please enter in your email [%s] : ", "admin@gmail.com");
        String password = Console.ask("Please enter in your password [%s] : ", "password");

        Optional<Person> person = databaseQuery.findByCredential(email, password);

        return person;
    }

    public static String menuAdmin() {
       String input =
       ask(String.format("\nOptions:\n" +
                "  1 - Add Student\n" +
                "  2 - Add Faculty\n" +
                "  3 - Edit Student\n" +
                "  4 - Edit Faculty\n" +
                "  5 - Add Class\n" +
                "  6 - Edit Class\n" +
                "  7 - Enroll Student\n" +
                "  8 - Assign Faculty\n" +
                "  9 - View All Information\n\n" +
                "  Q - Quit\n%s as %s> ",currentUser.getName(),currentUser.getClass().getSimpleName()));
       while(true) {
           switch (input.toLowerCase()) {
               case "1":
               case "2":
               case "3":
               case "4":
               case "5":
               case "6":
               case "7":
               case "8":
               case "9":
               case "q":
                   return input;
               case "quit":
                   return "q";
               default:
                   System.out.println("Not the right choice, try again\n\n");
           }
       }
    }

    public static Person getPersonInformation() {
        return getPersonInformation(null);
    }

    public static Person getPersonInformation(Person person) {
        Person current;

        if (person == null)
            current = new Person(0,"","","password");
        else
            current = new Person(person);

        String name = ask("  Name [%s] : ", current.getName());
        String email = ask("  Email [%s] : ", current.getEmail()).toLowerCase();
        String password = ask("  Password [%s] : ", current.getPassword());

        return new Person(0, name, email, password);
    }

    public static Course getCourseInformation() {
        return getCourseInformation(null);
    }

    public static Course getCourseInformation(Course course) {
        Course current;

        if (course == null)
            current = new Course(0, "", "");
        else
            current = new Course(course);

        String name = ask("  Name [%s] : ", current.getName());
        String description = ask("  Description [%s] : ", current.getDescription());

        return new Course(0, name, description);

    }

    public static Person getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Person currentUser) {
        Console.currentUser = currentUser;
    }
}
