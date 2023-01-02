package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.ImageRepository;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Log4j2
public class ImagesService {
    private final ImageRepository imageRepository;

    public ImagesService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void save(HttpServletRequest req, Part filePart, String path) {
        String mime = filePart.getContentType();
        String uniqueFileName = uniqueFileName(mime);
        try (OutputStream out = Files.newOutputStream(Paths.get(path + File.separator + uniqueFileName))) {
            try (InputStream in = filePart.getInputStream()) {
                byte[] bytes = in.readAllBytes();
                out.write(bytes);
                req.getSession().setAttribute("img", Base64.getEncoder().encodeToString(bytes));
            }
            User user = (User) req.getSession().getAttribute("user");
            Image image = new Image(getFileName(filePart), uniqueFileName, mime, filePart.getSize());
            imageRepository.save(user.getId(), image);
            user.getImages().add(image);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String uniqueFileName(String contentType) {
        String type = contentType.split("/")[1];
        return UUID.randomUUID().toString().replace("-", "") + "." + type;
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        log.info("Part Header = {}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
