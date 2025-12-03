package com.sqlparser;

import java.util.List;

public class Query {

    private List<String> selects;
    private List<Source> froms;
    private Integer limit;
    private Integer offset;
    private List<Join> joins;
    private List<WhereClause> whereClauses;

    // TODO
//
//    private List<String> groupByColumns;
//    private List<Sort> sortColumns;



    public void setSelects(List<String> selects) {
        this.selects = selects;
    }

    public void setFroms(List<Source> froms) {
        this.froms = froms;
    }

    public void setJoins(List<Join> joins) {
        this.joins = joins;
    }

    public void setWheres(List<WhereClause> whereClauses) {
        this.whereClauses = whereClauses;
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
                "\nJOINS=" + joins + ", " +
                "\nWHERES=" + whereClauses + ", " +
                "\nLIMIT=" + limit + ", " +
                "\nOFFSET=" + offset + ", " +
                "\n]";
    }
}
