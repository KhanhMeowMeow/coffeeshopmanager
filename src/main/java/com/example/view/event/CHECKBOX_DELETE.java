package com.example.view.event;

import javafx.event.Event;
import javafx.event.EventType;

public class CHECKBOX_DELETE<T> extends Event {

    public static final EventType<CHECKBOX_DELETE> CHECKBOX_DELETE_TYPE = new EventType<>(Event.ANY, "CHECKBOX_DELETE_TYPE");

    private T data;

    public CHECKBOX_DELETE(T data) {
        super(CHECKBOX_DELETE_TYPE);
        this.data = data;
    }
    
    public T getData() {
        return data;
    }
    
}
