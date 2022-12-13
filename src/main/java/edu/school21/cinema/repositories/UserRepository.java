package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByName(HttpServletRequest req) throws SQLException;

    void save(User user) throws SQLException;
}
