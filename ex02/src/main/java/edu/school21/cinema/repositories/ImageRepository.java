package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Image;

import java.sql.SQLException;

public interface ImageRepository {
    void save(long id, Image image) throws SQLException;
}
