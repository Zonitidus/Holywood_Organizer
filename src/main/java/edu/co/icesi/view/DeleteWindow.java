package edu.co.icesi.view;

import edu.co.icesi.controller.DeleteWindowController;
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

public class DeleteWindow extends Stage {

    private String selected;
    private DeleteWindowController controller;

    private Button acceptBtn;
    private Button cancelBtn;
    private ComboBox<String> type;

    private ComboBox<String> entities;

    public DeleteWindow(Hollywood model) {

        this.acceptBtn = new Button("Delete");
        this.cancelBtn = new Button("Cancel");

        this.type = new ComboBox<String>();
        this.type.getItems().addAll(RegisterWindow.MOVIE,RegisterWindow.ACTOR, RegisterWindow.GENRE);

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

        vbox.getChildren().addAll(this.type, this.entities, hBox);


        Scene scene = new Scene(vbox, 400, 400);
        this.setScene(scene);
        this.controller = new DeleteWindowController(this, model);
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

    public ComboBox<String> getEntities() {
        return entities;
    }

    public void setSelected(String sel){
        this.selected = sel;
    }
}
