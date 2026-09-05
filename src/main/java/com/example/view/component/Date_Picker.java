package com.example.view.component;

import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Date_Picker extends BorderPane{
    public Date_Picker(String title, int width){
        datePicker = new DatePicker();
        labelTitle = new Label(title);
        labelTitle.setStyle("-fx-font-size: 13px; -fx-font-family: 'Asap Condensed'; -fx-text-fill: #626262;");
        labelWarning = new Label(this.warning);
        labelWarning.setStyle("-fx-font-size: 13px; -fx-font-family: 'Asap Condensed'; -fx-text-fill: red;");
        datePicker.setPrefHeight(43);
        datePicker.setPrefWidth(width);

        this.setTop(labelTitle);
        this.setCenter(datePicker);
        this.setBottom(labelWarning);
        this.setMargin(this.getCenter(), new Insets(5, 0, 5, 0));
    }

    public void setWarning(String warning){
        this.warning = warning;
    }

    public DatePicker getDate(){
        return datePicker;
    }

    private String warning;
    private DatePicker datePicker;
    private Label labelTitle, labelWarning;
}
