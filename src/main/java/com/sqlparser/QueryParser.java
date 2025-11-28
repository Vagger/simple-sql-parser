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
        int startIndex = sql.toLowerCase().indexOf("select");
        if (startIndex == -1) return null;

        startIndex += "select".length();
        int endIndex = sql.toLowerCase().indexOf("from", startIndex);
        if (endIndex == -1) return null;

        return Arrays.stream(sql.substring(startIndex, endIndex).split(",")).toList();
    }

}
