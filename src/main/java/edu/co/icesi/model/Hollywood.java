package edu.co.icesi.model;

import edu.co.icesi.db.MySQLConnection;
import javafx.scene.control.Tab;

import java.util.ArrayList;
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

        //this.connection = new MySQLConnection();
        this.loadDataBase();
    }

    public void loadDataBase(){

        Movie m1 = new Movie("Rambo 2");
        m1.addCast(new Actor("Manolo"));
        m1.addCast(new Actor("Swaza"));
        m1.addGenre(new Genre("Drama"));
        m1.addGenre(new Genre("Sci-Fi"));

        this.movies.put(m1.getName(), m1);

        Actor a1 = new Actor("Manolo Kabezibolo");
        Actor a2 = new Actor("Gurrupleto");

        Genre g1 = new Genre("Drama");
        Genre g2 = new Genre("Sci-Fi");

        this.actors.put(a1.getName(), a1);
        this.actors.put(a2.getName(), a2);

        this.genres.put(g1.getName(), g1);
        this.genres.put(g2.getName(), g2);

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

    public ArrayList<TableMovie> getBasicData() {

        ArrayList<TableMovie> movies = new ArrayList<TableMovie>();

        for (String key: this.movies.keySet()){

            Movie m = this.movies.get(key);

            TableMovie tm = new TableMovie(m.getName(), m.listActors(), m.listGenres());
            movies.add(tm);
        }

        return movies;
    }

    public HashMap<String, Movie> getMovies() {
        return movies;
    }

    public HashMap<String, Actor> getActors() {
        return actors;
    }

    public HashMap<String, Genre> getGenres() {
        return genres;
    }

    public void linkActorToMovie(String movieName, String actorName) {
    }

    public void linkGenreToMovie(String movieName, String genre) {
    }

    public void deleteMovie(String value) {
    }

    public void deleteActor(String value) {
    }

    public void deleteGenre(String value) {
    }
}
