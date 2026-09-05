package com.example.view.layout;

import com.example.controller.lib.FileSYSTEM;
import com.example.controller.lib.JDBC;

import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainStage extends Stage{

    private Scene scene;

    public MainStage(){
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
        this.setScene(scene);
        this.setMaximized(true);
        this.setTitle("PolyCoffee");
        this.getIcons().add(FileSYSTEM.logoApp);
    }
}
