package edu.school21.cinema.models;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;

@Data
public class User {
    private String firstname;
    private String lastname;
    private String phone;
    private String password;

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
}
