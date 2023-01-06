package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

import java.sql.SQLException;
import java.sql.Timestamp;

public interface InfoRepository {
    void findInfo(User user) throws SQLException;

    void save(long id, Timestamp date, String ip) throws SQLException;
}
