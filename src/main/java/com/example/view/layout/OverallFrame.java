package com.example.view.layout;

import com.example.view.event.MENU_CLICK_ROUTE;
import com.example.view.frame.ChagePasswordDialog;
import com.example.view.frame.HistoriesFrame;
import com.example.view.frame.HomeFrame;
import com.example.view.frame.InforUserFrame;
import com.example.view.frame.LoginDialog;
import com.example.view.frame.ManagerRevenueFrame;
import com.example.view.frame.ManagerCardFrame;
import com.example.view.frame.ManagerCategoryFrame;
import com.example.view.frame.ManagerDrinksFrame;
import com.example.view.frame.ManagerUserFrame;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class OverallFrame extends BorderPane {

    public OverallFrame() {

        marignCenter = new Insets(20);
        menuComponent = new MenuComponent();
        this.setLeft(menuComponent);
        this.setCenter(new HomeFrame());
        this.setMargin(this.getCenter(), marignCenter);

        this.setEventHandler(MENU_CLICK_ROUTE.MENU_CLICK_ROUTE_TYPE, e -> {

            switch (e.getRoute()) {
                case "home":
                    Platform.runLater(() -> {
                        this.setCenter(new HomeFrame());
                        this.setMargin(this.getCenter(), marignCenter);
                    });
                    break;

                case "schedule":
                    Platform.runLater(() -> {
                        this.setCenter(new Label("Đang phát triển"));
                        this.setMargin(this.getCenter(), marignCenter);
                    });
                    break;

                case "histories":
                    Platform.runLater(() -> {
                        this.setCenter(new HistoriesFrame());
                        this.setMargin(this.getCenter(), marignCenter);
                    });
                    break;

                case "notification":
                    Platform.runLater(() -> {
                        this.setCenter(new Label("Đang phát triển"));
                        this.setMargin(this.getCenter(), marignCenter);
                    });
                    break;

                case "message":
                    Platform.runLater(() -> {
                        this.setCenter(new Label("Đang phát triển"));
                        this.setMargin(this.getCenter(), marignCenter);
                    });
                    break;

                case "managercard":
                    Platform.runLater(() -> {
                        this.setCenter(new ManagerCardFrame());
                        this.setMargin(this.getCenter(), marignCenter);
                    });
                    break;

                case "managercategory":
                    Platform.runLater(() -> {
                        this.setCenter(new ManagerCategoryFrame());
                        this.setMargin(this.getCenter(), marignCenter);
                    });
                    break;

                case "managerdrinks":
                    Platform.runLater(() -> {
                        this.setCenter(new ManagerDrinksFrame());
                        this.setMargin(this.getCenter(), marignCenter);
                    });
                    break;

                case "managerrevenue":
                    Platform.runLater(() -> {
                        this.setCenter(new ManagerRevenueFrame());
                        this.setMargin(this.getCenter(), marignCenter);
                    });
                    break;

                case "manageruser":
                    Platform.runLater(() -> {
                        this.setCenter(new ManagerUserFrame());
                        this.setMargin(this.getCenter(), marignCenter);
                    });
                    break;
                case "inforuser":
                    Platform.runLater(() -> {
                        this.setCenter(new InforUserFrame());
                        this.setMargin(this.getCenter(), marignCenter);
                    });
                    break;
                case "chagepassword":
                    Platform.runLater(() -> {
                        new ChagePasswordDialog().show();
                    });
                    break;
            }
            e.consume();
        });
    }

    private Insets marignCenter;
    private MenuComponent menuComponent;
}
