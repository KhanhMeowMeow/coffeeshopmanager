package com.example.view.component;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Text_Field extends BorderPane {

    public Text_Field(String title, int width, boolean status) {

        lableTitle = new Label(title);
        lableTitle.setStyle("-fx-font-size: 13px; -fx-font-family: 'Asap Condensed'; -fx-text-fill: #626262;");

        labelWarning = new Label();
        labelWarning.setStyle("-fx-font-size: 13px; -fx-font-family: 'Asap Condensed'; -fx-text-fill: #FF0000;");

        textBox = new TextField();
        textBox.setPrefWidth(width);
        textBox.setPrefHeight(43);
        textBox.setPadding(new Insets(0, 14, 0, 14));
        textBox.setDisable(!status);
        this.setTop(lableTitle);
        this.setCenter(textBox);
        this.setBottom(labelWarning);
        this.setMargin(textBox, new Insets(5, 0, 5, 0));
    }

    public void setText(String text) {
        this.textBox.setText(text);
    }

    public String getText(){
        return this.textBox.getText();
    }

    public void setStatus(boolean status){
        this.textBox.setDisable(!status);
    }

    public void setWarning(String warning){
        this.labelWarning.setText(warning);
    }

    public TextField getTextBox() {
        return textBox;
    }

    private Label lableTitle, labelWarning;
    private TextField textBox;
}
