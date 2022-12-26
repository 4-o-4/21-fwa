package edu.school21.cinema.models;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Image {
    private String name;
    private String file;
    private String mime;
    private long size;

    public Image(ResultSet rs) throws SQLException {
        this(rs.getString("filename"),
             rs.getString("file"),
             rs.getString("mime"),
             rs.getLong("filesize"));
    }

    public Image(String name, String file, String mime, long size) {
        this.name = name;
        this.file = file;
        this.mime = mime;
        this.size = size;
    }
}
