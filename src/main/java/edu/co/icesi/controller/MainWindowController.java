package edu.co.icesi.controller;

import edu.co.icesi.model.Hollywood;
import edu.co.icesi.model.TableMovie;
import edu.co.icesi.view.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MainWindowController {

    private MainWindow view;
    private Hollywood model;

    public MainWindowController(MainWindow mw, Hollywood model) {

        if (model == null)
            this.model = new Hollywood();
        else
            this.model = model;

        this.view = mw;
        this.initButtons();
        showTable();
    }

    private void initButtons() {

        this.view.setOnCloseRequest(e -> {
            this.model.closeConnection();
        });


        this.view.getAddBtn().setOnAction(e -> {

            RegisterWindow rw = new RegisterWindow(this.model);
            rw.show();
            this.view.close();

        });

        this.view.getConsultBtn().setOnAction(e -> {

            ConsultWindow cw = new ConsultWindow(this.model);
            cw.show();
            this.view.close();

        });

        this.view.getDeleteBtn().setOnAction(e -> {

            DeleteWindow dw = new DeleteWindow(this.model);
            dw.show();
            this.view.close();

        });

        this.view.getLinkBtn().setOnAction(e -> {

            LinkWindow lw = new LinkWindow(this.model);
            lw.show();
            this.view.close();

        });
    }

    public void showTable() {


        TableColumn nameColumn = new TableColumn("Movie");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<TableMovie, String>("movie"));

        TableColumn actors = new TableColumn("Actors");
        actors.setMinWidth(198);
        actors.setCellValueFactory(new PropertyValueFactory<TableMovie, String>("actors"));

        TableColumn genres = new TableColumn("Genres");
        genres.setMinWidth(100);
        genres.setCellValueFactory(new PropertyValueFactory<TableMovie, String>("genres"));


        ArrayList<TableMovie> data = this.model.getBasicData();
        ObservableList<TableMovie> items = FXCollections.observableArrayList();


        for (int i = 0; i < data.size(); i++) {

            items.add(data.get(i));
        }
        this.view.getTable().setItems(items);
        this.view.getTable().getColumns().addAll(nameColumn, actors, genres);

    }

}
