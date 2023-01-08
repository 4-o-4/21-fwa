package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

import java.sql.SQLException;

public interface InfoRepository {
    void findInfo(User user) throws SQLException;

    void save(long id, String ip) throws SQLException;
}
