package com.example.view.component;

import java.util.Objects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Group_Radio_Button<T> extends BorderPane{

    public Group_Radio_Button(String title, int width,  Radio_Button<T>... radio_Buttons){

        this.title = title;
        this.radio_Buttons = radio_Buttons;

        toggleGroup = new ToggleGroup();        
        layoutBody = new HBox();
        for (Radio_Button<T> radio_Button : this.radio_Buttons) {
            layoutBody.getChildren().add(radio_Button);
            radio_Button.setToggleGroup(toggleGroup);
        }
        layoutBody.setPrefWidth(width);
        layoutBody.setPrefHeight(43);
        layoutBody.setSpacing(10);
        layoutBody.setAlignment(Pos.CENTER_LEFT);
        layoutBody.setStyle("-fx-background-color: none; -fx-background-radius: 10; -fx-padding: 0 14 0 14;");

        lableTitle = new Label(this.title);
        lableTitle.setStyle("-fx-font-size: 13px; -fx-font-family: 'Asap Condensed'; -fx-text-fill: #626262;");
        lableWarning = new Label();
        lableWarning.setStyle("-fx-font-size: 13px; -fx-font-family: 'Asap Condensed'; -fx-text-fill: red;");

        this.setTop(lableTitle);
        this.setCenter(layoutBody);
        this.setBottom(lableWarning);
        this.setMargin(this.getCenter(), new Insets(5, 0, 5, 0));
    }

    public T getValue(){
        radio_Button = (Radio_Button<T>) toggleGroup.getSelectedToggle();
        if (radio_Button == null) {
            return null;
        }
        return radio_Button.getValue();
    }

    public void setValue(T value){
        if (Objects.equals(value, null)) {
            toggleGroup.selectToggle(null);
            return;
        }
        for (Radio_Button<T> radio_Button : radio_Buttons) {
            if (radio_Button.getValue().equals(value)) {
                toggleGroup.selectToggle(radio_Button);
                return;
            }
        }
        toggleGroup.selectToggle(null);
    }
    public ToggleGroup getChecked(){
        return toggleGroup;
    }

    public void setWarning(String warning){
        this.lableWarning.setText(warning);
    }

    private String title;
    private Radio_Button<T>[] radio_Buttons;
    private Label lableTitle, lableWarning;
    private HBox layoutBody;
    private ToggleGroup toggleGroup;
    private Radio_Button<T> radio_Button;
}
