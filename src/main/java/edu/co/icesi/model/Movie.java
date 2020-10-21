package edu.co.icesi.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Movie {

    private String name;
    private HashMap<String, Actor> cast;
    private ArrayList<Genre> genres;

    public Movie(String name) {
        this.name = name;
        this.cast = new HashMap<String, Actor>();
        this.genres = new ArrayList<Genre>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Actor> getCast() {
        return cast;
    }

    public void setCast(HashMap<String, Actor> cast) {
        this.cast = cast;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }
}
