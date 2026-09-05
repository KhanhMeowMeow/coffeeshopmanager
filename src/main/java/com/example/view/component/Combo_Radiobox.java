package com.example.view.component;

import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class Combo_Radiobox extends BorderPane {

    public Combo_Radiobox(String title, int width, Map<Long, String> dataList) {

        labelBody = new Label();
        labelTitle = new Label(title);
        labelTitle.setStyle("-fx-font-size: 13px; -fx-font-family: 'Asap Condensed'; -fx-text-fill: #626262;");
        labelWarning = new Label(this.warning);
        labelWarning.setStyle("-fx-font-size: 13px; -fx-font-family: 'Asap Condensed'; -fx-text-fill: red;");
        this.dataList = dataList;

        labelBody.setPrefWidth(width);
        labelBody.setPrefHeight(43);
        labelBody.setStyle(
                "-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #FF6D6D;-fx-border-radius: 10; -fx-border-width: 1; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;-fx-border-insets: 0 0 0 0; -fx-padding: 0 14 0 14; -fx-font-size: 16px; -fx-font-family: 'Asap Condensed';");
        labelBody.setOnMouseClicked(e -> {
            if (!popup.isShowing()) {
                point = this.localToScreen(0, 0);
                popup.show(this, point.getX(), point.getY() + 60);
            }
        });

        popup = new Popup();
        popup.setAutoHide(true);
        popup.setAutoFix(true);
        popup.setHideOnEscape(true);

        bodyComboBox = new ScrollPane();
        bodyComboBox.setPrefWidth(width);
        bodyComboBox.setPrefHeight(300);
        bodyComboBox.setVbarPolicy(ScrollBarPolicy.NEVER);
        bodyComboBox.setHbarPolicy(ScrollBarPolicy.NEVER);
        bodyComboBox.setFitToWidth(true);
        bodyComboBox.setStyle(
                "-fx-border-width: 1; -fx-border-insets: 0 0 0 0; -fx-border-color: #FF6D6D; -fx-background-color: white;");

        toggleGroup = new ToggleGroup();
        layoutBodyComboBox.setStyle("-fx-background-color: white;");
        layoutBodyComboBox.setMaxWidth(Double.MAX_VALUE);
        bodyComboBox.setContent(layoutBodyComboBox);
        popup.getScene().setRoot(bodyComboBox);

        this.setData(this.objectDto.id);
        this.setTop(labelTitle);
        this.setCenter(labelBody);
        this.setBottom(labelWarning);
        this.setMargin(this.getCenter(), new Insets(5, 0, 5, 0));

    }

    public void setData(Long id) {
        for (Map.Entry<Long, String> entry : this.dataList.entrySet()) {
            Radio_Button item = new Radio_Button<Long>(entry.getValue(), entry.getKey());
            item.setStyle(item.getStyle() + "-fx-padding: 10 60 10 60;");
            item.setOnMouseClicked(e -> {
                this.objectDto.id = entry.getKey();
                this.objectDto.value = entry.getValue();
                labelBody.setText(entry.getValue());
                popup.hide();
            });
            item.setToggleGroup(toggleGroup);
            layoutBodyComboBox.getChildren().add(item);

            if (id == null) {
                toggleGroup.selectToggle(null);
                this.objectDto.id = null;
                this.objectDto.value = null;
                labelBody.setText(null);
            } else if (entry.getKey().equals(id)) {
                this.objectDto.id = entry.getKey();
                this.objectDto.value = entry.getValue();
                labelBody.setText(entry.getValue());
                toggleGroup.selectToggle(item);
            }
        }

    }

    public Long getData() {
        return objectDto.id;
    }

    public void setWarning(String warning) {
        this.labelWarning.setText(warning);
    }

    public Label getBody(){
        return this.labelBody;
    }

    private Popup popup;
    private ObjectDto objectDto = new ObjectDto();
    private Map<Long, String> dataList;
    private Point2D point;
    private String warning;
    private ScrollPane bodyComboBox;
    private VBox layoutBodyComboBox = new VBox();
    private ToggleGroup toggleGroup;
    private Label labelBody, labelTitle, labelWarning;
}

class ObjectDto {
    Long id;
    String value;
}