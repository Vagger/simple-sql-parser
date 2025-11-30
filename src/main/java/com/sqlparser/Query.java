package com.sqlparser;

import java.util.List;

public class Query {

    private List<String> selects;
    private List<Source> froms;
    private Integer limit;
    private Integer offset;

    // TODO
//    private List<String> joins;
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

    @Override
    public String toString() {
        return "Query: " +
                "\nSELECTS=" + selects + ", " +
                "\nFROMS=" + froms + ", " +
                "\nLIMIT=" + limit + ", " +
                "\nOFFSET=" + offset + ", " +
                "\n]";
    }
}
