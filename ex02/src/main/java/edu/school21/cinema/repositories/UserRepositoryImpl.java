package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.Info;
import edu.school21.cinema.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Date;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository, InfoRepository {
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
            if (user != null) {
                findImages(user);
                save(user.getId(), new Timestamp(new Date().getTime()), req.getRemoteAddr());
                findInfo(user);
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

    private void findImages(User user) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(SQLUser.GET_IMAGES.QUERY)) {
            pst.setLong(1, user.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next())
                user.getImages().add(new Image(rs));
            rs.close();
        }
    }

    @Override
    public void save(long id, Timestamp date, String ip) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(SQLUser.INSERT_INFO.QUERY, Statement.RETURN_GENERATED_KEYS)) {
            pst.setLong(1, id);
            pst.setTimestamp(2, date);
            pst.setString(3, ip);
            pst.executeUpdate();
        }
    }

    @Override
    public void findInfo(User user) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(SQLUser.GET_INFO.QUERY)) {
            pst.setLong(1, user.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next())
                user.getInfo().add(new Info(rs));
            rs.close();
        }
    }

    enum SQLUser {
        GET("SELECT * FROM \"user\" WHERE firstname = ?"),
        GET_IMAGES("SELECT * FROM image WHERE owner = ?"),
        GET_INFO("SELECT * FROM info WHERE owner = ?"),
        INSERT("INSERT INTO \"user\" (firstname, lastname, phone, password) VALUES (?, ?, ?, ?)"),
        INSERT_INFO("INSERT INTO info(owner, date, ip) VALUES (?, ?, ?)");;

        final String QUERY;

        SQLUser(String query) {
            this.QUERY = query;
        }
    }
}
