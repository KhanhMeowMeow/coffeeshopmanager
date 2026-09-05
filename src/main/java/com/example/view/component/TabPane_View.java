package com.example.view.component;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class TabPane_View extends BorderPane {

    public TabPane_View(Tab_View... tab_Views) {

        this.tab_Views = tab_Views;

        layoutHeader = new GridPane();

        layoutHeader.setStyle("-fx-background-color: #B7B7B7; -fx-background-radius: 20 20 0 0;");
        for (int i = 0; i < this.tab_Views.length; i++) {
            Tab_View tab_View = this.tab_Views[i];
            layoutHeader.add(tab_View.getLabelNameTab(), i, 0);
            GridPane.setHgrow(tab_View.getLabelNameTab(), Priority.ALWAYS);
            tab_View.getLabelNameTab().setOnMouseClicked(e -> {
                focusTab(tab_View);
            });
        }

        refestStile();
        this.setTop(layoutHeader);
        if (this.tab_Views.length > 0) {
            this.tab_Views[0].getLabelNameTab().setStyle(this.tab_Views[0].getLabelNameTab().getStyle() + "-fx-background-color: white; -fx-background-radius: 20 20 0 0;");
            this.setCenter(this.tab_Views[0]);
            
        }
    }

    public void focusTab(Tab_View tab_View){
        for (int i = 0; i < tab_Views.length; i++) {
            if (tab_Views[i] == tab_View) {
                this.setCenter(tab_View);
                refestStile();
                tab_View.getLabelNameTab().setStyle(tab_View.getLabelNameTab().getStyle() + "-fx-background-color: white; -fx-background-radius: 20 20 0 0;");
            }
        }
    }

    private void refestStile() {
        for (Tab_View tab_View : this.tab_Views) {
            tab_View.getLabelNameTab().setStyle("-fx-font-size: 16px; -fx-font-family: 'Asap Condensed';");
        }
    }

    private Tab_View[] tab_Views;
    private GridPane layoutHeader;

    public Tab_View[] getTab_Views() {
        return tab_Views;
    }

    public void setTab_Views(Tab_View... tab_Views) {
        this.tab_Views = tab_Views;
    }
}
