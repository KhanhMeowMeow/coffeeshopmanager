package com.example.view.component;

import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ButtonParent_MenuItem extends BorderPane{
    
    public ButtonParent_MenuItem(String text_button, String topOrBottom, String nameButton) {

        btnMenuItem = new Button_MenuItem(text_button, "", nameButton, nameButton);
        btnMenuItem.getGlobal_menu_click_activebutton().listenerEvent("activeMenuClick_Children",  data -> {
            if (!data.equals(nameButton) && children_Box.isVisible()) {
                children_Box.setVisible(false);
                children_Box.setManaged(false);
            }
        });
        btnMenuItem.addEventHandler(ActionEvent.ACTION, e -> {            
            if (children_Box.isVisible()) {
                children_Box.setVisible(false);
                children_Box.setManaged(false);
            } else {
                children_Box.setVisible(true);
                children_Box.setManaged(true);
            }
        });

        children_Box = new VBox();
        children_Box.setVisible(false);
        children_Box.setManaged(false);

        if (topOrBottom.equals("top")) {
            this.setTop(btnMenuItem);
        } else {
            this.setBottom(btnMenuItem);
        }
        this.setCenter(children_Box);
        this.setMinWidth(237);
        this.setPrefHeight(35);
    }

    public VBox getChildrensBox() {
        return children_Box;
    }

    private Button_MenuItem btnMenuItem;
    private VBox children_Box;
}
