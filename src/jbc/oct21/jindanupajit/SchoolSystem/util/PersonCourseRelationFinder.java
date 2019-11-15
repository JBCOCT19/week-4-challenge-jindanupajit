package jbc.oct21.jindanupajit.SchoolSystem.util;

import jbc.oct21.jindanupajit.SchoolSystem.model.PersonCourseRelation;

import java.util.List;
import java.util.Optional;

public class PersonCourseRelationFinder extends Finder<PersonCourseRelation> {


    public PersonCourseRelationFinder(List<? extends PersonCourseRelation> database) {
        super(database);
    }

    public Optional<PersonCourseRelation> findById(long id) {
        for (PersonCourseRelation record : super.getDatabase()) {
            if (record.getId() == id)
                return Optional.of(record);
        }

        return Optional.empty();
    }

    public Optional<PersonCourseRelation> findByRelation(long personId, long courseId) {
        for (PersonCourseRelation record : super.getDatabase()) {
            if ((record.getPerson() == personId)&&(record.getCourse() == courseId))
                return Optional.of(record);
        }

        return Optional.empty();
    }
}
