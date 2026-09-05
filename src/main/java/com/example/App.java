package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import com.example.controller.lib.FileSYSTEM;
import com.example.controller.lib.JDBC;
import com.example.view.frame.LoginDialog;
import com.example.view.layout.OverallFrame;

public class App extends Application {
    
    private Scene scene;
    public static Stage stage;
    
    @Override
    public void start(Stage stage) {
        JDBC.getConnection();
        FileSYSTEM.createFile(FileSYSTEM.fileApp);
        FileSYSTEM.createFile(FileSYSTEM.fileAppImage);
        Font.loadFont(FileSYSTEM.fontApp.getName(), 16);
        scene = new Scene(new OverallFrame());
        scene.getStylesheets().add(getClass().getResource("/css/styleCheckBox.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/css/styleTextBox.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/css/styleTable.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/css/styleDatePicker.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/css/styleSpinner.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("PolyCoffee");
        stage.getIcons().add(FileSYSTEM.logoApp);
        new LoginDialog().show();
        this.stage = stage;
    }


    public static void main(String[] args) {
        launch();
    }

}