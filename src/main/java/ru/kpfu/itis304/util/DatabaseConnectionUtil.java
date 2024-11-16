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
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); //подключение драйвера
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java3_books", "root", "ksenia2005");
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection getCon() {
        return con;
    }
}
