package com.example.view.frame;

import java.util.Objects;

import com.example.controller.DAO.UserDAO;
import com.example.controller.DAO.DAOImpl.UserDAOImpl;
import com.example.controller.lib.FileSYSTEM;
import com.example.controller.lib.SYSTEM_SESSION;
import com.example.model.User;
import com.example.view.component.Button_View;
import com.example.view.component.Password_Field;
import com.example.view.component.Text_Field;
import com.example.view.event.LOGIN;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class SignupDialog extends Dialog{
    
    public SignupDialog(){
        userDAO = new UserDAOImpl();
        
        layoutLoginFrame = new GridPane();
        layoutButton = new HBox();
        textBox_Username = new Text_Field("Tên đăng nhập", 339, true);
        textBox_Password = new Password_Field("Mật khẩu", 339, true);
        textBox_AgainPassword = new Password_Field("Nhập lại mật khẩu", 339, true);
        btnLogin = new Button_View("Đăng nhập", 100, 35);
        btnSignup = new Button_View("Đăng ký", 100, 35);

        btnSignup.setOnMouseClicked(e -> {
            if (textBox_Username.getText().equals("")) {
                textBox_Username.setWarning("Không được để trống!!");
                return;
            }
            if (textBox_Password.getValue().equals("")) {
                textBox_Password.setWarning("Không được để trống!!");
                return;
            }
            if (textBox_AgainPassword.getValue().equals("")) {
                textBox_AgainPassword.setWarning("Không được để trống!!");
                return;
            }
            if (!textBox_Password.getValue().equals(textBox_AgainPassword.getValue())) {
                textBox_Password.setWarning("Không trùng khớp!");
                textBox_AgainPassword.setWarning("Không trùng khớp!");
                return;
            }
            User user = new User(
                textBox_Username.getText(),
                textBox_Password.getValue(),
                false,
                "notName",
                "notPhot",
                false,
                false
            );
            userDAO.create(user);
            this.close();

        });
        btnLogin.setOnMouseClicked(e -> {
            new LoginDialog().show();
            this.close();
        });
        layoutButton.getChildren().addAll(
            btnSignup,
            btnLogin
        );
        layoutButton.setSpacing(10);

        layoutLoginFrame.add(textBox_Username, 0, 0);
        layoutLoginFrame.add(textBox_Password, 0, 1);
        layoutLoginFrame.add(textBox_AgainPassword, 0, 2);
        layoutLoginFrame.add(layoutButton, 0, 3);
        layoutLoginFrame.setHgap(20);
        layoutLoginFrame.setVgap(20);
        layoutLoginFrame.setPadding(new Insets(20));
        layoutLoginFrame.getStylesheets().add(getClass().getResource("/css/styleCheckBox.css").toExternalForm());
        layoutLoginFrame.getStylesheets().add(getClass().getResource("/css/styleTextBox.css").toExternalForm());

        Font.loadFont(FileSYSTEM.fontApp.getName(), 16);
        this.getDialogPane().setContent(layoutLoginFrame);
        this.setResizable(false);
        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        this.getDialogPane().lookupButton(ButtonType.CLOSE).setVisible(false);
        this.getDialogPane().lookupButton(ButtonType.CLOSE).setManaged(false);
        this.getDialogPane().setStyle("-fx-background-color: white;");
        // this.setGraphic(new ImageView(FileSYSTEM.logoApp));
        this.setTitle("Đăng ký");
    }

    private GridPane layoutLoginFrame;
    private Button_View btnLogin, btnSignup;
    private HBox layoutButton;
    private Text_Field textBox_Username;
    private Password_Field textBox_Password, textBox_AgainPassword;
    private UserDAO userDAO;
}
