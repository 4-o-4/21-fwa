package edu.school21.cinema.servlets;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImagesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.UUID;

@Log4j2
@MultipartConfig
@WebServlet("/profile")
public class Profile extends HttpServlet {
    private String path;
    private ImagesService imagesService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        String fileName = getFileName(filePart);
        long size = filePart.getSize();
        String mime = filePart.getContentType();
        if (fileName == null || fileName.equals("")) {
            req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
            return;
        }
        if (!"image/jpeg".equals(mime) && !"image/png".equals(mime)) {
            req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
            return;
        }
        File file = new File(this.path + File.separator);
        if (!file.exists()) {
            file.mkdirs();
        }
        String uniqueFileName = uniqueFileName(mime);
        try (OutputStream out = Files.newOutputStream(Paths.get(path + File.separator + uniqueFileName))) {
            try (InputStream in = filePart.getInputStream()) {
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = in.read(bytes)) != -1)
                    out.write(bytes, 0, read);
                User user = (User) req.getSession().getAttribute("user");
                long id = user.getId();
                Image image = imagesService.save(id, fileName, uniqueFileName, mime, size);
                user.getImages().add(image);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.path = springContext.getBean(String.class);
        this.imagesService = springContext.getBean(ImagesService.class);
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

    private String uniqueFileName(String contentType) {
        String type = contentType.split("/")[1];
        return UUID.randomUUID().toString().replace("-", "") + "." + type;
    }
}
