package jbc.oct21.jindanupajit.SchoolSystem.model;

public class PersonCourseRelation {
    private long id;
    private long person;
    private long course;
    private Object data = null;

    public PersonCourseRelation() {
    }

    public PersonCourseRelation(PersonCourseRelation personCourseRelation) {
        this.id = personCourseRelation.id;
        this.person = personCourseRelation.person;
        this.course = course;
    }

    public PersonCourseRelation(long id, long person, long course) {
        this.id = id;
        this.person = person;
        this.course = course;
    }

    public PersonCourseRelation(long id, long person, long course, Object data) {
        this.id = id;
        this.person = person;
        this.course = course;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPerson() {
        return person;
    }

    public void setPerson(long person) {
        this.person = person;
    }

    public long getCourse() {
        return course;
    }

    public void setCourse(long course) {
        this.course = course;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void importValue(PersonCourseRelation personCourseRelation) {
        this.id = personCourseRelation.id;
        this.person = personCourseRelation.person;
        this.course = personCourseRelation.course;
        this.data = personCourseRelation.data;
    }
   @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PersonCourseRelation ");
        sb.append("id=").append(id);
        sb.append(", person=").append(person);
        sb.append(", course=").append(course);
        sb.append('}');
        return sb.toString();
    }
}
