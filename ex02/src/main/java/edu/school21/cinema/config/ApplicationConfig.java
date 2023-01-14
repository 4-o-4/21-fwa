package edu.school21.cinema.config;

import edu.school21.cinema.repositories.ImageRepository;
import edu.school21.cinema.repositories.ImageRepositoryImpl;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.repositories.UserRepositoryImpl;
import edu.school21.cinema.services.ImagesService;
import edu.school21.cinema.services.UsersService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@PropertySource("file:src/main/webapp/WEB-INF/application.properties")
public class ApplicationConfig {
    @Value("${db.url}")
    private String url;

    @Value("${storage.path}")
    private String path;

    @Bean
    Connection dataSource() throws SQLException {
        return DriverManager.getConnection(url);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserRepository userRepository() throws SQLException {
        return new UserRepositoryImpl(dataSource(), encoder());
    }

    @Bean
    public UsersService usersService() throws SQLException {
        return new UsersService(userRepository());
    }

    @Bean
    public ImageRepository imageRepository() throws SQLException {
        return new ImageRepositoryImpl(dataSource());
    }

    @Bean
    public ImagesService imagesService() throws SQLException {
        return new ImagesService(imageRepository());
    }

    @Bean
    public String path() {
        return this.path;
    }
}
