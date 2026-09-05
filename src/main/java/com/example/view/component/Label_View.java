package com.example.view.component;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Label_View extends BorderPane{
    public Label_View(String title, int width){
        
        labelTitle = new Label(title);
        labelTitle.setStyle("-fx-font-size: 13px; -fx-font-family: 'Asap Condensed'; -fx-text-fill: #626262;");

        labelWarning = new Label();
        labelWarning.setStyle("-fx-font-size: 13px; -fx-font-family: 'Asap Condensed'; -fx-text-fill: #FF0000;");

        labelValue = new Label();
        labelValue.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #FF6D6D; -fx-border-radius: 10; -fx-border-width: 1; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;-fx-border-insets: 0 0 0 0; -fx-padding: 0 14 0 14; -fx-font-size: 16px; -fx-font-family: 'Asap Condensed';");
        labelValue.setPrefHeight(43);
        labelValue.setPrefWidth(width);


        this.setTop(labelTitle);
        this.setCenter(labelValue);
        this.setBottom(labelWarning);
        this.setMargin(labelValue, new javafx.geometry.Insets(5, 0, 5, 0));
    }

    public String getValue() {
        return this.labelValue.getText();
    }
    
    public void setValue(String value) {
        this.labelValue.setText(value);
    }
    
    public void setWarning(String warning){
        this.labelWarning.setText(warning);
    }
    
    private Label labelTitle;
    private Label labelValue;
    private Label labelWarning;
}
