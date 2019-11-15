package jbc.oct21.jindanupajit.SchoolSystem.util;

import jbc.oct21.jindanupajit.SchoolSystem.model.Course;

import java.util.List;
import java.util.Optional;

public class CourseFinder extends Finder<Course>{



    public CourseFinder(List<? extends Course> database) {
        super(database);
    }


    public Optional<Course> findById(long id) {
        for (Course record : super.getDatabase()) {
            if (record.getId() == id)
                return Optional.of(record);
        }

        return Optional.empty();
    }
}
