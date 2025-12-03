package com.sqlparser;

public class Join {
    private final String type;      // e.g., "left join", "inner join"
    private final Source source;    // table being joined
    private final String condition; // ON condition (optional)

    public Join(String type, Source source, String condition) {
        this.type = type;
        this.source = source;
        this.condition = condition;
    }

    public Join(String type, Source source) { // when no ON condition yet
        this(type, source, null);
    }

    @Override
    public String toString() {
        return "{type='" + type + '\'' +
                ", source=" + source +
                ", condition='" + condition + '\'' +
                '}';
    }
}
