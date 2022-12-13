package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final Connection connection;
    private final PasswordEncoder encoder;

    public UserRepositoryImpl(Connection connection, PasswordEncoder encoder) {
        this.connection = connection;
        this.encoder = encoder;
    }

    @Override
    public Optional<User> findByName(HttpServletRequest req) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(SQLUser.GET.QUERY)) {
            pst.setString(1, req.getParameter("firstname"));
            ResultSet rs = pst.executeQuery();
            User user = null;
            while (rs.next()) {
                if (encoder.matches(req.getParameter("password"), rs.getString("password"))) {
                    user = new User(rs);
                    break;
                }
            }
            rs.close();
            return Optional.ofNullable(user);
        }
    }

    @Override
    public void save(User user) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(SQLUser.INSERT.QUERY, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, user.getFirstname());
            pst.setString(2, user.getLastname());
            pst.setString(3, user.getPhone());
            pst.setString(4, encoder.encode(user.getPassword()));
            pst.executeUpdate();
        }
    }

    enum SQLUser {
        GET("SELECT * FROM \"user\" WHERE firstname = ?"),
        INSERT("INSERT INTO \"user\" (firstname, lastname, phone, password) VALUES (?, ?, ?, ?)");

        final String QUERY;

        SQLUser(String query) {
            this.QUERY = query;
        }
    }
}
