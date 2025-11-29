package com.sqlparser;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class QueryParser {

    public QueryParser() {}

    public Query parse(String sql) {
        Query query = new Query();
        query.selects = parseSelects(sql);
        query.froms = parseFroms(sql);
        return query;
    }

    public List<String> parseSelects(String sql) {
        String selectPart = between(sql, "select", Set.of("from"));
        assert selectPart != null;
        return Arrays.stream(selectPart.split(",")).toList();
    }

    public List<String> parseFroms(String sql) {
        String fromPart = between(sql, "from", Set.of("where", "join", "limit", "offset"));

        assert fromPart != null;
        return Arrays.stream(fromPart.split(",")).toList();
    }

    private String between(String sql, String left, Set<String> right) {
        int startIndex = sql.toLowerCase().indexOf(left);
        if (startIndex == -1) return null;
        startIndex += left.length();

        int endIndex = sql.length() - 1;
        for (String keyword : right) {
            int currentIndex = sql.toLowerCase().indexOf(keyword, startIndex);
            if (currentIndex != -1) {
                endIndex = Math.min(endIndex, currentIndex);
            }
        }
        return sql.substring(startIndex, endIndex);
    }
}
