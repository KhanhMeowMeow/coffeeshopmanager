package com.example.view.frame;

import com.example.controller.DAO.UserDAO;
import com.example.controller.DAO.DAOImpl.UserDAOImpl;
import com.example.controller.lib.FileSYSTEM;
import com.example.controller.lib.SYSTEM_SESSION;
import com.example.model.User;
import com.example.view.component.Button_View;
import com.example.view.component.Password_Field;
import com.example.view.component.Text_Field;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class ChagePasswordDialog extends Dialog {
    public ChagePasswordDialog() {

        userDAO = new UserDAOImpl();
        layoutLoginFrame = new GridPane();
        layoutButton = new HBox();
        textBox_PassswordOld = new Password_Field("Nhập mật khẩu cữ", 339, true);
        textBox_PasswordNew = new Password_Field("Mật khẩu", 339, true);
        textBox_AgainPassword = new Password_Field("Nhập lại mật khẩu", 339, true);
        btnChagePassword = new Button_View("Đổi mật khẩu", 150, 35);

        btnChagePassword.setOnMouseClicked(e -> {
            User user = userDAO.findById(SYSTEM_SESSION.idUser);
            if (user == null) {
                return;
            }
            if (!textBox_PassswordOld.getValue().equals(user.getPassword())) {
                textBox_PassswordOld.setWarning("Bạn nhập sai mật khẩu cũ");
                return;
            } else {
                textBox_PassswordOld.setWarning("");
            }
            if (!textBox_PasswordNew.getValue().equals(textBox_AgainPassword.getValue())) {
                textBox_PasswordNew.setWarning("Mật khẩu mới và nhập lại phải giống nhau");
                textBox_AgainPassword.setWarning("Mật khẩu mới và nhập lại phải giống nhau");
                return;
            } else {
                textBox_PasswordNew.setWarning("");
                textBox_AgainPassword.setWarning("");
            }

            user.setPassword(textBox_PasswordNew.getValue());
            userDAO.update(user);
            this.close();
        });
        layoutButton.getChildren().addAll(
                btnChagePassword);
        layoutButton.setSpacing(10);

        layoutLoginFrame.add(textBox_PassswordOld, 0, 0);
        layoutLoginFrame.add(textBox_PasswordNew, 0, 1);
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
        // this.setGraphic(new ImageView(FileSYSTEM.logoApp));
        this.setTitle("Đổi mật khẩu");
        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        this.getDialogPane().lookupButton(ButtonType.CLOSE).setVisible(false);
        this.getDialogPane().lookupButton(ButtonType.CLOSE).setManaged(false);
        this.getDialogPane().setStyle("-fx-background-color: white;");
    }

    private UserDAO userDAO;
    private GridPane layoutLoginFrame;
    private Button_View btnChagePassword;
    private HBox layoutButton;
    private Password_Field textBox_PassswordOld, textBox_PasswordNew, textBox_AgainPassword;
}
