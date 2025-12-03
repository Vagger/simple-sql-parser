package com.sqlparser;

public class WhereClause {
    private final String type;
    private final String condition;

    public WhereClause(String type, String condition) {
        this.type = type;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "{type='" + type + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
}
