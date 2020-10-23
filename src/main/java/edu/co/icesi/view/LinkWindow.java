package edu.co.icesi.view;

import edu.co.icesi.controller.LinkWindowController;
import edu.co.icesi.controller.RegisterWindowController;
import edu.co.icesi.model.Hollywood;
import edu.co.icesi.model.Movie;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Dictionary;
import java.util.HashMap;

public class LinkWindow extends Stage {

    private String selected;
    private LinkWindowController controller;

    private Button acceptBtn;
    private Button cancelBtn;
    private ComboBox<String> type;
    private ComboBox<String> movie;

    private ComboBox<String> entities;

    public LinkWindow(Hollywood model) {

        this.acceptBtn = new Button("Accept");
        this.cancelBtn = new Button("Cancel");

        this.type = new ComboBox<String>();
        this.type.getItems().addAll(RegisterWindow.ACTOR, RegisterWindow.GENRE);

        this.movie = new ComboBox<String>();
        this.entities = new ComboBox<String>();

        type.setOnAction(e -> {
            this.selected = type.getValue();
        });

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(this.cancelBtn, this.acceptBtn);
        hBox.setSpacing(25);

        VBox vbox = new VBox();
        vbox.setSpacing(25);
        vbox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(this.type, this.movie, this.entities, hBox);


        Scene scene = new Scene(vbox, 400, 400);
        this.setScene(scene);
        this.controller = new LinkWindowController(this, model);
    }

    public String getSelected() {
        return selected;
    }

    public Button getAcceptBtn() {
        return acceptBtn;
    }

    public Button getCancelBtn() {
        return cancelBtn;
    }

    public ComboBox<String> getType() {
        return type;
    }

    public ComboBox<String> getMovie() {
        return movie;
    }

    public ComboBox<String> getEntities() {
        return entities;
    }

    public void setSelected(String sel){
        this.selected = sel;
    }
}
