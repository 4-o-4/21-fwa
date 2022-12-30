package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.repositories.ImageRepository;

import javax.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class ImagesService {
    private final ImageRepository imageRepository;

    public ImagesService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image save(long id, String fileName, String mime, String path, Part filePart) {
        String uniqueFileName = uniqueFileName(mime);
        try (OutputStream out = Files.newOutputStream(Paths.get(path + File.separator + uniqueFileName))) {
            try (InputStream in = filePart.getInputStream()) {
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = in.read(bytes)) != -1)
                    out.write(bytes, 0, read);
            }
            long size = filePart.getSize();
            Image image = new Image(fileName, uniqueFileName, mime, size);
            imageRepository.save(id, image);
            return image;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String uniqueFileName(String contentType) {
        String type = contentType.split("/")[1];
        return UUID.randomUUID().toString().replace("-", "") + "." + type;
    }
}
