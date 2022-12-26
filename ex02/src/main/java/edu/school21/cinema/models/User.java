package edu.school21.cinema.models;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private long id;
    private String firstname;
    private String lastname;
    private String phone;
    private String password;
    private List<Image> images = new ArrayList<>();

    public User(ResultSet rs) throws SQLException {
        this(rs.getLong("id"),
             rs.getString("firstname"),
             rs.getString("lastname"),
             rs.getString("phone"),
             rs.getString("password"));
    }

    public User(HttpServletRequest req) {
        this(req.getParameter("firstname"),
             req.getParameter("lastname"),
             req.getParameter("phone"),
             req.getParameter("password"));
    }

    public User(String firstname, String lastname, String phone, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.password = password;
    }

    public User(long id, String firstname, String lastname, String phone, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.password = password;
    }
}
