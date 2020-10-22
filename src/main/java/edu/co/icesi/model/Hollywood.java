package edu.co.icesi.model;

import edu.co.icesi.db.MySQLConnection;

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

        TableMovie a = new TableMovie("Rambo 2", "Arnold\nEl pepe\nAdam Sandler", "Drama");
        TableMovie b = new TableMovie("Interestelar", "Nolan\nEte Sech\nTotori", "Action\nSci-Fi");



        ArrayList<TableMovie> tm = new ArrayList<TableMovie>();
        tm.add(a);
        tm.add(b);
        return tm;
    }
}
