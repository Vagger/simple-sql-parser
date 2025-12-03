package com.sqlparser;

public class Main {
    public static void main(String[] args) {
        String simpleSql = """
                    SELECT book.title, book.pages
                    FROM book
                    LIMIT      10
                    OFFSET   5
                """;

        String largeSql = """
                    SELECT author.name, count(book.id), sum(book.cost)
                    FROM author
                    LEFT JOIN book b ON (author.id = book.author_id)
                    JOIN reader ON (author.id = reader.author_id)
                    INNER JOIN reviews r ON (reader.id = reviews.reader_id)
                    GROUP BY author.name
                    HAVING COUNT(*) > 1 AND SUM(book.cost) > 500
                    LIMIT 10
                    OFFSET 5;
                """;

        QueryParser parser = new QueryParser();
        Query query = parser.parse(largeSql);
        System.out.println(query);
    }
}
