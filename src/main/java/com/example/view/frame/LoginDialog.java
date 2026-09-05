package com.example.view.frame;

import com.example.App;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class LoginDialog extends Dialog {

    public LoginDialog() {
        LOGIN = new LOGIN();
        userDAO = new UserDAOImpl();

        layoutLoginFrame = new GridPane();
        layoutButton = new HBox();
        textBox_Username = new Text_Field("Tên đăng nhập", 339, true);
        textBox_Password = new Password_Field("Mật khẩu", 339, true);
        btnLogin = new Button_View("Đăng nhập", 100, 35);
        btnClose = new Button_View("Tắt", 100, 35);

        btnLogin.setOnMouseClicked(e -> {
            User user = userDAO.findById("khanh");
            // if (user == null) {
            //     textBox_Username.setWarning("Không tìm thấy người dùng!");
            //     return;
            // } else {
            //     textBox_Username.setWarning("");
            // }
            // if (!user.isEnabled()) {
            //     textBox_Username.setWarning("Người dùng tạm vắng!");
            //     return;
            // } else {
            //     textBox_Username.setWarning("");
            // }
            // if (!user.getUsername().equals(textBox_Username.getText())) {
            //     textBox_Username.setWarning("Không tìm thấy người dùng!");
            //     return;
            // } else {
            //     textBox_Username.setWarning("");
            // }
            // if (!user.getPassword().equals(textBox_Password.getValue())) {
            //     textBox_Password.setWarning("Sai mật khẩu!");
            //     return;
            // } else {
            //     textBox_Password.setWarning("");
            // }
            // if (textBox_Username.getText().equals("")) {
            //     textBox_Username.setWarning("Bạn chưa nhập tên người dùng!");
            //     return;
            // } else {
            //     textBox_Username.setWarning("");
            // }
            // if (textBox_Password.getValue().equals("")) {
            //     textBox_Password.setWarning("Bạn chưa nhập mật khẩu!");
            //     return;
            // } else {
            //     textBox_Password.setWarning("");
            // }

            LOGIN.fireEvent("Login", user.isManager());
            SYSTEM_SESSION.idUser = user.getUsername();
            this.close();
            App.stage.show();
        });

        btnClose.setOnMouseClicked(e -> {
            this.close();
        });

        layoutButton.getChildren().addAll(
                btnLogin,
                btnClose);
        layoutButton.setSpacing(10);

        layoutLoginFrame.add(textBox_Username, 0, 0);
        layoutLoginFrame.add(textBox_Password, 0, 1);
        layoutLoginFrame.add(layoutButton, 0, 2);
        layoutLoginFrame.setHgap(20);
        layoutLoginFrame.setVgap(20);
        layoutLoginFrame.setPadding(new Insets(20));
        layoutLoginFrame.getStylesheets().add(getClass().getResource("/css/styleCheckBox.css").toExternalForm());
        layoutLoginFrame.getStylesheets().add(getClass().getResource("/css/styleTextBox.css").toExternalForm());
        this.getDialogPane().setContent(layoutLoginFrame);
        this.setResizable(false);
        Font.loadFont(FileSYSTEM.fontApp.getName(), 16);
        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        this.getDialogPane().lookupButton(ButtonType.CLOSE).setVisible(false);
        this.getDialogPane().lookupButton(ButtonType.CLOSE).setManaged(false);
        this.getDialogPane().setStyle("-fx-background-color: white;");
        // this.setGraphic(new ImageView(FileSYSTEM.logoApp));
        this.setTitle("Đăng nhập");
    }

    private GridPane layoutLoginFrame;
    private Button_View btnLogin, btnClose;
    private HBox layoutButton;
    private Text_Field textBox_Username;
    private Password_Field textBox_Password;
    private UserDAO userDAO;
    private LOGIN LOGIN;
}
