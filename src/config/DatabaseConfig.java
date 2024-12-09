package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/databasefinalpbo";
    private static final String USER = "root";  // Ganti dengan username database Anda
    private static final String PASSWORD = "password";  // Ganti dengan password database Anda

    public static Connection connect() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Error connecting to database", e);
        }
    }
}
