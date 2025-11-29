package com.sqlparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class QueryParser {

    public QueryParser() {}

    public Query parse(String sql) {
        Query query = new Query();
        query.setSelects(parseSelects(sql));
        query.setFroms(parseFroms(sql));
        return query;
    }

    public List<String> parseSelects(String sql) {
        String selectPart = between(sql, "select", Set.of("from"));
        assert selectPart != null;
        return Arrays.stream(selectPart.split(",")).toList();
    }

    public List<Source> parseFroms(String sql) {
        String fromPart = between(sql, "from", Set.of("where", "join", "limit", "offset"));

        List<Source> sources = new ArrayList<>();
        assert fromPart != null;
        for (String part : fromPart.split(",")) {
            String[] tableAlias = part.trim().split("\\s+");
            sources.add(tableAlias.length == 2 ? new Source(tableAlias[0], tableAlias[1]) : new Source(tableAlias[0], null));
        }

        return sources;
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
