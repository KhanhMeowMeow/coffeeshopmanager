package com.example.view.frame;

import java.util.List;

import com.example.controller.DAO.CardDAO;
import com.example.controller.DAO.DAOImpl.CardDAOImpl;
import com.example.model.Card;
import com.example.model.DTO.CardDTO;
import com.example.view.component.Button_View;
import com.example.view.component.Group_Radio_Button;
import com.example.view.component.Radio_Button;
import com.example.view.component.Space;
import com.example.view.component.TabPane_View;
import com.example.view.component.Tab_View;
import com.example.view.component.Table_View;
import com.example.view.component.Text_Field;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ManagerCardFrame extends BorderPane {

    public ManagerCardFrame() {

        cardDAO = new CardDAOImpl();

        tabTable = new Tab_View("Bảng danh sách thẻ");
        layoutTable = new BorderPane();
        table_View = new Table_View("Bảng danh sách");
        tabManager = new Tab_View("Quản lý thẻ");
        layoutManager = new GridPane();
        textBox_Id = new Text_Field("Mã thẻ", 339, false);
        group_Radio_Button = new Group_Radio_Button(
                "Trạng thái thẻ",
                339,
                new Radio_Button<Integer>("Đang hoạt đông", 0),
                new Radio_Button<Integer>("Đang phục vụ", 1),
                new Radio_Button<Integer>("Ngưng hoạt động", 2));
        textBox_Disable = new Text_Field("Đã xoá", 339, false);
        layoutButtons = new VBox();
        btnCreate = new Button_View("Tạo mới", 100, 35);
        btnDelete = new Button_View("Xoá", 100, 35);
        btnUpdate = new Button_View("Sửa", 100, 35);
        btnClear = new Button_View("Làm sạch", 100, 35);
        layoutManager.add(new Space(0, 35), 0, 2, 2, 1);
        tabPane_View = new TabPane_View(tabTable, tabManager);
        layoutNavigation = new HBox();
        btnNext = new Button_View(">>", 80, 35);
        btnPrev = new Button_View("<<", 80, 35);
        btnFirst = new Button_View("|<<", 80, 35);
        btnLast = new Button_View(">>|", 80, 35);

        layoutNavigation.setSpacing(10);

        card_ObservableList = FXCollections.observableArrayList();
        table_View.getTable().setItems(card_ObservableList);

        TableColumn<Card, String> col_Id = new TableColumn<>("Mã thẻ");
        col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Card, Integer> col_status = new TableColumn<>("Trạng thái thẻ");
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_status.setCellFactory(col -> new TableCell<Card, Integer>() {
            private final Label label = new Label();

            @Override
            protected void updateItem(Integer status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setGraphic(null);
                } else {
                    if (status == 0) {
                        label.setText("Còn hoạt động");
                        label.setStyle("-fx-text-fill: green;");
                    }
                    if (status == 1) {
                        label.setText("Đang phục vụ");
                        label.setStyle("-fx-text-fill: blue;");
                    }
                    if (status == 2) {
                        label.setText("Ngưng hoạt động");
                        label.setStyle("-fx-text-fill: red;");
                    }
                    setGraphic(label);
                }
            }
        });

        TableColumn<Card, Boolean> col_disable = new TableColumn<>("Đã xoá");
        col_disable.setCellValueFactory(new PropertyValueFactory<>("disable"));
        col_disable.setCellFactory(col -> new TableCell<Card, Boolean>() {
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

        table_View.getTable().getColumns().addAll(col_Id, col_status, col_disable);

        fillTable_Card(cardDAO.findAll());

        layoutTable.setCenter(table_View);
        tabTable.setCenter(layoutTable);

        layoutManager.setVgap(10);
        layoutManager.setHgap(10);
        layoutManager.add(textBox_Id, 0, 0);
        layoutManager.add(group_Radio_Button, 0, 1, 2, 1);
        layoutManager.add(textBox_Disable, 1, 0);
        layoutButtons.setSpacing(10);
        layoutButtons.getChildren().addAll(
                btnCreate, btnDelete, btnUpdate, btnClear);
        layoutNavigation.getChildren().addAll(
                btnFirst, btnPrev, btnNext, btnLast);
        layoutManager.add(layoutButtons, 2, 0, 1, 3);
        layoutManager.add(layoutNavigation, 0, 3, 2, 1);

        tabManager.setCenter(layoutManager);

        btnFirst.setOnAction(e -> {
            index = 0;
            getCardIndex(index);
        });
        btnLast.setOnAction(e -> {
            index = card_ObservableList.size() - 1;
            getCardIndex(index);
        });
        btnPrev.setOnAction(e -> {
            index--;
            if (index < 0) {
                index = card_ObservableList.size() - 1;
            }
            getCardIndex(index);
        });
        btnNext.setOnAction(e -> {
            index++;
            if (index >= card_ObservableList.size()) {
                index = 0;
            }
            getCardIndex(index);
        });
        table_View.getTable().setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                this.index = table_View.getTable().getSelectionModel().getSelectedIndex();
                getCardIndex(this.index);
                tabPane_View.focusTab(tabManager);
            }
        });
        btnClear.setOnAction(e -> {
            resetForm();
        });
        btnCreate.setOnAction(e -> {
            if (!validateForm()) {
                return;
            }
            if (this.cardData == null) {
                cardDAO.create(new CardDTO((Integer) group_Radio_Button.getValue(), false));
                resetForm();
                fillTable_Card(cardDAO.findAll());
            }
        });
        btnDelete.setOnAction(e -> {
            if (this.cardData != null) {
                cardDAO.delete(this.cardData.getId());
                resetForm();
                fillTable_Card(cardDAO.findAll());
            }
        });
        btnUpdate.setOnAction(e -> {
            if (!validateForm()) {
                return;
            }
            if (this.cardData != null) {
                cardDAO.update(
                        new Card(Long.valueOf(textBox_Id.getText()), (Integer) group_Radio_Button.getValue(), false));
                fillTable_Card(cardDAO.findAll());
                resetForm();

            }
        });
        resetForm();
        this.setCenter(tabPane_View);
        this.setStyle("-fx-background-color: #fff; -fx-background-radius: 20;");
    }

    private void fillTable_Card(List<Card> listCard) {
        Platform.runLater(() -> {
            card_ObservableList.clear();
            card_ObservableList.setAll(listCard);
        });
    }

    private void resetForm() {
        textBox_Id.setText("");
        group_Radio_Button.setValue(null);
        group_Radio_Button.setWarning("");
        textBox_Disable.setText("");

        this.cardData = null;
        this.index = -1;

        btnCreate.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void getCardIndex(int index) {
        if (index < 0) {
            index = 0;
        }
        if (index >= card_ObservableList.size()) {
            index = card_ObservableList.size() - 1;
        }
        this.cardData = cardDAO.findbyId(card_ObservableList.get(index).getId());
        if (this.cardData != null) {
            textBox_Id.setText(this.cardData.getId() + "");
            group_Radio_Button.setValue(this.cardData.getStatus());
            textBox_Disable.setText(this.cardData.isDisable() ? "Đã xoá" : "Chưa xoá");
        }

        btnCreate.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    private boolean validateForm() {
        if (group_Radio_Button.getValue() == null) {
            group_Radio_Button.setWarning("Bạn chưa nhập thông tin");
            return false;
        } else {
            group_Radio_Button.setWarning("");
        }
        return true;
    }

    private int index;
    private ObservableList<Card> card_ObservableList;
    private TabPane_View tabPane_View;
    private Tab_View tabTable, tabManager;
    private BorderPane layoutTable;
    private Table_View table_View;
    private CardDAO cardDAO;
    private Card cardData;
    private Text_Field textBox_Disable, textBox_Id;
    private Group_Radio_Button<Integer> group_Radio_Button;
    private GridPane layoutManager;
    private HBox layoutNavigation;
    private VBox layoutButtons;
    private Button_View btnCreate, btnDelete, btnUpdate, btnClear, btnFirst, btnLast, btnNext, btnPrev;

}
