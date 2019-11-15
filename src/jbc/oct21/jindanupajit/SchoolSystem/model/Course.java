package jbc.oct21.jindanupajit.SchoolSystem.model;

public class Course {
    private long id;
    private String name;
    private String description;

    public Course() {
    }

    public Course(Course course) {
        this.id = course.id;
        this.name = course.name;
        this.description = course.description;
    }
    public Course(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void importValue(Course course) {
        this.id = course.id;
        this.name = course.name;
        this.description = course.description;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Course {");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
