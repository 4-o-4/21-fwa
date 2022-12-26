package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.repositories.ImageRepository;

import java.sql.SQLException;

public class ImagesService {
    private final ImageRepository imageRepository;

    public ImagesService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image save(long id, String fileName, String uniqueFileName, String mime, long size) throws SQLException {
        Image image = new Image(fileName, uniqueFileName, mime, size);
        imageRepository.save(id, image);
        return image;
    }
}
