package edu.school21.cinema.models;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Data
public class Info {
    private Timestamp date;
    private String ip;

    public Info(ResultSet rs) throws SQLException {
        this(rs.getTimestamp("date"),
             rs.getString("ip"));
    }

    public Info(Timestamp date, String ip) {
        this.date = date;
        this.ip = ip;
    }
}
