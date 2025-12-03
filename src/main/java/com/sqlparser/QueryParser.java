package com.sqlparser;

import java.util.*;

public class QueryParser {

    String currentSql = "";

    public QueryParser() {
    }

    public Query parse(String sql) {

        sql = sql.replaceAll("\\s+", " ").trim().replaceAll(";$", "").toLowerCase();
        currentSql = sql;

        Query query = new Query();
        query.setSelects(parseSelects());
        query.setFroms(parseFroms());
        query.setJoins(parseJoins());
        query.setWheres(parseWheres());
        query.setLimit(parseLimit());
        query.setOffset(parseOffset());
        return query;
    }

    public List<String> parseSelects() {
        List<String> selects = new ArrayList<>();
        String selectPart = between(currentSql, "select ", Set.of(" from "));
        assert selectPart != null;
        for (String part : selectPart.split(",")) {
            selects.add(part.trim());
        }
        return selects;
    }

    public List<Source> parseFroms() {
        String fromPart = between(currentSql, " from ", Set.of(" where ", " join ", " left join ", " right join ", " inner join ", " full join ", " limit ", " offset "));

        List<Source> sources = new ArrayList<>();
        assert fromPart != null;
        for (String part : fromPart.split(",")) {
            String[] tableAlias = part.trim().split("\\s+");
            sources.add(tableAlias.length == 2 ? new Source(tableAlias[0], tableAlias[1]) : new Source(tableAlias[0], null));
        }

        return sources;
    }

    public List<Join> parseJoins() {
        List<Join> joins = new ArrayList<>();

        Set<String> joinKeywords = Set.of(" left join ", " right join ", " inner join ", " full join ", " join ");

        int idx = -1;
        for (String type : joinKeywords) {
            int i = currentSql.indexOf(type);
            if (i != -1 && (idx == -1 || i < idx)) idx = i;
        }

        while (true) {
            String joinType = " join ";
            for (String type : joinKeywords) {
                if (currentSql.startsWith(type, idx)) {
                    joinType = type.trim();
                }
            }
            String tableAndAlias = between(currentSql, joinType, Set.of(" on "));
            if (tableAndAlias == null) {
                break;
            }
            String condition = between(currentSql, " on ", Set.of(" left join ", " right join ", " inner join ", " full join ", " join ", " where ", " group by ", " order by ", " limit ", " offset "));

            String[] parts = tableAndAlias.trim().split("\\s+");
            Source source = new Source(parts[0].trim(), parts.length > 1 ? parts[1].trim() : null);
            joins.add(new Join(joinType.trim(), source, condition != null ? condition.trim() : null));
        }
        return joins;
    }

    public List<WhereClause> parseWheres() {
        List<WhereClause> whereClauses = new ArrayList<>();

        Set<String> whereKeywords = Set.of(" and ", " or ");

        String whereType = " where ";

        String condition = between(currentSql, whereType, Set.of(" and ", " or ", " group by ", " order by ", " limit ", " offset "));
        if (condition == null) {
            return whereClauses;
        }
        whereClauses.add(new WhereClause(whereType.trim(), condition.trim()));
        while (true) {
            for (String type : whereKeywords) {
                if (currentSql.startsWith(type)) {
                    whereType = type;
                }
            }

            condition = between(currentSql, whereType, Set.of(" and ", " or ", " group by ", " order by ", " limit ", " offset "));
            if (condition == null) {
                break;
            }
            WhereClause whereClause = new WhereClause(whereType.trim(), condition.trim());
            whereClauses.add(whereClause);
        }
        return whereClauses;
    }

    public Integer parseLimit() {
        String limitPart = between(currentSql, " limit ", Set.of(" offset "));
        if (limitPart == null || limitPart.trim().isEmpty()) {
            return null;
        }
        return Integer.valueOf(limitPart.trim());
    }

    public Integer parseOffset() {
        String offsetPart = between(currentSql, " offset ", Set.of());
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
        currentSql = sql.substring(endIndex);
        return sql.substring(startIndex, endIndex);
    }
}
