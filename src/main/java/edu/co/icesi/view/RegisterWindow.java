package edu.co.icesi.view;

import edu.co.icesi.controller.RegisterWindowController;
import edu.co.icesi.model.Hollywood;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterWindow extends Stage {

    public final static String GENRE = "Genre";
    public final static String ACTOR = "Actor";
    public final static String MOVIE = "Movie";

    private String selected;

    private ComboBox<String> type;
    private TextField nameTxt;
    private Button acceptBtn;
    private Button cancelBtn;

    private RegisterWindowController controller;

    public RegisterWindow(Hollywood model){

        this.acceptBtn = new Button("Accept");
        this.cancelBtn = new Button("Cancel");

        this.nameTxt = new TextField();
        this.nameTxt.setOpacity(0);
        this.nameTxt.setEditable(false);

        this.type = new ComboBox<String>();
        this.type.getItems().addAll(this.MOVIE, this.ACTOR,this.GENRE);

        type.setOnAction(e->{
            this.selected = type.getValue();
            this.nameTxt.setOpacity(100);
            this.nameTxt.setEditable(true);
        });

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(this.cancelBtn, this.acceptBtn);
        hBox.setSpacing(25);

        VBox vbox = new VBox();
        vbox.setSpacing(25);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(this.type, this.nameTxt, hBox);


        Scene scene = new Scene(vbox, 400, 400);
        this.setScene(scene);
        this.controller = new RegisterWindowController(this, model);

    }




}
