package com.sqlparser;

import java.util.*;

public class QueryParser {

    public QueryParser() {}

    public Query parse(String sql) {

        sql = sql.replaceAll("\\s+", " ")
                .trim()
                .replaceAll(";$", "");

        Query query = new Query();
        query.setSelects(parseSelects(sql));
        query.setFroms(parseFroms(sql));
        query.setLimit(parseLimit(sql));
        query.setOffset(parseOffset(sql));
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

    public Integer parseLimit(String sql) {
        String limitPart = between(sql, "limit", Set.of("offset"));
        if (limitPart == null || limitPart.trim().isEmpty()) {
            return null;
        }
        return Integer.valueOf(limitPart.trim());
    }

    public Integer parseOffset(String sql) {
        String offsetPart = between(sql, "offset", Set.of());
        if (offsetPart == null || offsetPart.trim().isEmpty()) {
            return null;
        }
        return Integer.valueOf(offsetPart.trim());
    }

    private String between(String sql, String left, Set<String> right) {
        int startIndex = sql.toLowerCase().indexOf(left);
        if (startIndex == -1) return null;
        startIndex += left.length();

        int endIndex = sql.length();
        for (String keyword : right) {
            int currentIndex = sql.toLowerCase().indexOf(keyword, startIndex);
            if (currentIndex != -1) {
                endIndex = Math.min(endIndex, currentIndex);
            }
        }
        return sql.substring(startIndex, endIndex);
    }
}
