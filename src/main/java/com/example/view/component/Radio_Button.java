package com.example.view.component;

import javafx.scene.control.RadioButton;

public class Radio_Button<T> extends RadioButton{
    public Radio_Button(String title, T value){
        this.value = value;
        this.setText(title);
    }

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value){
        this.value = value;
    }
}
