package com.sqlparser;

import java.util.List;

public class Query {

    public List<String> selects;
    public List<String> froms;

    // TODO
//    public List<String> joins;
//    public List<String> wheres;
//    public List<String> groupBy;
//    public List<String> sorts;
//    public int limit;
//    public int offset;

    @Override
    public String toString() {
        return "Query: " +
                "\nSELECTS=" + selects + ", " +
                "\nFROMS=" + froms +
                "\n]";
    }
}
