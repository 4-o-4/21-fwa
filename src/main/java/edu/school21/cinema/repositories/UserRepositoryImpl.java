package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepositoryImpl implements UserRepository {
    private final Connection connection;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(User user) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(SQLUser.INSERT.QUERY, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, user.getFirstname());
            pst.setString(2, user.getLastname());
            pst.setString(3, user.getPhone());
            pst.executeUpdate();
        }
    }

    enum SQLUser {
        INSERT("INSERT INTO \"user\" (firstname, lastname, phone) VALUES (?, ?, ?)");

        final String QUERY;

        SQLUser(String query) {
            this.QUERY = query;
        }
    }
}
