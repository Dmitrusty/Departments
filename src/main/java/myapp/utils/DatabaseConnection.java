package myapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection()
            throws SQLException {

        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/web_departments";
        String dbUsername = "root";
        String dbPassword = "1234";

        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException ignored) {
        }

        return DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    }
}