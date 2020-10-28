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

        this.connection = new MySQLConnection();
        this.loadDataBase();

    }

    public void loadDataBase() {

        this.movies = new HashMap<String, Movie>();
        this.actors = new HashMap<String, Actor>();
        this.genres = new HashMap<String, Genre>();

        ArrayList<String> movies = this.connection.listMovies();
        for (String movie : movies) {
            this.movies.put(movie, new Movie(movie));
        }

        ArrayList<String> actors = this.connection.listActors();
        for (String actor : actors) {
            this.actors.put(actor, new Actor(actor));
        }

        ArrayList<String> genres = this.connection.listGenres();
        for (String genre : genres) {
            this.genres.put(genre, new Genre(genre));
        }

        this.loadLinks();
    }

    public void loadLinks() {

        for (String movie : this.movies.keySet()) {

            ArrayList<String> actors = this.connection.getMovieActors(movie);

            for (String actor : actors) {
                this.movies.get(movie).addCast(new Actor(actor));
            }

            if(this.connection.getMovieGenres(movie).size()> 0)
                this.movies.get(movie).addGenre(new Genre(this.connection.getMovieGenres(movie).get(0)));
        }


        System.out.println(this.connection.getMovieActors("Tenet").toString());
        System.out.println(this.connection.getMovieGenres("Tenet").toString());
    }


    public void addActor(String name) {
        this.actors.put(name, new Actor(name));
        this.connection.addActor(name);
    }

    public void addMovie(String name) {
        this.movies.put(name, new Movie(name));
        this.connection.addMovie(name);
    }

    public void addGenre(String name) {
        this.genres.put(name, new Genre(name));
        this.connection.addGenre(name);
    }

    public ArrayList<TableMovie> getBasicData() {

        ArrayList<TableMovie> movies = new ArrayList<TableMovie>();

        for (String key : this.movies.keySet()) {

            Movie m = this.movies.get(key);

            TableMovie tm = new TableMovie(m.getName(), m.listActors(), m.listGenres());
            movies.add(tm);
        }

        return movies;
    }

    public void linkActorToMovie(String movieName, String actorName) {

        this.connection.linkMovieAndActor(movieName, actorName);
    }

    public void linkGenreToMovie(String movieName, String genre) {

        this.connection.linkMovieAndGenre(movieName, genre);
    }

    public void deleteMovie(String movie) {
        this.connection.deleteMovie(movie);
    }

    public void deleteActor(String actor) {
        this.connection.deleteActor(actor);
    }

    public void deleteGenre(String genre) {
        this.connection.deleteGenre(genre);
    }

    public ArrayList<TableMovie> moviesByGenre(String genre){



        ArrayList<TableMovie> movies = new ArrayList<TableMovie>();

        for (String key : this.movies.keySet()) {

            Movie m = this.movies.get(key);

            if(m.getGenres().get(0).getName().equals(genre)){
                TableMovie tm = new TableMovie(m.getName(), m.listActors(), m.listGenres());
                movies.add(tm);
            }
        }

        return movies;

    }

    public TableMovie getMovie(String movie){

        for (String key : this.movies.keySet()) {

            Movie m = this.movies.get(key);

            if(m.getName().equals(movie)){
                TableMovie tm = new TableMovie(m.getName(), m.listActors(), m.listGenres());
                return tm;
            }
        }

        return new TableMovie("", "", "");

    }


    public ArrayList<TableMovie> moviesByActor(String actor){

        ArrayList<TableMovie> movies = new ArrayList<TableMovie>();

        for (String key : this.movies.keySet()) {

            Movie m = this.movies.get(key);

            if(m.getCast().containsKey(actor)){
                TableMovie tm = new TableMovie(m.getName(), m.listActors(), m.listGenres());
                movies.add(tm);
            }
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

    public void closeConnection() {
        this.connection.closeDB();

    }
}
