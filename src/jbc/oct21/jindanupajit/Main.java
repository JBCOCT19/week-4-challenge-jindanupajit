package jbc.oct21.jindanupajit;


import com.sun.deploy.security.SelectableSecurityManager;
import jbc.oct21.jindanupajit.helper.*;
import jbc.oct21.jindanupajit.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static jbc.oct21.jindanupajit.helper.Console.ask;

public class Main {

    public static ArrayList<Admin> adminDatabase = new ArrayList<>();
    public static ArrayList<Faculty> facultyDatabase = new ArrayList<>();
    public static ArrayList<Student> studentDatabase = new ArrayList<>();
    public static ArrayList<Course> courseDatabase = new ArrayList<>();

    public static ArrayList<PersonCourseRelation> enrollmentDatabase = new ArrayList<>();
    public static ArrayList<PersonCourseRelation> facultyAssignmentDatabase = new ArrayList<>();

    public static PersonCrudRepository adminQuery = new PersonCrudRepository(adminDatabase);
    public static PersonCrudRepository facultyQuery = new PersonCrudRepository(facultyDatabase);
    public static PersonCrudRepository studentQuery = new PersonCrudRepository(studentDatabase);
    public static CourseCrudRepository courseQuery = new CourseCrudRepository(courseDatabase);

    public static PersonCourseRelationCrudRepository enrollmentQuery = new PersonCourseRelationCrudRepository(enrollmentDatabase);
    public static PersonCourseRelationCrudRepository facultyAssignmentQuery = new PersonCourseRelationCrudRepository(facultyAssignmentDatabase);


    public static void main(String[] args) {
        System.out.println("Welcome to Coding 's School System\n");

        System.out.println("Instruction: \n" +
                "  when you see a prompt like\n\n" +
                "      Email [default@email.com] : \n\n" +
                "  you can hit enter for 'default' value " +
                "  in this case, it will return 'default@email.com' \n" +
                "  as an input value\n" +
                "  or you can enter a new value.\n\n");


        adminDatabase.add(new Admin(Admin.getAutoId(),"Krissada", "admin@gmail.com","password"));
        facultyDatabase.add(new Faculty(Faculty.getAutoId(),"Faculty 1", "faculty@gmail.com","password"));
        studentDatabase.add(new Student(Student.getAutoId(),"Student 1", "student@gmail.com","password"));

        courseDatabase.add(new Course(Course.getAutoId(), "java101", "Basic Java"));
        courseDatabase.add(new Course(Course.getAutoId(), "java102", "Java Boot Camp"));

        enrollmentDatabase.add(new PersonCourseRelation(AutoIncrement.getEnrollmentId(), 1, 1, (new Date()).toString()));

        facultyAssignmentDatabase.add(new PersonCourseRelation(AutoIncrement.getFacultyAssignmentId(), 1, 2));

        start:
        while(true) {
            while (!Console.YesNo("Would you like to login? ")) {
                doYouWantToQuit();

                System.out.println("If you do not want to quit, then login!\n");
            }

            System.out.println("Would you like to login as an admin, faculty, or student?");
            switch (ask("(A)dmin, (F)aculty, (S)tudent, or (Q)uit : ").toLowerCase()) {
                case "admin":
                case "a":
                    admin();
                    continue start;
                case "faculty":
                case "f":
                    //faculty();
                    System.out.println("Faculty login not implement yet!\n");
                    continue start;

                case "student":
                case "s":
                    //student();
                    System.out.println("Student login not implement yet!\n");
                    continue start;
                case "quit":
                case "q":
                    doYouWantToQuit();
                    System.out.println("Make-up your mind and try again!\n");
                    continue start;
                default:
                    doYouWantToQuit();
                    System.out.println("Then, pay attention to the question and try again!\n");
            }
        }
    }


    public static void admin() {
        Optional<Person> admin;

        admin = Console.login(adminQuery);

        if (!admin.isPresent()) {
            System.out.print("Wrong credential!");
            return;
        }

        System.out.println("\nYou logged in\n");
        Console.setCurrentUser(admin.get());
        while (true) {
            switch (Console.menuAdmin()) {
                case "q":
                    return;
                case "1": addStudent();
                    break;
                case "2": addFaculty();
                    break;
                case "3": editStudent();
                    break;
                case "4": editFaculty();
                    break;
                case "5": addCourse();
                    break;
                case "6": editCourse();
                    break;
                case "7": addEnrollment();
                    break;
                case "8": addFacultyAssignment();
                    break;
                case "9": viewAll();
                    break;
            }
        }
    }

    private static void viewAll() {

        viewAdmin(0);

        viewFaculty(0);

        viewStudent(0);

        viewCourse(0);

        viewFacultyAssignment(0);

        viewEnrollment(0);


    }

    //TODO: Admin
    private static void viewAdmin(long id) {
        if (id == 0)
            for (Admin admin : adminDatabase) {
                System.out.println(admin);
            }
        else
            System.out.println(adminQuery.findById(id));
    }



    //TODO: Faculty
    private static void viewFaculty(long id) {
        if (id == 0)
            for (Faculty faculty : facultyDatabase) {
                System.out.println(faculty);
            }
        else
            System.out.println(facultyQuery.findById(id).orElse(new Faculty()));
    }
    private static void addFaculty() {
        Faculty faculty = new Faculty(Console.getPersonInformation());

        Optional<Person> duplicate = facultyQuery.findByEmail(faculty.getEmail());
        if (duplicate.isPresent()) {
            System.out.println("Duplicated entry found (same email)");
            viewFaculty(duplicate.get().getId());
            return;
        }

        faculty.setId(Faculty.getAutoId());
        facultyDatabase.add(faculty);

        viewFaculty(faculty.getId());
    }

    private static void editFaculty() {
        viewFaculty(0);
        long id = Long.parseLong(Console.ask("Faculty Id : "));

        Optional<Person> current = facultyQuery.findById(id);

        if (!current.isPresent()) {
            System.out.println("Invalid id\n");
            return;
        }

        Faculty faculty = new Faculty(Console.getPersonInformation(current.get()));

        if (!faculty.getEmail().equalsIgnoreCase(current.get().getEmail())) {
            Optional<Person> duplicate = facultyQuery.findByEmail(faculty.getEmail());
            if (duplicate.isPresent()) {
                System.out.println("Duplicated entry found (same email)");
                viewFaculty(duplicate.get().getId());
                return;
            }
        }

        faculty.setId(current.get().getId());

        current.get().importValue(faculty);
        viewFaculty(faculty.getId());
    }

    //TODO: Student
    private static void viewStudent(long id) {
        if (id == 0)
            for (Student student : studentDatabase) {
                System.out.println(student);
            }
        else
            System.out.println(studentQuery.findById(id).orElse(new Student()));
    }
    private static void addStudent() {
        Student student = new Student(Console.getPersonInformation());

        Optional<Person> duplicate = studentQuery.findByEmail(student.getEmail());
        if (duplicate.isPresent()) {
            System.out.println("Duplicated entry found (same email)");
            viewStudent(duplicate.get().getId());
            return;
        }
        student.setId(Student.getAutoId());
        studentDatabase.add(student);
        viewStudent(student.getId());
    }

    private static void editStudent() {
        viewStudent(0);
        long id = Long.parseLong(Console.ask("Student Id : "));

        Optional<Person> current = studentQuery.findById(id);

        if (!current.isPresent()) {
            System.out.println("Invalid id\n");
            return;
        }

        Student student = new Student(Console.getPersonInformation(current.get()));

        if (!student.getEmail().equalsIgnoreCase(current.get().getEmail())) {
            Optional<Person> duplicate = studentQuery.findByEmail(student.getEmail());
            if (duplicate.isPresent()) {
                System.out.println("Duplicated entry found (same email)");
                viewStudent(duplicate.get().getId());
                return;
            }
        }


        student.setId(current.get().getId());

        current.get().importValue(student);
        viewStudent(student.getId());
    }

    //TODO: Course
    private static void viewCourse(long id) {
        if (id == 0)
            for (Course course : courseDatabase) {
                System.out.println(course);
            }
         else
            System.out.println(courseQuery.findById(id).orElse(new Course()));

    }

    private static void addCourse() {
        Course course = new Course(Console.getCourseInformation());

        course.setId(Course.getAutoId());

        courseDatabase.add(course);
        viewCourse(course.getId());
    }

    private static void editCourse() {
        viewCourse(0);
        long id = Long.parseLong(Console.ask("Course Id : "));

        Optional<Course> current = courseQuery.findById(id);

        if (!current.isPresent()) {
            System.out.println("Invalid id\n");
            return;
        }

       Course course = new Course(Console.getCourseInformation(current.get()));
       course.setId(current.get().getId());

       current.get().importValue(course);
        viewCourse(course.getId());
    }

    //TODO: Enrollment
    private static void viewEnrollment(long id) {
        if (id == 0)
            for (PersonCourseRelation enrollment : enrollmentDatabase) {
                Optional<Person> student = studentQuery.findById(enrollment.getPerson());
                Optional<Course> course = courseQuery.findById(enrollment.getCourse());
                Optional<Object> date = Optional.ofNullable(enrollment.getData());
                final StringBuffer sb = new StringBuffer("Enrollment ");
                sb.append("id = ").append(enrollment.getId());
                sb.append("\n\tstudent = ").append(student.isPresent()?student.get():"");
                sb.append("\n\tcourse = ").append(course.isPresent()?course.get():"");
                sb.append("\n\tdate of enrollment = ").append(date.orElse(""));
                System.out.println(sb.toString());
            }
        else {
            PersonCourseRelation enrollment = enrollmentQuery.findById(id).orElse(new PersonCourseRelation());
            Optional<Person> student = studentQuery.findById(enrollment.getPerson());
            Optional<Course> course = courseQuery.findById(enrollment.getCourse());
            Optional<Object> date = Optional.ofNullable(enrollment.getData());
            final StringBuffer sb = new StringBuffer("Enrollment ");
            sb.append("id = ").append(enrollment.getId());
            sb.append("\n\tstudent = ").append(student.isPresent()?student.get():"");
            sb.append("\n\tcourse = ").append(course.isPresent()?course.get():"");
            sb.append("\n\tdate of enrollment = ").append(date.orElse(""));
            System.out.println(sb.toString());
        }

    }

    private static void addEnrollment() {
        PersonCourseRelation enrollment = new PersonCourseRelation();

        viewStudent(0);
        long studentId = Long.parseLong(Console.ask("Student Id : "));

        Optional<Person> student = studentQuery.findById(studentId);

        if (!student.isPresent()) {
            System.out.println("Invalid student id\n");
            return;
        }

        viewCourse(0);
        long courseId = Long.parseLong(Console.ask("Course Id : "));

        Optional<Course> course = courseQuery.findById(courseId);

        if (!course.isPresent()) {
            System.out.println("Invalid course id\n");
            return;
        }
        Optional<PersonCourseRelation> duplicate = enrollmentQuery.findByRelation(studentId, courseId);
        if (duplicate.isPresent()) {
            System.out.println("Duplicated entry found");
            viewEnrollment(duplicate.get().getId());
            return;
        }

        String date = ask("Date of enrollment [%s] : ", (new Date()).toString());

        enrollment.setId(AutoIncrement.getEnrollmentId());
        enrollment.setPerson(studentId);
        enrollment.setCourse(courseId);
        enrollment.setData(date);

        enrollmentDatabase.add(enrollment);
        viewEnrollment(enrollment.getId());
    }
    //TODO: Faculty Assignment
    private static void viewFacultyAssignment(long id) {
        if (id == 0)
            for (PersonCourseRelation facultyAssignment : facultyAssignmentDatabase) {
                Optional<Person> faculty = facultyQuery.findById(facultyAssignment.getPerson());
                Optional<Course> course = courseQuery.findById(facultyAssignment.getCourse());

                final StringBuffer sb = new StringBuffer("FacultyAssignment ");
                sb.append("id = ").append(facultyAssignment.getId());
                sb.append("\n\tfaculty = ").append(faculty.isPresent()?faculty.get():facultyAssignment.getPerson());
                sb.append("\n\tcourse = ").append(course.isPresent()?course.get():facultyAssignment.getCourse());
                System.out.println(sb.toString());
            }
        else {
            PersonCourseRelation facultyAssignment = facultyAssignmentQuery.findById(id).orElse(new PersonCourseRelation());
            Optional<Person> faculty = facultyQuery.findById(facultyAssignment.getPerson());
            Optional<Course> course = courseQuery.findById(facultyAssignment.getCourse());

            final StringBuffer sb = new StringBuffer("FacultyAssignment ");
            sb.append("id = ").append(facultyAssignment.getId());
            sb.append("\n\tfaculty = ").append(faculty.isPresent()?faculty.get():facultyAssignment.getPerson());
            sb.append("\n\tcourse = ").append(course.isPresent()?course.get():facultyAssignment.getCourse());
            System.out.println(sb.toString());
        }
    }
    private static void addFacultyAssignment() {
        PersonCourseRelation facultyAssignment = new PersonCourseRelation();

        viewFaculty(0);
        long facultyId = Long.parseLong(Console.ask("Faculty Id : "));

        Optional<Person> faculty = facultyQuery.findById(facultyId);

        if (!faculty.isPresent()) {
            System.out.println("Invalid faculty id\n");
            return;
        }

        viewCourse(0);
        long courseId = Long.parseLong(Console.ask("Course Id : "));

        Optional<Course> course = courseQuery.findById(courseId);

        if (!course.isPresent()) {
            System.out.println("Invalid course id\n");
            return;
        }

        Optional<PersonCourseRelation> duplicate = facultyAssignmentQuery.findByRelation(facultyId, courseId);
        if (duplicate.isPresent()) {
            System.out.println("Duplicated entry found");
            viewFacultyAssignment(duplicate.get().getId());
            return;
        }

        facultyAssignment.setId(AutoIncrement.getFacultyAssignmentId());
        facultyAssignment.setPerson(facultyId);
        facultyAssignment.setCourse(courseId);

        facultyAssignmentDatabase.add(facultyAssignment);
        viewFacultyAssignment(facultyAssignment.getId());
    }

    private static void doYouWantToQuit() {
        if (Console.YesNo("Do you want to quit? "))
            System.exit(1);
    }
}
