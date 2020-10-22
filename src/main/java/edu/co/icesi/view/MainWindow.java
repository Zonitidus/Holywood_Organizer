package edu.co.icesi.view;

import edu.co.icesi.controller.MainWindowController;
import edu.co.icesi.model.Hollywood;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Stage {

    private Scene scene;

    private TableView table;
    private Button consultBtn;
    private Button deleteBtn;
    private Button linkBtn;
    private  Button addBtn;

    private MainWindowController controller;

    public MainWindow(Hollywood model){

        this.table = new TableView();
        this.table.setPrefWidth(500);

        this.consultBtn = new Button("Consult");
        this.deleteBtn = new Button("Delete");
        this.linkBtn = new Button("Link");
        this.addBtn = new Button("Add");

        VBox vBbox = new VBox();
        vBbox.setAlignment(Pos.CENTER);
        vBbox.setSpacing(25);
        vBbox.getChildren().add(this.consultBtn);
        vBbox.getChildren().add(this.deleteBtn);
        vBbox.getChildren().add(this.addBtn);
        vBbox.getChildren().add(this.linkBtn);


        HBox root = new HBox();


        VBox vBbox2 = new VBox();
        vBbox2.setAlignment(Pos.CENTER);
        vBbox2.setSpacing(25);
        vBbox2.getChildren().add(this.table);

        root.setSpacing(35);
        root.getChildren().add(new Label(""));
        root.getChildren().add(vBbox2);
        root.getChildren().add(vBbox);

        this.scene = new Scene(root, 675, 450);
        this.controller = new MainWindowController(this, model);

        this.setScene(scene);

    }

    public TableView getTable() {
        return table;
    }

    public Button getConsultBtn() {
        return consultBtn;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public Button getLinkBtn() {
        return linkBtn;
    }

    public Button getAddBtn() {
        return addBtn;
    }

    public MainWindowController getController() {
        return controller;
    }

    public void setTable(TableView table){
        this.table = table;
    }
}
