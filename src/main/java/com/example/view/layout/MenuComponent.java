package com.example.view.layout;


import com.example.App;
import com.example.controller.lib.SYSTEM_SESSION;
import com.example.view.component.ButtonParent_MenuItem;
import com.example.view.component.Button_MenuItem;
import com.example.view.event.LOGIN;
import com.example.view.frame.LoginDialog;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MenuComponent extends BorderPane{

    public MenuComponent() {

        LOGIN = new LOGIN();

        menuBar = new VBox(0.0);
        logoContainer = new BorderPane();
        adminContainer = new BorderPane();

        icon_menu = new Image("file:src/main/resources/image/LogoPolyCoffee.png");
        btnMenuItem = new Button_MenuItem("Trang chủ", "home", "home", "");
        btnMenuItem2 = new Button_MenuItem("Lịch phân công", "schedule", "schedule", "");
        btnMenuItem3 = new Button_MenuItem("Đặt đơn", "order", "order", "");
        btnMenuItem4 = new Button_MenuItem("Thông báo" , "notification", "notification", "");
        btnMenuItem5 = new Button_MenuItem("Tin nhắn", "message","message", "");
        btnMenuItem8 = new Button_MenuItem("Lịch sử lên đơn", "histories", "histories", "histories");
        
        btnMenuItem2.setVisible(false);
        btnMenuItem2.setManaged(false);

        btnMenuItem4.setVisible(false);
        btnMenuItem4.setManaged(false);

        btnMenuItem5.setVisible(false);
        btnMenuItem5.setManaged(false);

        btnLogout =  new Button_MenuItem("  Đăng xuất", "logout", "logout", "setting");
        btnLogout.setOnMouseClicked(e -> {
            SYSTEM_SESSION.idUser = null;
            new LoginDialog().show(); 
            App.stage.hide();
        });
        btnMenuItem6 = new ButtonParent_MenuItem("Cài đặt", "top", "setting");
        btnMenuItem6.getChildrensBox().getChildren().addAll( 
            new Button_MenuItem("  Thông tin tài khoản", "inforuser", "inforuser", "setting"),
            new Button_MenuItem("  Đổi mật khẩu", "chagepassword", "change_password", "setting"),
            btnLogout
        );

        btnMenuItem7 = new ButtonParent_MenuItem("Quản trị", "bottom", "admin");
        btnMenuItem7.getChildrensBox().getChildren().addAll( 
            new Button_MenuItem("  Quản lý nhân viên", "manageruser", "manageruser", "admin"),
            new Button_MenuItem("  Quản lý thẻ định danh", "managercard", "managercard", "admin"),
            new Button_MenuItem("  Quản lý đồ uống", "managerdrinks", "managerdrinks", "admin"),
            new Button_MenuItem("  Quản lý loại đồ uống", "managercategory", "managercategory", "admin"),
            new Button_MenuItem("  Quản lý doanh thu", "managerrevenue", "managerrevenue", "admin")
        );
        
        btnMenuItem7.getChildrensBox().setVisible(false);
        btnMenuItem7.getChildrensBox().setManaged(false);

        icon_view = new ImageView(icon_menu);
        icon_view.setFitHeight(124);
        icon_view.setFitWidth(139);
        icon_view.setPreserveRatio(true);
        icon_view.setSmooth(true);


        logoContainer.setCenter(icon_view);
        logoContainer.setAlignment(adminContainer, Pos.TOP_CENTER);

        adminContainer.setCenter(btnMenuItem7);
        adminContainer.setAlignment(adminContainer, Pos.TOP_CENTER);

        menuBar.getChildren().addAll(
            logoContainer,
            btnMenuItem,
            btnMenuItem8,
            // btnMenuItem2,
            // btnMenuItem3,
            btnMenuItem4,
            btnMenuItem5,
            btnMenuItem6,
            adminContainer
        );
        menuBar.setAlignment(Pos.TOP_CENTER);

        adminContainer.setVisible(true);
        LOGIN.listenerEvent("Login", data -> {
            if ((Boolean) data) {
                adminContainer.setVisible(true);
            } else {
                adminContainer.setVisible(false);
            }
        });
        
        this.setMinWidth(237);
        this.setStyle("-fx-background-color: #fff; -fx-background-radius: 0 20 20 0");
        this.setPadding(new Insets(20,0,20,0));
        this.setMargin(menuBar, new Insets(45,0,0,0));
        this.setBottom(adminContainer);
        this.setCenter(menuBar);   
        this.setTop(logoContainer);
    }

    private Image icon_menu;
    private Button_MenuItem btnMenuItem, btnMenuItem2, btnMenuItem3, btnMenuItem4, btnMenuItem5, btnMenuItem8, btnLogout;
    private ButtonParent_MenuItem btnMenuItem6;
    private ButtonParent_MenuItem btnMenuItem7;
    private ImageView icon_view;
    private VBox menuBar;
    private BorderPane logoContainer;
    private BorderPane adminContainer;
    private LOGIN LOGIN;
}                               