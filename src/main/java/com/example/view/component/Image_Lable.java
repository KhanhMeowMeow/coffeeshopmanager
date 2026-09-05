package com.example.view.component;

import com.example.controller.lib.FileSYSTEM;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Image_Lable extends Label {

    public Image_Lable(int width, int height, String nameFileImage) {

        this.nameImage = nameFileImage;

        imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        this.showImage(this.nameImage);
        this.setGraphic(imageView);
        this.setStyle(
                "-fx-border-color: #FF6D6D; -fx-border-width: 1; -fx-border-insets: 0 0 0 0; -fx-border-radius: 10; -fx-padding: 10; -fx-background-color: white; -fx-background-radius: 10;");
    }

    public void showImage(String nameImage) {
        Task<Image> loadImage = new Task<>() {
            @Override
            protected Image call() throws Exception {
                Image image = new Image("file:" + FileSYSTEM.fileAppImage.resolve(nameImage).toString());
                if (nameImage == null || nameImage.equals("")) {
                    imageView.setImage(new Image(getClass().getResource("/image/graybackground.png").toExternalForm()));
                    image = null;
                    return image;
                }
                if (image.isError()) {
                    throw new Exception("Không thể tải ảnh: " + nameImage);
                }
                return image;
            }
        };

        loadImage.setOnRunning(e -> {
            imageView.setImage(new Image(getClass().getResource("/image/graybackground.png").toExternalForm()));
            image = null;
        });
        loadImage.setOnSucceeded(e -> {
            imageView.setImage(loadImage.getValue());
            image = loadImage.getValue();
        });
        loadImage.setOnFailed(e -> {
            imageView.setImage(new Image(getClass().getResource("/image/graybackground.png").toExternalForm()));
            image = null;
        });
        new Thread(loadImage).start();
    }

    public void setImage(Image image) {
        Platform.runLater(() -> {
            if (image.isError()) {
                imageView.setImage(new Image(getClass().getResource("/image/graybackground.png").toExternalForm()));
                this.image = null;
            }
            this.image = image;
            imageView.setImage(this.image);
        });
    }

    public Image getImage() {
        return this.image;
    }

    public String getNameImage() {
        return this.nameImage;
    }

    private String nameImage;
    private Image image;
    private ImageView imageView;
}
