package jbc.oct21.jindanupajit.helper;

import jbc.oct21.jindanupajit.model.Course;
import jbc.oct21.jindanupajit.model.PersonCourseRelation;

import java.util.List;
import java.util.Optional;

public class PersonCourseRelationCrudRepository {
    private List<? extends PersonCourseRelation> database = null;

    public PersonCourseRelationCrudRepository(List<? extends PersonCourseRelation> database) {
        this.database = database;
    }

    public Optional<PersonCourseRelation> findById(long id) {
        for (PersonCourseRelation record : database) {
            if (record.getId() == id)
                return Optional.of(record);
        }

        return Optional.empty();
    }

    public Optional<PersonCourseRelation> findByRelation(long personId, long courseId) {
        for (PersonCourseRelation record : database) {
            if ((record.getPerson() == personId)&&(record.getCourse() == courseId))
                return Optional.of(record);
        }

        return Optional.empty();
    }
}
