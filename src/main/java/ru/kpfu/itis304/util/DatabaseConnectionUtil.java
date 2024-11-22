package ru.kpfu.itis304.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnectionUtil {
    private static DatabaseConnectionUtil _instance;

    public static DatabaseConnectionUtil getInstance()  {
        if (_instance == null) {
            _instance = new DatabaseConnectionUtil();
        }
        return _instance;
    }

    private Connection con;

    private DatabaseConnectionUtil() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/oris-kntrl",
                    "postgres",
                    "1234");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection getCon() {
        return con;
    }
}

