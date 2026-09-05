package com.example.view.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Tab_View extends BorderPane{
    
    public Tab_View(String nameTab){

        this.nameTab = nameTab;

        labelNameTab = new Label(this.nameTab);
        labelNameTab.setStyle("-fx-font-size: 16px; -fx-font-family: 'Asap Condensed';");
        labelNameTab.setPadding(new Insets(10));
        labelNameTab.setAlignment(Pos.CENTER);
        labelNameTab.setMaxWidth(Double.MAX_VALUE);
        labelNameTab.setMinWidth(Double.MIN_VALUE);
        
        this.setPadding(new Insets(20));
    }

    private Label labelNameTab;
    private String nameTab;

    public Label getLabelNameTab() {
        return labelNameTab;
    }
    public void setLabelNameTab(Label labelNameTab) {
        this.labelNameTab = labelNameTab;
    }
    public String getNameTab() {
        return nameTab;
    }
    public void setNameTab(String nameTab) {
        this.nameTab = nameTab;
    }
}
