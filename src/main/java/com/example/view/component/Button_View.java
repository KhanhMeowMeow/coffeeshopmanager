package com.example.view.component;

import javafx.scene.control.Button;

public class Button_View extends Button{
    
    public Button_View(String text, int width, int height){
        super(text);
        this.setPrefWidth(width);
        this.setPrefHeight(height);
        this.setStyle("-fx-background-color: red; -fx-background-radius: 10; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-family: 'Asap Condensed';");
    }
}
