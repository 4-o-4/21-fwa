package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImageRepositoryImpl implements ImageRepository {
    private final Connection connection;

    public ImageRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(long id, Image image) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement(ImageRepositoryImpl.SQLImage.INSERT.QUERY)) {
            pst.setLong(1, id);
            pst.setString(2, image.getName());
            pst.setString(3, image.getFile());
            pst.setString(4, image.getMime());
            pst.setLong(5, image.getSize());
            pst.executeUpdate();
        }
    }

    enum SQLImage {
        INSERT("INSERT INTO image(owner, filename, file, mime, filesize) VALUES (?, ?, ?, ?, ?)");

        final String QUERY;

        SQLImage(String query) {
            this.QUERY = query;
        }
    }
}
