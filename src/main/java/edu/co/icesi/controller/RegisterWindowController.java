package edu.co.icesi.controller;

import edu.co.icesi.model.Hollywood;
import edu.co.icesi.view.MainWindow;
import edu.co.icesi.view.RegisterWindow;
import javafx.scene.control.Alert;

public class RegisterWindowController {

    private RegisterWindow view;
    private Hollywood model;

    public RegisterWindowController(RegisterWindow view, Hollywood model){
        this.view = view;
        this.model = model;
        this.initNodes();
    }

    private void initNodes() {

        this.view.setOnCloseRequest(e -> {
            this.model.closeConnection();
        });

        this.view.getCancelBtn().setOnAction(e->{
            MainWindow mw = new MainWindow(this.model);
            mw.show();
            this.view.close();
        });

        this.view.getAcceptBtn().setOnAction(e->{

            String entity = "";

            if(this.view.getSelected() != null){
                if(this.view.getSelected().equals(RegisterWindow.MOVIE)){
                    this.model.addMovie(this.view.getNameTxt().getText());
                }

                if(this.view.getSelected().equals(RegisterWindow.ACTOR)){
                    this.model.addActor(this.view.getNameTxt().getText());
                }

                if(this.view.getSelected().equals(RegisterWindow.GENRE)){
                    this.model.addGenre(this.view.getNameTxt().getText());
                }

                showMessage("Register",entity+" successfully registered!", Alert.AlertType.INFORMATION);
            }
            else {
                showMessage(":(",entity+" Please select an option to register", Alert.AlertType.ERROR);
            }
        });

        this.view.getType().setOnAction(e -> {
            this.view.setSelected(this.view.getType().getValue());
            this.view.getNameTxt().setOpacity(100);
            this.view.getNameTxt().setEditable(true);
        });
    }

    private void showMessage(String title, String context, Alert.AlertType type){

        Alert a = new Alert(type);
        a.setContentText(context);
        a.setTitle(title);
        a.show();
    }

}
