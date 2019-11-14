package jbc.oct21.jindanupajit.helper;

import jbc.oct21.jindanupajit.model.Course;

import java.util.List;
import java.util.Optional;

public class CourseCrudRepository {

    private List<? extends Course> database = null;

    public CourseCrudRepository(List<? extends Course> database) {
        this.database = database;
    }

    public Optional<Course> findById(long id) {
        for (Course record : database) {
            if (record.getId() == id)
                return Optional.of(record);
        }

        return Optional.empty();
    }
}
