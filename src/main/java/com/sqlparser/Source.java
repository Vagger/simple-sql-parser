package com.sqlparser;

public class Source {
    private String table;
    private String alias;

    public Source(String table, String alias) {
        this.table = table;
        this.alias = alias;
    }

    @Override
    public String toString() {
        return table + (alias != null ? " AS " + alias : "");
    }
}
