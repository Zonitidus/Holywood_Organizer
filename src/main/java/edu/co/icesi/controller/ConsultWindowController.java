package edu.co.icesi.controller;

import edu.co.icesi.model.*;
import edu.co.icesi.view.ConsultWindow;
import edu.co.icesi.view.MainWindow;
import edu.co.icesi.view.RegisterWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class ConsultWindowController {

    private ConsultWindow view;
    private Hollywood model;

    public ConsultWindowController(ConsultWindow view, Hollywood model) {
        this.view = view;
        this.model = model;
        this.initNodes();
        this.showTable();
    }

    private void initNodes() {

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

            String entity = "";

            TableColumn nameColumn = new TableColumn("Movie");
            nameColumn.setMinWidth(200);
            nameColumn.setCellValueFactory(new PropertyValueFactory<TableMovie, String>("movie"));

            TableColumn actors = new TableColumn("Actors");
            actors.setMinWidth(198);
            actors.setCellValueFactory(new PropertyValueFactory<TableMovie, String>("actors"));

            TableColumn genres = new TableColumn("Genres");
            genres.setMinWidth(100);
            genres.setCellValueFactory(new PropertyValueFactory<TableMovie, String>("genres"));


            if (this.view.getSelected() != null && this.view.getEntities().getValue() != null) {
                if (this.view.getSelected().equals(RegisterWindow.MOVIE)) {



                }

                else if (this.view.getSelected().equals(RegisterWindow.ACTOR)) {

                    ArrayList<TableMovie> data = this.model.moviesByActor(this.view.getEntities().getValue());
                    ObservableList<TableMovie> items = FXCollections.observableArrayList();


                    for (int i = 0; i < data.size(); i++) {

                        items.add(data.get(i));
                    }
                    this.view.getTable().setItems(items);
                    this.view.getTable().getColumns().addAll(nameColumn, actors, genres);


                }

                else if(this.view.getSelected().equals(RegisterWindow.GENRE)) {


                    ArrayList<TableMovie> data = this.model.moviesByGenre(this.view.getEntities().getValue());
                    ObservableList<TableMovie> items = FXCollections.observableArrayList();


                    for (int i = 0; i < data.size(); i++) {

                        items.add(data.get(i));
                    }
                    this.view.getTable().setItems(items);
                    this.view.getTable().getColumns().addAll(nameColumn, actors, genres);
                }

            } else {
                showMessage(":(", entity + " Please select an option to filter", Alert.AlertType.ERROR);
            }
        });

        this.view.getType().setOnAction(e -> {
            this.view.setSelected(this.view.getType().getValue());
            this.view.getTable().setOpacity(100);
            this.view.getTable().setEditable(true);
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
            else if(this.view.getSelected().equals(RegisterWindow.GENRE)){

                HashMap<String, Genre> genres = this.model.getGenres();

                for(String key: genres.keySet()){

                    this.view.getEntities().getItems().add(genres.get(key).getName());
                }

            }
            else{
                HashMap<String, Movie> movies = this.model.getMovies();

                for(String key: movies.keySet()){

                    this.view.getEntities().getItems().add(movies.get(key).getName());
                }
            }
        }
        else{

        }
    }



    public void showTable(){


        TableColumn nameColumn = new TableColumn("Movie");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<TableMovie, String>("movie"));

        TableColumn actors = new TableColumn("Actors");
        actors.setMinWidth(150);
        actors.setCellValueFactory(new PropertyValueFactory<TableMovie, String>("actors"));

        TableColumn genres = new TableColumn("Genres");
        genres.setMinWidth(98);
        genres.setCellValueFactory(new PropertyValueFactory<TableMovie, String>("genres"));




        ArrayList<TableMovie> data = this.model.getBasicData();
        ObservableList<TableMovie> items = FXCollections.observableArrayList();



        for (int i = 0; i< data.size(); i++){

            items.add(data.get(i));
        }
        this.view.getTable().setItems(items);
        this.view.getTable().getColumns().addAll(nameColumn, actors, genres);

    }



    private void showMessage(String title, String context, Alert.AlertType type) {

        Alert a = new Alert(type);
        a.setContentText(context);
        a.setTitle(title);
        a.show();
    }
}