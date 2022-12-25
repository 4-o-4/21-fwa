package edu.school21.cinema.listeners;

import edu.school21.cinema.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().
                setAttribute("springContext", new AnnotationConfigApplicationContext(ApplicationConfig.class));
    }
}
