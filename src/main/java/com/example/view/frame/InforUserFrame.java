package com.example.view.frame;

import com.example.controller.DAO.UserDAO;
import com.example.controller.DAO.DAOImpl.UserDAOImpl;
import com.example.controller.lib.SYSTEM_SESSION;
import com.example.model.User;
import com.example.view.component.Button_View;
import com.example.view.component.Image_Lable;
import com.example.view.component.Password_Field;
import com.example.view.component.Text_Field;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class InforUserFrame extends BorderPane{
    public InforUserFrame(){

        userDAO = new UserDAOImpl();

        layoutManager = new GridPane();
        btnUpdate = new Button_View("Cập nhật", 100, 35);
        image_Lable = new Image_Lable(300, 400, null);
        textBox_UserName = new Text_Field("Tên đăng nhập", 339, false);
        password_Field = new Password_Field("Mật khẩu", 339, false);
        textBox_Fullname = new Text_Field("Tên người dùng", 339, false);
        textBox_Manager = new Text_Field("Vai trò", 339, false);

        layoutManager.setHgap(10);
        layoutManager.setVgap(10);
        layoutManager.add(textBox_UserName, 0, 0);
        layoutManager.add(password_Field, 1, 0);
        layoutManager.add(textBox_Fullname, 0, 1);
        layoutManager.add(textBox_Manager, 1, 1);
        layoutManager.add(image_Lable, 2, 0, 1, 3);

        showUser();
        this.setCenter(layoutManager);
        this.setStyle("-fx-background-color: #fff; -fx-background-radius: 20; -fx-padding: 20");
    }

    private void showUser(){
        if (SYSTEM_SESSION.idUser != null) {
            User user = userDAO.findById(SYSTEM_SESSION.idUser);
            textBox_UserName.setText(user.getUsername());
            textBox_Fullname.setText(user.getFullname());
            textBox_Manager.setText(user.isManager() ? "Quản lý" : "Nhân viên");
            image_Lable.showImage(user.getPhoto());
            password_Field.setValue(user.getPassword());
        }
    }

    private UserDAO userDAO;
    private GridPane layoutManager;
    private Button_View btnUpdate;
    private Image_Lable image_Lable;
    private Text_Field textBox_UserName, textBox_Fullname, textBox_Manager;
    private Password_Field password_Field;
}
