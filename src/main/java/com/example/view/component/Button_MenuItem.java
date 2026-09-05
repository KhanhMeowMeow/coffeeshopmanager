package com.example.view.component;

import com.example.view.event.GLOBAL_MENU_CLICK_ACTIVEBUTTON;
import com.example.view.event.MENU_CLICK_ROUTE;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class Button_MenuItem extends Button {

    public Button_MenuItem(String text, String router, String nameButton, String nameParent) {
        
        super(text);
        this.setMinWidth(237);
        this.setMinHeight(35);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setStyle(styleButtonBasic);
        this.setOnMouseEntered(e -> this.setStyle(styleButtonHover));
        this.setOnMouseExited(e -> {
            if (!statusClick_Button_MenuItem) {
                this.setStyle(styleButtonBasic);
            }
        });
        this.setOnAction(e -> {
            setStyleClick();
            this.fireEvent(new MENU_CLICK_ROUTE(router, nameButton));
            global_menu_click_activebutton.fireEvent("activeMenuClick", nameButton);
            global_menu_click_activebutton.fireEvent("activeMenuClick_Children", nameParent);
        });

        global_menu_click_activebutton = new GLOBAL_MENU_CLICK_ACTIVEBUTTON();
        global_menu_click_activebutton.listenerEvent("activeMenuClick", data -> {   
            if (!data.equals(nameButton)) {
                statusClick_Button_MenuItem = false;
                this.setStyle(styleButtonBasic);
            }
        });
    }

    private void setStyleClick() {
        if (!statusClick_Button_MenuItem) {
            this.setStyle(styleButtonHover);
            statusClick_Button_MenuItem = true;
        }
    }

    public GLOBAL_MENU_CLICK_ACTIVEBUTTON getGlobal_menu_click_activebutton() {
        return global_menu_click_activebutton;
    }

    private boolean statusClick_Button_MenuItem = false;
    private GLOBAL_MENU_CLICK_ACTIVEBUTTON global_menu_click_activebutton;
    private String styleButtonBasic = "-fx-background-color: #fff; -fx-font-size: 16px; -fx-font-family: 'Asap Condensed'; -fx-padding: 0 37 0 37;";
    private String styleButtonHover = "-fx-background-color: #FFEDED; -fx-font-size: 16px; -fx-text-fill: red; -fx-font-family: 'Asap Condensed'; -fx-padding: 0 37 0 37;";
}