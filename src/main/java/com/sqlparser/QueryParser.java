package com.sqlparser;

import java.util.Arrays;
import java.util.List;

public class QueryParser {

    public QueryParser() {}

    public Query parse(String sql) {
        Query query = new Query();
        query.selects = parseSelects(sql);
        System.out.println(query.selects);
        return query;
    }

    public List<String> parseSelects(String sql) {
        String selectPart = between(sql, "select", "from");
        assert selectPart != null;
        return Arrays.stream(selectPart.split(",")).toList();
    }

    private String between(String sql, String left, String right) {
        int startIndex = sql.toLowerCase().indexOf(left);
        if (startIndex == -1) return null;

        startIndex += left.length();
        int endIndex = sql.toLowerCase().indexOf(right, startIndex);
        if (endIndex == -1) return null;

        return sql.substring(startIndex, endIndex);
    }
}
