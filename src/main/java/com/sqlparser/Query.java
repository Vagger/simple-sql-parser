package com.sqlparser;

import java.util.List;

public class Query {

    private List<String> selects;
    private List<Source> froms;
    private Integer limit;
    private Integer offset;
    private List<Join> joins;
    private List<WhereClause> whereClauses;
    private List<String> groupByColumns;
    private List<String> havingColumns;
    private List<Sort> orderByColumns;



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

    public void setGroupByColumns(List<String> groupByColumns) {
        this.groupByColumns = groupByColumns;
    }

    public void setHavingColumns(List<String> havingColumns) {
        this.havingColumns = havingColumns;
    }

    public void setOrderByColumns(List<Sort> orderByColumns) {
        this.orderByColumns = orderByColumns;
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
                "\nGROUP BY=" + groupByColumns + ", " +
                "\nHAVING=" + havingColumns + ", " +
                "\nORDER BY=" + orderByColumns + ", " +
                "\nLIMIT=" + limit + ", " +
                "\nOFFSET=" + offset + ", " +
                "\n]";
    }
}
