package com.example.view.component;

import javafx.geometry.Insets;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.BorderPane;

public class Number_Input extends BorderPane {
    public Number_Input(String title, int width, SpinnerValueFactory valueFactory){
        
        labelTitle = new Label(title);
        labelTitle.setStyle("-fx-font-size: 13px; -fx-font-family: 'Asap Condensed'; -fx-text-fill: #626262;");

        labelWarning = new Label();
        labelWarning.setStyle("-fx-font-size: 13px; -fx-font-family: 'Asap Condensed'; -fx-text-fill: #FF0000;");

        spinner = new Spinner<>();
        spinner.setPrefHeight(43);
        spinner.setPrefWidth(width);
        spinner.setValueFactory(valueFactory);

        this.setTop(labelTitle);
        this.setCenter(spinner);
        this.setBottom(labelWarning);
        this.setMargin(this.getTop(), new Insets(5, 0, 5, 0));
    }

    
    public Double getValue(){
        return spinner.getValue();
    }

    public void setValue(Double value){
        this.spinner.getValueFactory().setValue(value);;
    }

    public void setWarning(String warning){
        this.labelWarning.setText(warning);
    }

    private Label labelTitle, labelWarning;
    private Spinner<Double> spinner;
}
