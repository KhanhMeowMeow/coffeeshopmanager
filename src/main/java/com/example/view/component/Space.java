package com.example.view.component;

import javafx.scene.layout.Region;

public class Space extends Region {
    
    public Space(int width, int height){
        setPrefSize(width, height);
        setMinSize(width, height);
        setMaxSize(width, height);
    }
}
