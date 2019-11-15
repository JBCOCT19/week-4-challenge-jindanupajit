package jbc.oct21.jindanupajit.SchoolSystem.view;

import com.sun.istack.internal.Nullable;
import jbc.oct21.jindanupajit.SchoolSystem.model.Course;
import jbc.oct21.jindanupajit.SchoolSystem.model.Person;
import jbc.oct21.jindanupajit.SchoolSystem.util.PersonFinder;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;


public class Terminal {

    private OutputStream outputStream = System.out;
    private OutputStream errorStream = System.out;
    private InputStream inputStream = System.in;

    private Scanner scanner;

    public Terminal() {
        scanner = new Scanner(inputStream);
    }

    public Terminal(OutputStream outputStream, InputStream inputStream) {
        this.outputStream = outputStream;
        this.errorStream = outputStream;
        this.inputStream = inputStream;
        scanner = new Scanner(inputStream);
    }

    private  Person currentUser;

    public   void init() {
        System.setErr(System.out);
    }

    public   void greeting() {

       println("Welcome to Coding 's School System\n");

        println("Instruction: \n" +
                "  when you see a prompt like\n\n" +
                "      Email [default@email.com] : \n\n" +
                "  you can hit enter for 'default' value " +
                "  in this case, it will return 'default@email.com' \n" +
                "  as an input value\n" +
                "  or you can enter a new value.\n\n");
    }
    /**
     * Resolve race condition between System.out and System.err
     * @param stream toPrint
     * @param messageObject message (as Object)
     */
    synchronized private void printOutOrError(OutputStream stream, Object messageObject)  {

        String message = messageObject.toString();

        BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(stream),1);
        try {
            for (int charIndex = 0; charIndex < message.length(); charIndex++) {
                StringBuilder line = new StringBuilder();
                 char c = message.charAt(charIndex);
                if ( c != '\n')
                    bufferedWriter.write(c);
                else
                    bufferedWriter.newLine();

                bufferedWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  void error(@Nullable Object message) {
        printOutOrError(errorStream, "*** "+message);

    }

    public   void errorln(@Nullable Object message) {
        error(message+"\n");
    }

    public   void print(@Nullable Object message) {
        printOutOrError(outputStream, message);
    }

    public   void println(@Nullable Object message) {
        print(message+"\n");
    }

    public   String ask(String prompt) {
        return ask(prompt,null);
    }
    public   String ask(String prompt, String current)  {
        if (current == null) {
            print(prompt);
            String input = scanner.nextLine().trim();
            //TODO: HERE!!!!
            inputStream.available()
        }

        print(String.format(prompt, current));
        String input = scanner.nextLine();

        if (input.equals(""))
            return current.trim();
        else
            return input.trim();
    }

    public   boolean YesNo(String prompt) {
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

    public   Optional<Person> login(PersonFinder databaseQuery) {
        String email = ask("Please enter in your email [%s] : ", "admin@gmail.com");
        String password = ask("Please enter in your password [%s] : ", "password");

        Optional<Person> person = databaseQuery.findByCredential(email, password);

        return person;
    }

    public   String menuAdmin() {
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
                   println("Not the right choice, try again\n\n");
           }
       }
    }

    public   Person getPersonInformation() {
        return getPersonInformation(null);
    }

    public   Person getPersonInformation(Person person) {
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

    public   Course getCourseInformation() {
        return getCourseInformation(null);
    }

    public   Course getCourseInformation(Course course) {
        Course current;

        if (course == null)
            current = new Course(0, "", "");
        else
            current = new Course(course);

        String name = ask("  Name [%s] : ", current.getName());
        String description = ask("  Description [%s] : ", current.getDescription());

        return new Course(0, name, description);

    }

    public   Person getCurrentUser() {
        return currentUser;
    }

    public   void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }
}
