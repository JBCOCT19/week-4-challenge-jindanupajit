package jbc.oct21.jindanupajit.SchoolSystem.util;

import com.sun.istack.internal.NotNull;

import java.util.List;

public class Finder <T> {
    private List<? extends T> database = null;

    public Finder(@NotNull List<? extends T> database) {
        this.database = database;
    }
    public List<? extends T> getDatabase() {
        return database;
    }
}
