package edu.co.icesi.model;

import javafx.beans.property.SimpleStringProperty;

public class TableMovie {

    private final SimpleStringProperty movie;
    private final SimpleStringProperty actors;
    private final SimpleStringProperty genres;

    public TableMovie(String name, String actors, String genres) {
        this.movie = new SimpleStringProperty(name);
        this.actors = new SimpleStringProperty(actors);
        this.genres = new SimpleStringProperty(genres);
    }

    public String getMovie() {
        return movie.get();
    }

    public SimpleStringProperty movieProperty() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie.set(movie);
    }

    public String getActors() {
        return actors.get();
    }

    public SimpleStringProperty actorsProperty() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors.set(actors);
    }

    public String getGenres() {
        return genres.get();
    }

    public SimpleStringProperty genresProperty() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres.set(genres);
    }

    @Override
    public String toString() {
        return "-------------------------------------------\n" + this.movie.getValue() + "\n-----\n" + this.actors + "\n-----\n" + this.genres + "\n-------------------------------------------\n";
    }
}
