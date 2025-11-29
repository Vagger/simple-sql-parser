package com.sqlparser;

public class Main {
    public static void main(String[] args) {
        String sql = """
            SELECT book.title, book.pages FROM book;
        """;

        QueryParser parser = new QueryParser();
        Query query = parser.parse(sql);
        System.out.println(query);
    }
}
