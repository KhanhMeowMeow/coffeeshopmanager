package com.example.controller.lib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class FileSYSTEM {

    public static final Path fileApp = Paths.get(System.getProperty("user.home"), "coffeeshopmanager");
    public static final Path fileAppImage = fileApp.resolve("image");
    public static Image logoApp = new Image(FileSYSTEM.class.getResource("/image/LogoPolyCoffee.png").toExternalForm());

    public static Font fontApp = Font.loadFont(FileSYSTEM.class.getResourceAsStream("/font/AsapCondensed-Regular.ttf"), 16);

    public static void createFile(Path path){
        try {
            Files.createDirectories(path);
        } catch (IOException e) {}
    }
}