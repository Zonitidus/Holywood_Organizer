package edu.co.icesi.controller;

import edu.co.icesi.model.Actor;
import edu.co.icesi.model.Genre;
import edu.co.icesi.model.Hollywood;
import edu.co.icesi.model.Movie;
import edu.co.icesi.view.LinkWindow;
import edu.co.icesi.view.MainWindow;
import edu.co.icesi.view.RegisterWindow;
import javafx.scene.control.Alert;

import java.util.HashMap;

public class LinkWindowController {

    private LinkWindow view;
    private Hollywood model;

    public LinkWindowController(LinkWindow view, Hollywood model) {
        this.view = view;
        this.model = model;
        this.initNodes();
        this.fillMovies();
    }

    public void initNodes(){

        this.view.getCancelBtn().setOnAction(e->{
            MainWindow mw = new MainWindow(this.model);
            mw.show();
            this.view.close();
        });

        this.view.getAcceptBtn().setOnAction(e->{

            if(this.view.getSelected().equals(RegisterWindow.ACTOR)){
                this.model.linkActorToMovie(this.view.getMovie().getValue(), this.view.getEntities().getValue());
            }else{
                this.model.linkGenreToMovie(this.view.getMovie().getValue(), this.view.getEntities().getValue());
            }

            showMessage(":)", this.view.getSelected()+" succesfully added!", Alert.AlertType.INFORMATION);
        });


        this.view.getType().setOnAction(e->{
            this.view.setSelected(this.view.getType().getValue());
            this.fillComboBox();
        });
    }

    private void fillComboBox(){

        this.view.getEntities().getItems().clear();

        if(this.view.getSelected() != null){



            if(this.view.getSelected().equals(RegisterWindow.ACTOR)){

                HashMap<String, Actor> actors = this.model.getActors();

                for(String key: actors.keySet()){

                    this.view.getEntities().getItems().add(actors.get(key).getName());
                }

            }
            else {

                HashMap<String, Genre> genres = this.model.getGenres();

                for(String key: genres.keySet()){

                    this.view.getEntities().getItems().add(genres.get(key).getName());
                }

            }
        }
        else{
            System.out.println(">>>>ERROOOOOOR");
        }
    }

    private void fillMovies(){

        HashMap<String, Movie> movies = this.model.getMovies();

        for(String key: movies.keySet()){

            this.view.getMovie().getItems().add(movies.get(key).getName());
        }
    }

    private void showMessage(String title, String context, Alert.AlertType type){

        Alert a = new Alert(type);
        a.setContentText(context);
        a.setTitle(title);
        a.show();
    }
}
