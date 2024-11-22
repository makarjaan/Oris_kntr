package ru.kpfu.itis304.dao;

import ru.kpfu.itis304.entity.User;
import ru.kpfu.itis304.util.DatabaseConnectionUtil;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private DatabaseConnectionUtil connectionProvider;

    public UserDao(DatabaseConnectionUtil connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public User getUser(String login, String password) throws Exception {
        try {
            PreparedStatement st = this.connectionProvider.getCon().prepareStatement("SELECT * FROM users WHERE login = ? AND password = MD5(?)");
            st.setString(1, login);
            st.setString(2, password);
            ResultSet result = st.executeQuery();
            boolean hasOne = result.next();
            if (hasOne) {
                return new User(
                        result.getString("login"),
                        null
                );
            } else { return null; }
        } catch (SQLException e) {
            throw new Exception("Can't get user from db.", e);
        }
    }

    public boolean addUser(User user) throws Exception {
        try {
            PreparedStatement st = this.connectionProvider.getCon().prepareStatement("INSERT INTO users (name, login, password) VALUES (?, ?, ?) ");
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getPassword());
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new Exception("Can't add user from db.", e);
        }
    }

    public void logLoginAttempt(String login, boolean success) throws Exception {
        String sql = "INSERT INTO auth (login, success, timestamp) VALUES (?, ?, ?)";
        try (PreparedStatement statement = this.connectionProvider.getCon().prepareStatement(sql)) {
            statement.setString(1, login);
            statement.setBoolean(2, success);
            statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error logging login attempt.", e);
        }
    }

}
