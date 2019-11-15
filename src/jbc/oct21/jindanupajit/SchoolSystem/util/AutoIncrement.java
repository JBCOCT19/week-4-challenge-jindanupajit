package jbc.oct21.jindanupajit.SchoolSystem.util;

public class AutoIncrement {
    private static long personId = 0;
    private static long adminId = 0;
    private static long facultyId = 0;
    private static long studentId = 0;
    private static long courseId = 0;
    private static long personCourseRelationId = 0;
    private static long enrollmentId = 0;
    private static long facultyAssignmentId = 0;

    public AutoIncrement() {
    }

    public static long getPersonId() {
        return ++personId;
    }

    public static void setPersonId(long personId) {
        AutoIncrement.personId = personId;
    }



    public static long getAdminId() {
        return ++adminId;
    }

    public static void setAdminId(long adminId) {
        AutoIncrement.adminId = adminId;
    }
    

    public static long getFacultyId() {
        return ++facultyId;
    }

    public static void setFacultyId(long facultyId) {
        AutoIncrement.facultyId = facultyId;
    }
    

    public static long getStudentId() {
        return ++studentId;
    }

    public static void setStudentId(long studentId) {
        AutoIncrement.studentId = studentId;
    }
    

    public static long getCourseId() {
        return ++courseId;
    }

    public static void setCourseId(long courseId) {
        AutoIncrement.courseId = courseId;
    }
    

    public static long getPersonCourseRelationId() {
        return ++personCourseRelationId;
    }

    public static void setPersonCourseRelationId(long personCourseRelationId) {
        AutoIncrement.personCourseRelationId = personCourseRelationId;
    }
    

    public static long getEnrollmentId() {
        return ++enrollmentId;
    }

    public static void setEnrollmentId(long enrollmentId) {
        AutoIncrement.enrollmentId = enrollmentId;
    }

    public static long getFacultyAssignmentId() {
        return ++facultyAssignmentId;
    }

    public static void setFacultyAssignmentId(long facultyAssignmentId) {
        AutoIncrement.facultyAssignmentId = facultyAssignmentId;
    }
}
