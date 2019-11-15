package jbc.oct21.jindanupajit.SchoolSystem.control;


import jbc.oct21.jindanupajit.SchoolSystem.model.*;
import jbc.oct21.jindanupajit.SchoolSystem.util.AutoIncrement;
import jbc.oct21.jindanupajit.SchoolSystem.util.CourseFinder;
import jbc.oct21.jindanupajit.SchoolSystem.util.PersonCourseRelationFinder;
import jbc.oct21.jindanupajit.SchoolSystem.util.PersonFinder;
import jbc.oct21.jindanupajit.SchoolSystem.view.Terminal;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class TerminalController implements Runnable {

    private static ArrayList<Admin> adminDatabase = new ArrayList<>();
    private static ArrayList<Faculty> facultyDatabase = new ArrayList<>();
    private static ArrayList<Student> studentDatabase = new ArrayList<>();
    private static ArrayList<Course> courseDatabase = new ArrayList<>();

    private static ArrayList<PersonCourseRelation> enrollmentDatabase = new ArrayList<>();
    private static ArrayList<PersonCourseRelation> facultyAssignmentDatabase = new ArrayList<>();

    private static PersonFinder adminFinder = new PersonFinder(adminDatabase);
    private static PersonFinder facultyFinder = new PersonFinder(facultyDatabase);
    private static PersonFinder studentFinder = new PersonFinder(studentDatabase);
    private static CourseFinder courseFinder = new CourseFinder(courseDatabase);

    private static PersonCourseRelationFinder enrollmentFinder = new PersonCourseRelationFinder(enrollmentDatabase);
    private static PersonCourseRelationFinder facultyAssignmentFinder = new PersonCourseRelationFinder(facultyAssignmentDatabase);

    private Terminal terminal;

    public TerminalController(Terminal terminal) {
        this.terminal = terminal;
    }

    public void run() {


        terminal.init();

        terminal.greeting();

        adminDatabase.add(new Admin(AutoIncrement.getAdminId(),"Krissada", "admin@gmail.com","password"));
        facultyDatabase.add(new Faculty(AutoIncrement.getFacultyId(),"Faculty 1", "faculty@gmail.com","password"));
        studentDatabase.add(new Student(AutoIncrement.getAdminId(),"Student 1", "student@gmail.com","password"));

        courseDatabase.add(new Course(AutoIncrement.getCourseId(), "java101", "Basic Java"));
        courseDatabase.add(new Course(AutoIncrement.getCourseId(), "java102", "Java Boot Camp"));

        enrollmentDatabase.add(new PersonCourseRelation(AutoIncrement.getEnrollmentId(), 1, 1, (new Date()).toString()));

        facultyAssignmentDatabase.add(new PersonCourseRelation(AutoIncrement.getFacultyAssignmentId(), 1, 2));

        start:
        while(true) {
            while (!terminal.YesNo("Would you like to login? ")) {
                doYouWantToQuit_IfYesThenQuit_ElseContinueNextLine();

                terminal.errorln("If you do not want to quit, then login!\n");
            }

            terminal.println("Would you like to login as an admin, faculty, or student?");
            switch (terminal.ask("(A)dmin, (F)aculty, (S)tudent, or (Q)uit : ").toLowerCase()) {
                case "admin":
                case "a":
                    admin();
                    continue start;
                case "faculty":
                case "f":
                    //faculty();
                    terminal.errorln("Faculty login not implement yet!\n");
                    continue start;

                case "student":
                case "s":
                    //student();
                    terminal.errorln("Student login not implement yet!\n");
                    continue start;
                case "quit":
                case "q":
                    doYouWantToQuit_IfYesThenQuit_ElseContinueNextLine();
                    terminal.errorln("Make-up your mind and try again!\n");
                    continue start;
                default:
                    doYouWantToQuit_IfYesThenQuit_ElseContinueNextLine();
                    terminal.errorln("Then, pay attention to the question and try again!\n");
            }
        }
    }


    public void admin() {
        Optional<Person> admin;

        admin = terminal.login(adminFinder);

        if (!admin.isPresent()) {
            terminal.errorln("Wrong credential!\n");
            return;
        }

        terminal.println("\nYou logged in\n");
        terminal.setCurrentUser(admin.get());
        while (true) {
            switch (terminal.menuAdmin()) {
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

    private  void viewAll() {

        viewAdmin(0);

        viewFaculty(0);

        viewStudent(0);

        viewCourse(0);

        viewFacultyAssignment(0);

        viewEnrollment(0);


    }

    //TODO: Admin
    private  void viewAdmin(long id) {
        if (id == 0)
            for (Admin admin : adminDatabase) {
                terminal.println(admin);
            }
        else
            terminal.println(adminFinder.findById(id));
    }



    //TODO: Faculty
    private  void viewFaculty(long id) {
        if (id == 0)
            for (Faculty faculty : facultyDatabase) {
                terminal.println(faculty);
            }
        else
            terminal.println(facultyFinder.findById(id).orElse(new Faculty()));
    }
    private  void addFaculty() {
        Faculty faculty = new Faculty(terminal.getPersonInformation());

        Optional<Person> duplicate = facultyFinder.findByEmail(faculty.getEmail());
        if (duplicate.isPresent()) {
            terminal.errorln("Duplicated entry found (same email)\n");
            viewFaculty(duplicate.get().getId());
            return;
        }

        faculty.setId(AutoIncrement.getFacultyId());
        facultyDatabase.add(faculty);

        viewFaculty(faculty.getId());
    }

    private  void editFaculty() {
        viewFaculty(0);
        long id = Long.parseLong(terminal.ask("Faculty Id : "));

        Optional<Person> current = facultyFinder.findById(id);

        if (!current.isPresent()) {
            terminal.errorln("Invalid id\n");
            return;
        }

        Faculty faculty = new Faculty(terminal.getPersonInformation(current.get()));

        if (!faculty.getEmail().equalsIgnoreCase(current.get().getEmail())) {
            Optional<Person> duplicate = facultyFinder.findByEmail(faculty.getEmail());
            if (duplicate.isPresent()) {
                terminal.errorln("Duplicated entry found (same email)");
                viewFaculty(duplicate.get().getId());
                return;
            }
        }

        faculty.setId(current.get().getId());

        current.get().importValue(faculty);
        viewFaculty(faculty.getId());
    }

    //TODO: Student
    private  void viewStudent(long id) {
        if (id == 0)
            for (Student student : studentDatabase) {
                terminal.println(student);
            }
        else
            terminal.println(studentFinder.findById(id).orElse(new Student()));
    }
    private  void addStudent() {
        Student student = new Student(terminal.getPersonInformation());

        Optional<Person> duplicate = studentFinder.findByEmail(student.getEmail());
        if (duplicate.isPresent()) {
            terminal.errorln("Duplicated entry found (same email)");
            viewStudent(duplicate.get().getId());
            return;
        }
        student.setId(AutoIncrement.getStudentId());
        studentDatabase.add(student);
        viewStudent(student.getId());
    }

    private  void editStudent() {
        viewStudent(0);
        long id = Long.parseLong(terminal.ask("Student Id : "));

        Optional<Person> current = studentFinder.findById(id);

        if (!current.isPresent()) {
            terminal.errorln("Invalid id\n");
            return;
        }

        Student student = new Student(terminal.getPersonInformation(current.get()));

        if (!student.getEmail().equalsIgnoreCase(current.get().getEmail())) {
            Optional<Person> duplicate = studentFinder.findByEmail(student.getEmail());
            if (duplicate.isPresent()) {
                terminal.errorln("Duplicated entry found (same email)");
                viewStudent(duplicate.get().getId());
                return;
            }
        }


        student.setId(current.get().getId());

        current.get().importValue(student);
        viewStudent(student.getId());
    }

    //TODO: Course
    private  void viewCourse(long id) {
        if (id == 0)
            for (Course course : courseDatabase) {
                terminal.println(course);
            }
         else
            terminal.println(courseFinder.findById(id).orElse(new Course()));

    }

    private  void addCourse() {
        Course course = new Course(terminal.getCourseInformation());

        course.setId(AutoIncrement.getCourseId());

        courseDatabase.add(course);
        viewCourse(course.getId());
    }

    private  void editCourse() {
        viewCourse(0);
        long id = Long.parseLong(terminal.ask("Course Id : "));

        Optional<Course> current = courseFinder.findById(id);

        if (!current.isPresent()) {
            terminal.errorln("Invalid id\n");
            return;
        }

       Course course = new Course(terminal.getCourseInformation(current.get()));
       course.setId(current.get().getId());

       current.get().importValue(course);
        viewCourse(course.getId());
    }

    //TODO: Enrollment
    private  void viewEnrollment(long id) {
        if (id == 0)
            for (PersonCourseRelation enrollment : enrollmentDatabase) {
                if (enrollment.getId() != 0) viewEnrollment(enrollment.getId());
            }
        else {
            PersonCourseRelation enrollment = enrollmentFinder.findById(id).orElse(new PersonCourseRelation());
            Optional<Person> student = studentFinder.findById(enrollment.getPerson());
            Optional<Course> course = courseFinder.findById(enrollment.getCourse());
            Optional<Object> date = Optional.ofNullable(enrollment.getData());
            final StringBuilder sb = new StringBuilder("Enrollment ");
            sb.append("id = ").append(enrollment.getId());
            sb.append("\n\tstudent = ").append(student.isPresent()?student.get():"");
            sb.append("\n\tcourse = ").append(course.isPresent()?course.get():"");
            sb.append("\n\tdate of enrollment = ").append(date.orElse(""));
            terminal.println(sb.toString());
        }

    }

    private  void addEnrollment() {
        PersonCourseRelation enrollment = new PersonCourseRelation();

        viewStudent(0);
        long studentId = Long.parseLong(terminal.ask("Student Id : "));

        Optional<Person> student = studentFinder.findById(studentId);

        if (!student.isPresent()) {
            terminal.errorln("Invalid student id\n");
            return;
        }

        viewCourse(0);
        long courseId = Long.parseLong(terminal.ask("Course Id : "));

        Optional<Course> course = courseFinder.findById(courseId);

        if (!course.isPresent()) {
            terminal.errorln("Invalid course id\n");
            return;
        }
        Optional<PersonCourseRelation> duplicate = enrollmentFinder.findByRelation(studentId, courseId);
        if (duplicate.isPresent()) {
            terminal.errorln("Duplicated entry found");
            viewEnrollment(duplicate.get().getId());
            return;
        }

        String date = terminal.ask("Date of enrollment [%s] : ", (new Date()).toString());

        enrollment.setId(AutoIncrement.getEnrollmentId());
        enrollment.setPerson(studentId);
        enrollment.setCourse(courseId);
        enrollment.setData(date);

        enrollmentDatabase.add(enrollment);
        viewEnrollment(enrollment.getId());
    }
    //TODO: Faculty Assignment
    private  void viewFacultyAssignment(long id) {
        if (id == 0)
            for (PersonCourseRelation facultyAssignment : facultyAssignmentDatabase) {
                   if (facultyAssignment.getId() != 0) viewFacultyAssignment(facultyAssignment.getId());
            }
        else {
            PersonCourseRelation facultyAssignment = facultyAssignmentFinder.findById(id).orElse(new PersonCourseRelation());

            Optional<Person> faculty = facultyFinder.findById(facultyAssignment.getPerson());
            Optional<Course> course = courseFinder.findById(facultyAssignment.getCourse());

            final StringBuilder sb = new StringBuilder("FacultyAssignment ");
            sb.append("id = ").append(facultyAssignment.getId());
            sb.append("\n\tfaculty = ").append(faculty.isPresent()?faculty.get():facultyAssignment.getPerson());
            sb.append("\n\tcourse = ").append(course.isPresent()?course.get():facultyAssignment.getCourse());
            terminal.println(sb.toString());
        }
    }
    private  void addFacultyAssignment() {
        PersonCourseRelation facultyAssignment = new PersonCourseRelation();

        viewFaculty(0);
        long facultyId = Long.parseLong(terminal.ask("Faculty Id : "));

        Optional<Person> faculty = facultyFinder.findById(facultyId);

        if (!faculty.isPresent()) {
            terminal.errorln("Invalid faculty id\n");
            return;
        }

        viewCourse(0);
        long courseId = Long.parseLong(terminal.ask("Course Id : "));

        Optional<Course> course = courseFinder.findById(courseId);

        if (!course.isPresent()) {
            terminal.errorln("Invalid course id\n");
            return;
        }

        Optional<PersonCourseRelation> duplicate = facultyAssignmentFinder.findByRelation(facultyId, courseId);
        if (duplicate.isPresent()) {
            terminal.errorln("Duplicated entry found");
            viewFacultyAssignment(duplicate.get().getId());
            return;
        }

        facultyAssignment.setId(AutoIncrement.getFacultyAssignmentId());
        facultyAssignment.setPerson(facultyId);
        facultyAssignment.setCourse(courseId);

        facultyAssignmentDatabase.add(facultyAssignment);
        viewFacultyAssignment(facultyAssignment.getId());
    }

    private  void doYouWantToQuit_IfYesThenQuit_ElseContinueNextLine() {
        if (terminal.YesNo("Do you want to quit? "))
            System.exit(1);
    }
}
