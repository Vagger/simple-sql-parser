package com.sqlparser;

import java.util.List;

public class Query {

    private List<String> selects;
    private List<Source> froms;
    private Integer limit;
    private Integer offset;
    private List<Join> joins;


    // TODO
//    private List<String> wheres;
//    private List<String> groupBy;
//    private List<String> sorts;



    public void setSelects(List<String> selects) {
        this.selects = selects;
    }

    public void setFroms(List<Source> froms) {
        this.froms = froms;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setJoins(List<Join> joins) {
        this.joins = joins;
    }

    @Override
    public String toString() {
        return "Query: " +
                "\nSELECTS=" + selects + ", " +
                "\nFROMS=" + froms + ", " +
                "\nJOINS=" + joins + ", " +
                "\nLIMIT=" + limit + ", " +
                "\nOFFSET=" + offset + ", " +
                "\n]";
    }
}
