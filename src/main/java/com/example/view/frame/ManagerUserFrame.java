package com.example.view.frame;

import java.util.List;

import com.example.controller.DAO.UserDAO;
import com.example.controller.DAO.DAOImpl.UserDAOImpl;
import com.example.controller.lib.SYSTEM_SESSION;
import com.example.controller.lib.UploadImage;
import com.example.model.User;
import com.example.model.DTO.RevenueBill_UserDTO;
import com.example.view.component.Button_View;
import com.example.view.component.Group_Radio_Button;
import com.example.view.component.Image_Lable;
import com.example.view.component.Radio_Button;
import com.example.view.component.Space;
import com.example.view.component.TabPane_View;
import com.example.view.component.Tab_View;
import com.example.view.component.Table_View;
import com.example.view.component.Text_Field;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ManagerUserFrame extends BorderPane {

    public ManagerUserFrame() {

        userDAO = new UserDAOImpl();
        tabTable = new Tab_View("Danh sách nhân viên");
        layoutTable = new BorderPane();
        tableView = new Table_View("Bảng danh sách");
        textBox_Search = new Text_Field("Tìm kiếm", 500, true);
        btnSearch = new Button_View("Tìm", 80, 43);
        layoutSearch = new HBox();
        tabManager = new Tab_View("Quản lý nhân viên");
        layoutManager = new GridPane();
        textBox_UserName = new Text_Field("Tên đăng nhập", 339, true);
        textBox_Password = new Text_Field("Mật khẩu", 339, true);
        textBox_Fullname = new Text_Field("Họ tên", 339, true);
        textBox_Disable = new Text_Field("Đã xoá", 339, false);
        image_Lable = new Image_Lable(300, 400, null);
        layoutButtons = new VBox();
        btnCreate = new Button_View("Tạo mới", 100, 35);
        btnDelete = new Button_View("Xoá", 100, 35);
        btnUpdate = new Button_View("Sửa", 100, 35);
        btnClear = new Button_View("Làm sạch", 100, 35);
        layoutButtonsNatigation = new HBox();
        btnNext = new Button_View(">>", 80, 35);
        btnPrev = new Button_View("<<", 80, 35);
        btnFirst = new Button_View("|<<", 80, 35);
        btnLast = new Button_View(">>|", 80, 35);
        tabPane_View = new TabPane_View(tabTable, tabManager);

        users = FXCollections.observableArrayList();
        tableView.getTable().setItems(users);

        TableColumn<User, String> usernameCol = new TableColumn<>("Tên đăng nhập");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> passwordCol = new TableColumn<>("Mật khẩu");
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<User, Boolean> enabledCol = new TableColumn<>("Kích hoạt");
        enabledCol.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        enabledCol.setCellFactory(col -> new TableCell<User, Boolean>() {
            private final Label label = new Label();

            @Override
            protected void updateItem(Boolean disable, boolean empty) {
                super.updateItem(disable, empty);
                if (empty || disable == null) {
                    setGraphic(null);
                } else {
                    if (disable) {
                        label.setText("Còn hoạt động");
                        label.setStyle("-fx-text-fill: green;");
                    } else {
                        label.setText("Ngưng hoạt động");
                        label.setStyle("-fx-text-fill: gray;");
                    }
                    setGraphic(label);
                }
            }
        });

        TableColumn<User, String> fullnameCol = new TableColumn<>("Họ tên");
        fullnameCol.setCellValueFactory(new PropertyValueFactory<>("fullname"));

        TableColumn<User, String> photoCol = new TableColumn<>("Ảnh");
        photoCol.setCellValueFactory(new PropertyValueFactory<>("photo"));
        photoCol.setCellFactory(col -> new TableCell<User, String>() {
            @Override
            protected void updateItem(String nameImage, boolean empty) {
                super.updateItem(nameImage, empty);
                Image_Lable image_Lable = new Image_Lable(50, 50, nameImage);
                image_Lable.setStyle("-fx-padding: 5");
                if (nameImage == null || empty) {
                    setGraphic(null);
                } else {
                    setGraphic(image_Lable);
                }
            };
        });

        TableColumn<User, Boolean> managerCol = new TableColumn<>("Vai trò");
        managerCol.setCellValueFactory(new PropertyValueFactory<>("manager"));
        managerCol.setCellFactory(col -> new TableCell<User, Boolean>() {
            private final Label label = new Label();

            @Override
            protected void updateItem(Boolean disable, boolean empty) {
                super.updateItem(disable, empty);
                if (empty || disable == null) {
                    setGraphic(null);
                } else {
                    if (disable) {
                        label.setText("Quản lý");
                        label.setStyle("-fx-text-fill: red;");
                    } else {
                        label.setText("Nhân viên");
                        label.setStyle("-fx-text-fill: blue;");
                    }
                    setGraphic(label);
                }
            }
        });

        TableColumn<User, Boolean> disableCol = new TableColumn<>("Vô hiệu");
        disableCol.setCellValueFactory(new PropertyValueFactory<>("disable"));
        disableCol.setCellFactory(col -> new TableCell<User, Boolean>() {
            private final Label label = new Label();

            @Override
            protected void updateItem(Boolean disable, boolean empty) {
                super.updateItem(disable, empty);
                if (empty || disable == null) {
                    setGraphic(null);
                } else {
                    if (disable) {
                        label.setText("Đã xoá");
                        label.setStyle("-fx-text-fill: red;");
                    } else {
                        label.setText("Chưa xoá");
                        label.setStyle("-fx-text-fill: gray;");
                    }
                    setGraphic(label);
                }
            }
        });

        tableView.getTable().getColumns().addAll(usernameCol, passwordCol, enabledCol, fullnameCol, photoCol,
                managerCol, disableCol);

        fillTable(SYSTEM_SESSION.idUser != null ? userDAO.findAll(SYSTEM_SESSION.idUser) : null);

        layoutSearch.setSpacing(10);
        layoutSearch.setAlignment(Pos.CENTER);
        layoutSearch.getChildren().addAll(textBox_Search, btnSearch);

        layoutTable.setCenter(tableView);
        layoutTable.setTop(layoutSearch);
        layoutTable.setMargin(layoutTable.getCenter(), new Insets(10, 0, 0, 0));
        layoutTable.setMargin(textBox_Search, new Insets(0, 100, 0, 100));
        tabTable.setCenter(layoutTable);

        layoutManager.setVgap(10);
        layoutManager.setHgap(10);
        group_Radio_Enabled = new Group_Radio_Button<>(
                "Trạng thái hoạt động",
                339,
                new Radio_Button<Boolean>("Hoạt động", true),
                new Radio_Button<Boolean>("Dừng hoạt động", false));
        group_Radio_Manager = new Group_Radio_Button(
                "Vai trò",
                339,
                new Radio_Button<Boolean>("Quản lý", true),
                new Radio_Button<Boolean>("Nhân viên", false));
        layoutManager.add(textBox_UserName, 0, 0);
        layoutManager.add(textBox_Password, 1, 0);
        layoutManager.add(textBox_Fullname, 0, 1);
        layoutManager.add(textBox_Disable, 1, 1);
        layoutManager.add(group_Radio_Enabled, 0, 2);
        layoutManager.add(group_Radio_Manager, 1, 2);
        layoutManager.add(image_Lable, 3, 0, 1, 5);
        layoutButtons.setSpacing(10);
        layoutButtons.getChildren().addAll(
                btnCreate, btnDelete, btnUpdate, btnClear);
        layoutManager.add(layoutButtons, 4, 0, 1, 5);
        layoutButtonsNatigation.getChildren().addAll(
                btnFirst, btnPrev, btnNext, btnLast);
        layoutButtonsNatigation.setSpacing(10);
        layoutManager.add(layoutButtonsNatigation, 0, 3, 2, 1);

        tabManager.setCenter(layoutManager);

        resetForm();

        tableView.getTable().setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                index = tableView.getTable().getSelectionModel().getSelectedIndex();
                tabPane_View.focusTab(tabManager);
                getUserIndex(index);
            }
        });
        textBox_Search.setOnKeyReleased(e -> {
            if (textBox_Search.getText().isEmpty()) {
                fillTable(SYSTEM_SESSION.idUser != null ? userDAO.findAll(SYSTEM_SESSION.idUser) : null);
            }
        });
        btnSearch.setOnMouseClicked(e -> {
            if (!textBox_Search.getText().isEmpty()) {
                fillTable(userDAO.findByName(textBox_Search.getText().trim().toLowerCase()));
            }
        });
        image_Lable.setOnMouseClicked(e -> {
            image_Lable.setImage((Image) UploadImage.choseImage(this.getScene().getWindow()));
        });
        btnFirst.setOnAction(e -> {
            index = 0;
            getUserIndex(index);
        });
        btnLast.setOnAction(e -> {
            index = users.size() - 1;
            getUserIndex(index);
        });
        btnPrev.setOnAction(e -> {
            index--;
            if (index < 0) {
                index = users.size() - 1;
            }
            getUserIndex(index);
        });
        btnNext.setOnAction(e -> {
            index++;
            if (index >= users.size()) {
                index = 0;
            }
            getUserIndex(index);
        });
        btnClear.setOnAction(e -> {
            resetForm();
        });
        btnCreate.setOnAction(e -> {
            if (!validateForm()) {
                return;
            }
            if (this.userData == null) {
                userDAO.create(new User(
                        textBox_UserName.getText(),
                        textBox_Password.getText(),
                        (Boolean) group_Radio_Enabled.getValue() == null ? false
                                : (Boolean) group_Radio_Enabled.getValue(),
                        textBox_Fullname.getText(),
                        UploadImage.loadImagetoLocal((Image) image_Lable.getImage()),
                        (Boolean) group_Radio_Manager.getValue() == null ? false
                                : (Boolean) group_Radio_Manager.getValue(),
                        false));
                fillTable(SYSTEM_SESSION.idUser != null ? userDAO.findAll(SYSTEM_SESSION.idUser) : null);
                resetForm();
            }
        });
        btnUpdate.setOnAction(e -> {
            if (!validateForm()) {
                return;
            }
            if (this.userData != null) {
                userDAO.update(new User(
                        textBox_UserName.getText(),
                        textBox_Password.getText(),
                        (Boolean) group_Radio_Enabled.getValue() == null ? false
                                : (Boolean) group_Radio_Enabled.getValue(),
                        textBox_Fullname.getText(),
                        UploadImage.loadImagetoLocal((Image) image_Lable.getImage()),
                        (Boolean) group_Radio_Manager.getValue() == null ? false
                                : (Boolean) group_Radio_Manager.getValue(),
                        false));
                fillTable(SYSTEM_SESSION.idUser != null ? userDAO.findAll(SYSTEM_SESSION.idUser) : null);
                resetForm();
            }
        });
        btnDelete.setOnAction(e -> {
            userDAO.delete(this.userData.getUsername());
            fillTable(SYSTEM_SESSION.idUser != null ? userDAO.findAll(SYSTEM_SESSION.idUser) : null);
            resetForm();
        });
        this.setCenter(tabPane_View);
        this.setStyle("-fx-background-color: #fff; -fx-background-radius: 20;");
    }

    private boolean validateForm() {

        if (textBox_UserName.getText().equals("")) {
            textBox_UserName.setWarning("Bạn chưa nhập thông tin");
            return false;
        } else {
            textBox_UserName.setWarning("");
        }

        if (textBox_Fullname.getText().equals("")) {
            textBox_Fullname.setWarning("Bạn chưa nhập thông tin");
            return false;
        } else {
            textBox_Fullname.setWarning("");
        }

        if (textBox_Password.getText().equals("")) {
            textBox_Password.setWarning("Bạn chưa nhập thông tin");
            return false;
        } else {
            textBox_Password.setWarning("");
        }

        if (group_Radio_Enabled.getValue() == null) {
            group_Radio_Enabled.setWarning("Bạn chưa nhập thông tin");
            return false;
        } else {
            group_Radio_Enabled.setWarning("");
        }

        if (group_Radio_Manager.getValue() == null) {
            group_Radio_Manager.setWarning("Bạn chưa nhập thông tin");
            return false;
        } else {
            group_Radio_Manager.setWarning("");
        }

        return true;
    }

    private void resetForm() {
        textBox_UserName.setText("");
        textBox_UserName.setWarning("");
        textBox_Password.setText("");
        textBox_Password.setWarning("");
        textBox_Fullname.setText("");
        textBox_Fullname.setWarning("");
        group_Radio_Manager.setValue(null);
        group_Radio_Manager.setWarning("");
        group_Radio_Enabled.setValue(null);
        group_Radio_Enabled.setWarning("");
        textBox_Disable.setText("");
        image_Lable.showImage("");
        this.userData = null;
        index = -1;
        textBox_UserName.setStatus(true);
        btnCreate.setDisable(this.userData != null);
        btnDelete.setDisable(this.userData == null);
        btnUpdate.setDisable(this.userData == null);
    }

    public void fillTable(List<User> listUsers) {
        Platform.runLater(() -> {
            users.clear();
            users.setAll(listUsers);
        });
    }

    private void getUserIndex(int index) {
        if (users.size() > 0) {
            if (index < 0) {
                index = 0;
            }
            if (index >= users.size()) {
                index = users.size() - 1;
            }
            this.userData = users.get(index);
            textBox_UserName.setText(this.userData.getUsername());
            textBox_Password.setText(this.userData.getPassword());
            textBox_Fullname.setText(this.userData.getFullname());
            group_Radio_Manager.setValue(this.userData.isManager());
            group_Radio_Enabled.setValue(this.userData.isEnabled());
            textBox_Disable.setText(this.userData.isDisable() ? "Đã xoá" : "Chưa xoá");
            image_Lable.showImage(this.userData.getPhoto());
            textBox_UserName.setStatus(false);

            textBox_UserName.setStatus(false);
            btnCreate.setDisable(this.userData != null);
            btnDelete.setDisable(this.userData == null);
            btnUpdate.setDisable(this.userData == null);
        }
    }

    private int index = -1;
    private TabPane_View tabPane_View;
    private Tab_View tabTable, tabManager;
    private BorderPane layoutTable;
    private UserDAO userDAO;
    private User userData;
    private Text_Field textBox_UserName, textBox_Password, textBox_Fullname,
            textBox_Disable, textBox_Search;
    private GridPane layoutManager;
    private VBox layoutButtons;
    private HBox layoutButtonsNatigation, layoutSearch;
    private Button_View btnCreate, btnDelete, btnUpdate, btnClear, btnNext, btnPrev, btnFirst, btnLast, btnSearch;
    private Image_Lable image_Lable;
    private Group_Radio_Button<Boolean> group_Radio_Manager, group_Radio_Enabled;
    private Table_View tableView;
    private ObservableList<User> users;
}
