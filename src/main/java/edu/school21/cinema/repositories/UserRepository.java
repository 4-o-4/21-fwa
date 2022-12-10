package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

import java.sql.SQLException;

public interface UserRepository {
    void save(User user) throws SQLException;
}
