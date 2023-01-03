package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImagesService;
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Base64;

@MultipartConfig
@WebServlet("/profile")
public class Profile extends HttpServlet {
    private String path;
    private ImagesService imagesService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("path", this.path);
        User user = (User) req.getSession().getAttribute("user");
        if (!user.getImages().isEmpty()) {
            String uniqueFileName = user.getImages().get(user.getImages().size() - 1).getFile();
            try (InputStream in = Files.newInputStream(Paths.get(this.path + File.separator + uniqueFileName))) {
                byte[] bytes = in.readAllBytes();
                req.getSession().setAttribute("img", Base64.getEncoder().encodeToString(bytes));
            } catch (NoSuchFileException ignored) {
            }
        }
        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        String mime = filePart.getContentType();
        if ("image/jpeg".equals(mime) || "image/png".equals(mime)) {
            File file = new File(this.path + File.separator);
            if (!file.exists())
                file.mkdirs();
            imagesService.save(req, filePart, this.path);
        }
        resp.sendRedirect("/profile");
    }

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.path = springContext.getBean(String.class);
        this.imagesService = springContext.getBean(ImagesService.class);
    }
}
