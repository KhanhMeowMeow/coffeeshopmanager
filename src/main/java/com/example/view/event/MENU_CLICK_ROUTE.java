package com.example.view.event;

import javafx.event.Event;
import javafx.event.EventType;

public class MENU_CLICK_ROUTE extends Event {

    public static final EventType<MENU_CLICK_ROUTE>  MENU_CLICK_ROUTE_TYPE = new EventType<>(Event.ANY, "MENU_CLICK_ROUTE_TYPE");

    private final String route;
    private final String nameButton;

    public MENU_CLICK_ROUTE(String route, String nameButton) {
        super(MENU_CLICK_ROUTE_TYPE);
        this.route = route;
        this.nameButton = nameButton;
    }

    public String getRoute() {
        return route;
    }

    public String getNameButton() {
        return nameButton;
    }
}
