package edu.co.icesi.controller;

import edu.co.icesi.model.Actor;
import edu.co.icesi.model.Genre;
import edu.co.icesi.model.Hollywood;
import edu.co.icesi.model.Movie;
import edu.co.icesi.view.DeleteWindow;
import edu.co.icesi.view.LinkWindow;
import edu.co.icesi.view.MainWindow;
import edu.co.icesi.view.RegisterWindow;
import javafx.scene.control.Alert;

import java.util.HashMap;

public class DeleteWindowController {

    private DeleteWindow view;
    private Hollywood model;

    public DeleteWindowController(DeleteWindow view, Hollywood model) {
        this.view = view;
        this.model = model;
        this.initNodes();
    }

    public void initNodes() {

        this.view.setOnCloseRequest(e -> {
            this.model.closeConnection();
        });

        this.view.getCancelBtn().setOnAction(e -> {
            this.model.loadDataBase();
            MainWindow mw = new MainWindow(this.model);
            mw.show();
            this.view.close();
        });

        this.view.getAcceptBtn().setOnAction(e -> {

            if (this.view.getEntities().getValue() != null && !this.view.getEntities().getValue().equals("")) {

                if (this.view.getType().getValue().equals(RegisterWindow.MOVIE)) {
                    this.model.deleteMovie(this.view.getEntities().getValue());
                } else if (this.view.getType().getValue().equals(RegisterWindow.ACTOR)) {
                    this.model.deleteActor(this.view.getEntities().getValue());
                } else {
                    this.model.deleteGenre(this.view.getEntities().getValue());
                }


                showMessage(":)", this.view.getSelected() + " succesfully deleted!", Alert.AlertType.INFORMATION);
            } else {
                showMessage(":(", "Please select something to delete", Alert.AlertType.ERROR);
            }

        });


        this.view.getType().setOnAction(e -> {
            this.view.setSelected(this.view.getType().getValue());
            this.fillComboBox();
        });
    }

    private void fillComboBox() {

        this.view.getEntities().getItems().clear();

        if (this.view.getSelected() != null) {

            if (this.view.getSelected().equals(RegisterWindow.ACTOR)) {

                HashMap<String, Actor> actors = this.model.getActors();

                for (String key : actors.keySet()) {

                    this.view.getEntities().getItems().add(actors.get(key).getName());
                }

            } else if (this.view.getSelected().equals(RegisterWindow.GENRE)) {

                HashMap<String, Genre> genres = this.model.getGenres();

                for (String key : genres.keySet()) {

                    this.view.getEntities().getItems().add(genres.get(key).getName());
                }

            } else {
                HashMap<String, Movie> movies = this.model.getMovies();

                for (String key : movies.keySet()) {

                    this.view.getEntities().getItems().add(movies.get(key).getName());
                }
            }
        } else {

        }
    }

    private void showMessage(String title, String context, Alert.AlertType type) {

        Alert a = new Alert(type);
        a.setContentText(context);
        a.setTitle(title);
        a.show();
    }
}

