package com.sqlparser;

import java.util.List;

public class Query {

    private List<String> selects;
    private List<Source> froms;

    // TODO
//    public List<String> joins;
//    public List<String> wheres;
//    public List<String> groupBy;
//    public List<String> sorts;
//    public int limit;
//    public int offset;


    public void setSelects(List<String> selects) {
        this.selects = selects;
    }

    public void setFroms(List<Source> froms) {
        this.froms = froms;
    }

    @Override
    public String toString() {
        return "Query: " +
                "\nSELECTS=" + selects + ", " +
                "\nFROMS=" + froms +
                "\n]";
    }
}
