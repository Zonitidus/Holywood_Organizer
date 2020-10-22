package edu.co.icesi.main;

import edu.co.icesi.view.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        MainWindow mainWindow = new MainWindow();
        mainWindow.show();
    }
}
