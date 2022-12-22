package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UsersService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class SignIn extends HttpServlet {
    private UsersService usersService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            resp.sendRedirect("/profile");
        } else {
            req.getRequestDispatcher("WEB-INF/jsp/signIn.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> user;
        try {
            user = usersService.findByName(req);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user.get());
            resp.sendRedirect("/profile");
        } else {
            req.getRequestDispatcher("WEB-INF/jsp/signIn.jsp").forward(req, resp);
        }
    }

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.usersService = springContext.getBean(UsersService.class);
    }
}
