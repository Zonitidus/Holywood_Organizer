package edu.co.icesi.db;

import java.sql.*;
import java.util.ArrayList;

public class MySQLConnection {

    private Connection connection;

    public MySQLConnection() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HollywoodOrganizer", "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            this.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addMovie(String name) {

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO movies(name) VALUES ('$NAME'))".replace("$NAME", name);
            statement.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addActor(String name) {

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO actors(nombre) VALUES ('$NOMBRE'))".replace("$NOMBRE", name);
            statement.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addGenre(String name) {

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO genres(genre) VALUES ('$GENRE'))".replace("$NGENRE", name);
            statement.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<String> listMovies() {
        ArrayList<String> movies = new ArrayList<String>();
        try {

            Statement statement = connection.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * from movies");

            while (rset.next()) {
                String name = rset.getString(rset.findColumn("name"));
                movies.add(name);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return movies;
    }

    public ArrayList<String> listActors() {
        ArrayList<String> actors = new ArrayList<String>();
        try {

            Statement statement = connection.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * from actors");

            while (rset.next()) {
                String name = rset.getString(rset.findColumn("name"));
                actors.add(name);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return actors;
    }

    public ArrayList<String> listGenres() {
        ArrayList<String> genres = new ArrayList<String>();
        try {

            Statement statement = connection.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * from genres");

            while (rset.next()) {
                String name = rset.getString(rset.findColumn("name"));
                genres.add(name);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genres;
    }

    public void linkMovieAndActor(String idMovie, String idActor) {

    }

    public void linkMovieAndGenre(String idMovie, String idGenre) {

    }

}

