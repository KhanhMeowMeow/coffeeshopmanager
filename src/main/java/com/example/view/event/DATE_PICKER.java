package com.example.view.event;

import javafx.event.Event;
import javafx.event.EventType;

public class DATE_PICKER<T> extends Event {

    public static final EventType<DATE_PICKER> DATE_PICKER_TYPE = new EventType<>(
        Event.ANY, "DATE_PICKER_TYPE"
    ); 

    private T data;

    public DATE_PICKER(T data) {
        super(DATE_PICKER_TYPE);
        this.data = data;   
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    
}
