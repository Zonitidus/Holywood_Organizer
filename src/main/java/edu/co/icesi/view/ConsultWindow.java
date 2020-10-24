package edu.co.icesi.view;

import edu.co.icesi.controller.ConsultWindowController;
import edu.co.icesi.controller.RegisterWindowController;
import edu.co.icesi.model.Hollywood;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConsultWindow extends Stage {

    private String selected;

    private ComboBox<String> type;
    private ComboBox<String> entities;
    private TableView table;
    private Button acceptBtn;
    private Button cancelBtn;

    private ConsultWindowController controller;

    public ConsultWindow(Hollywood model) {

        this.acceptBtn = new Button("Accept");
        this.cancelBtn = new Button("Cancel");

        this.table = new TableView();
        this.table.setOpacity(0);
        this.table.setEditable(false);

        this.entities = new ComboBox<String>();

        this.type = new ComboBox<String>();
        this.type.getItems().addAll(RegisterWindow.MOVIE, RegisterWindow.ACTOR, RegisterWindow.GENRE);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(this.cancelBtn, this.acceptBtn);
        hBox.setSpacing(25);

        VBox vbox = new VBox();
        vbox.setSpacing(25);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(this.type, this.entities, this.table, hBox);


        Scene scene = new Scene(vbox, 400, 400);
        this.setScene(scene);
        this.controller = new ConsultWindowController(this, model);

    }


    public String getSelected() {
        return selected;
    }

    public ComboBox<String> getType() {
        return type;
    }

    public TableView getTable() {
        return table;
    }

    public Button getAcceptBtn() {
        return acceptBtn;
    }

    public Button getCancelBtn() {
        return cancelBtn;
    }

    public ComboBox<String> getEntities(){return entities;}

    public  void setSelected(String sel){ this.selected = sel;}
}
