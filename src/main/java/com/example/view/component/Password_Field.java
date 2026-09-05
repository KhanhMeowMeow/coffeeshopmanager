package com.example.view.component;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Password_Field extends GridPane {

    public Password_Field(String title, int width, boolean status) {
        
        lableTitle = new Label(title);
        lableTitle.setStyle("-fx-font-size: 13px; -fx-font-family: 'Asap Condensed'; -fx-text-fill: #626262;");

        labelWarning = new Label();
        labelWarning.setStyle("-fx-font-size: 13px; -fx-font-family: 'Asap Condensed'; -fx-text-fill: #FF0000;");

        textPassword = new PasswordField();
        textPassword.setPrefWidth(width);
        textPassword.setPrefHeight(43);
        textPassword.setDisable(!status);
        textPassword.setVisible(true);

        textField = new TextField();
        textField.setPrefWidth(width);
        textField.setPrefHeight(43);
        textField.setDisable(!status);
        textField.setVisible(false);

        icon = new ImageView(image0);
        icon.setFitWidth(25);
        icon.setFitHeight(25);
        eyeLable = new Label();
        eyeLable.setPrefHeight(43);
        eyeLable.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #FF6D6D;-fx-border-radius: 0 10 10 0; -fx-border-width: 1 1 1 0; -fx-padding: 0 14 0 14;");
        eyeLable.setGraphic(icon);
        eyeLable.setAlignment(Pos.CENTER);
        eyeLable.setOnMouseClicked(e -> {
            isClickeye = !isClickeye;
            if (isClickeye) {
                icon.setImage(image1);
                textField.setText(textPassword.getText());
                textField.setVisible(true);
                textPassword.setVisible(false);
            } else {
                icon.setImage(image0);
                textPassword.setText(textField.getText());
                textPassword.setVisible(true);
                textField.setVisible(false);
            }
        });
        eyeLable.setVisible(status);
        this.add(lableTitle, 0, 0);
        this.add(textField, 0, 1);
        this.add(textPassword, 0, 1);
        this.add(labelWarning, 0, 2);
        this.setVgap(5);
        this.add(eyeLable, 0, 1);
        this.setHalignment(eyeLable, HPos.RIGHT);
    }

    public String getValue() {
        return isClickeye ? textField.getText() : textPassword.getText();
    }

    public void setValue(String value) {
        if (isClickeye) {
            textField.setText(value);
        }  else {
            textPassword.setText(value);
        }
    }

    public void setWarning(String warning){
        this.labelWarning.setText(warning);
    }

    private PasswordField textPassword;
    private TextField textField;
    private Image image0 = new Image(getClass().getResource("/image/icon/icons8-eye-100.png").toExternalForm());
    private Image image1 = new Image(getClass().getResource("/image/icon/icons8-eye-100_1.png").toExternalForm());
    private ImageView icon;
    private Label eyeLable, lableTitle, labelWarning;
    private boolean isClickeye = false;
}
