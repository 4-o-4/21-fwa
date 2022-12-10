package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class UsersService {
    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(HttpServletRequest req) throws SQLException {
        userRepository.save(new User(req));
    }
}
