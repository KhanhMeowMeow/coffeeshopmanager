package com.example.controller.lib;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class UploadImage {
    
    public static Image choseImage(Window stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn hình ảnh");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Image File", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            return image;
        } else {
            return null;
        }   
    }

    public static String loadImagetoLocal(Image image){ 
        if (image == null) {
            return "";
        }
        try {
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            File outputFile = FileSYSTEM.fileAppImage.resolve(new Random().nextInt(1_000_000) + ".png").toFile();
            ImageIO.write(bImage, "png", outputFile);
            return outputFile.getName();
        } catch (Exception  e) {
            return "";
        }
    }

}
