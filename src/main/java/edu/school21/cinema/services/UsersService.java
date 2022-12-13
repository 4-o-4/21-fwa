package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public class UsersService {
    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByName(HttpServletRequest req) throws SQLException {
        return userRepository.findByName(req);
    }

    public void save(HttpServletRequest req) throws SQLException {
        userRepository.save(new User(req));
    }
}
