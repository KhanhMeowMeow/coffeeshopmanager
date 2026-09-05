package com.example.view.event;

import javafx.event.Event;
import javafx.event.EventType;

public class CHECKBOX_INSERT<T> extends Event{

    public static final EventType<CHECKBOX_INSERT> CHECKBOX_INSERT_TYPE = new EventType<>(Event.ANY, "CHECKBOX_INSERT_TYPE");

    private T data;

    public CHECKBOX_INSERT(T data) {
        super(CHECKBOX_INSERT_TYPE);
        this.data = data;
    }
    
    public T getData() {
        return data;
    }
}
