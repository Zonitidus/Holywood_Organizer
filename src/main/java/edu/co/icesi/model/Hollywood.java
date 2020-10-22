package edu.co.icesi.model;

import edu.co.icesi.db.MySQLConnection;

import java.util.HashMap;

public class Hollywood {

    private HashMap<String, Movie> movies;
    private HashMap<String, Actor> actors;
    private HashMap<String, Genre> genres;

    private MySQLConnection connection;

    public Hollywood() {
        this.movies = new HashMap<String, Movie>();
        this.actors = new HashMap<String, Actor>();
        this.genres = new HashMap<String, Genre>();

        this.connection = new MySQLConnection();

    }

    public void loadDataBase(){



    }


    public void addActor(String name) {
        this.actors.put(name, new Actor(name));
    }

    public void addMovie(String name) {
        this.movies.put(name, new Movie(name));
    }

    public void addGenre(String name) {
        this.genres.put(name, new Genre(name));
    }
}
