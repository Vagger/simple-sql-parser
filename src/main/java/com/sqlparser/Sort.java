package com.sqlparser;

public class Sort {
    private String column;
    private boolean descending;

    public Sort(String column, boolean descending) {
        this.column = column;
        this.descending = descending;
    }

    @Override
    public String toString() {
        return column + (descending ? " DESC" : " ASC");
    }
}
