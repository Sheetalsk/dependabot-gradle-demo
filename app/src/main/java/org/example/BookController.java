package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookController {
    
    private Connection connection;
    
    public BookController(Connection connection) {
        this.connection = connection;
    }
    
    public List<Book> getBooks(String name, String author, boolean read) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT name, author, read FROM books";
        
        try {
            PreparedStatement statement;
            if (name != null && !name.isEmpty()) {
                sql = "SELECT * FROM books WHERE name LIKE ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, "%" + name + "%");
            } else if (author != null && !author.isEmpty()) {
                sql = "SELECT * FROM books WHERE author LIKE ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, "%" + author + "%");
            } else {
                statement = connection.prepareStatement(sql);
            }
            
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(new Book(resultSet.getString("name"), resultSet.getString("author"), resultSet.getBoolean("read")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}

class Book {
    private String name;
    private String author;
    private boolean read;
    
    public Book(String name, String author, boolean read) {
        this.name = name;
        this.author = author;
        this.read = read;
    }
    
    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isRead() {
        return read;
    }
}
