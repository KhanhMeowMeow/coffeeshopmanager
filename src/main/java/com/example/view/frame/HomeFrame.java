package com.example.view.frame;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.controller.DAO.BillDAO;
import com.example.controller.DAO.BillDetailDAO;
import com.example.controller.DAO.CardDAO;
import com.example.controller.DAO.DrinkDAO;
import com.example.controller.DAO.UserDAO;
import com.example.controller.DAO.DAOImpl.BillDAOImpl;
import com.example.controller.DAO.DAOImpl.BillDetailDAOImpl;
import com.example.controller.DAO.DAOImpl.CardDAOImpl;
import com.example.controller.DAO.DAOImpl.DrinkDAOImpl;
import com.example.controller.DAO.DAOImpl.UserDAOImpl;
import com.example.controller.lib.SYSTEM_SESSION;
import com.example.model.Bill;
import com.example.model.BillDetail;
import com.example.model.Card;
import com.example.model.Drink;
import com.example.model.User;
import com.example.model.DTO.BillDTO;
import com.example.model.DTO.BillDTOCreate;
import com.example.model.DTO.BillDetailDTO;
import com.example.model.DTO.Bill_DrinkOrder;
import com.example.model.DTO.DrinkDTO;
import com.example.view.component.Button_View;
import com.example.view.component.Image_Lable;
import com.example.view.component.Label_View;
import com.example.view.component.TabPane_View;
import com.example.view.component.Tab_View;
import com.example.view.component.Table_View;
import com.example.view.component.Text_Field;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Spinner;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeFrame extends BorderPane {

    public HomeFrame() {

        // Xây dựng các thành phần giao diện
        billDAO = new BillDAOImpl();
        billDetailDAO = new BillDetailDAOImpl();
        drinkDAO = new DrinkDAOImpl();
        userDAO = new UserDAOImpl();
        cardDAO = new CardDAOImpl();
        tab_Table = new Tab_View("Danh sách đơn");
        tab_Manager = new Tab_View("Quản lý đơn");
        tabPane_View = new TabPane_View(tab_Table, tab_Manager);
        table_View_ListOders = new Table_View("Các đơn đã lên");
        btn_NewOder = new Button_View("Tạo đơn mới", 200, 43);
        layoutTabManager = new GridPane();
        textBox_BillId = new Text_Field("Mã đơn", 339, false);
        textBox_Fullname = new Text_Field("Người lên đơn", 339, false);
        textBox_Checkin = new Text_Field("Ngày tạo đơn", 339, false);
        textBox_Checkout = new Text_Field("Ngày thanh toán", 339, false);
        textBox_Status = new Text_Field("Trạng thái", 339, false);
        label_CardId = new Label_View("Chọn thẻ", 339);
        btnFirst = new Button_View("|<", 60, 35);
        btnPrev = new Button_View("<<", 60, 35);
        btnNext = new Button_View(">>", 60, 35);
        btnLast = new Button_View(">|", 60, 35);
        btnOrder = new Button_View("Đặt đơn", 100, 35);
        btnUpdate = new Button_View("Cập nhật", 100, 35);
        btnDelete = new Button_View("Xóa", 100, 35);
        btn_Pay = new Button_View("Thanh toán", 100, 35);
        btnAddDrink = new Button_View("Thêm đồ uống", 150, 43);
        layoutButtonsNatigation = new HBox(10, btnFirst, btnPrev, btnNext, btnLast);
        layoutButtonsCRUD = new VBox(10, btnOrder, btnDelete, btn_Pay);
        table_View_ListDrinds = new Table_View("Các đồ uống đã gọi");

        label_CardId.setDisable(true);

        bills = FXCollections.observableArrayList();
        bill_DrinkOrderDTOs = FXCollections.observableArrayList();

        table_View_ListOders.getTable().setItems(bills);
        tab_Table.setCenter(table_View_ListOders);

        TableColumn<BillDTO, String> col_Id = new TableColumn<>("Mã đơn");
        col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<BillDTO, String> col_fullname = new TableColumn<>("Người lên đơn");
        col_fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));

        TableColumn<BillDTO, String> col_cardId = new TableColumn<>("Mã thẻ");
        col_cardId.setCellValueFactory(new PropertyValueFactory<>("cardId"));

        TableColumn<BillDTO, Date> col_checkin = new TableColumn<>("Ngày tạo đơn");
        col_checkin.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        col_checkin.setCellFactory(column -> new TableCell<BillDTO, Date>() {
            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(new SimpleDateFormat("HH:mm - dd/MM/yy").format(item));
                }
            }
        });

        TableColumn<BillDTO, Date> col_checkout = new TableColumn<>("Ngày thanh toán");
        col_checkout.setCellValueFactory(new PropertyValueFactory<>("checkout"));
        col_checkout.setCellFactory(column -> new TableCell<BillDTO, Date>() {
            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(new SimpleDateFormat("HH:mm - dd/MM/yy").format(item));
                }
            }
        });

        TableColumn<BillDTO, Integer> col_status = new TableColumn<>("Trạng thái");
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_status.setCellFactory(col -> new TableCell<BillDTO, Integer>() {
            private final Label label = new Label();

            @Override
            protected void updateItem(Integer status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setGraphic(null);
                } else {
                    if (status == 0) {
                        label.setText("Đang chọn món");
                        label.setStyle("-fx-text-fill: red;");
                    }
                    if (status == 1) {
                        label.setText("Đang phục vụ");
                        label.setStyle("-fx-text-fill: blue;");
                    }
                    if (status == 2) {
                        label.setText("Đã thanh toán");
                        label.setStyle("-fx-text-fill: green;");
                    }
                    setGraphic(label);
                }
            }
        });

        table_View_ListOders.getTable().getColumns().addAll(col_Id, col_fullname, col_cardId, col_checkin, col_checkout,
                col_status);

        tab_Table.setBottom(btn_NewOder);
        tab_Table.setAlignment(tab_Table.getBottom(), Pos.CENTER);
        tab_Table.setMargin(tab_Table.getBottom(), new Insets(10, 0, 0, 0));
        fillTableBills(billDAO.findAllByDate(new Date()));

        layoutTabManager.setHgap(10);
        layoutTabManager.setVgap(10);

        layoutButtonsNatigation.setAlignment(Pos.CENTER_LEFT);

        layoutButtonsCRUD.setAlignment(Pos.CENTER);

        layoutTabManager.add(textBox_BillId, 0, 0);
        layoutTabManager.add(textBox_Fullname, 1, 0);
        layoutTabManager.add(label_CardId, 2, 0);
        layoutTabManager.add(textBox_Checkin, 0, 1);
        layoutTabManager.add(textBox_Checkout, 1, 1);
        layoutTabManager.add(textBox_Status, 2, 1);
        layoutTabManager.add(layoutButtonsNatigation, 0, 3, 4, 1);
        layoutTabManager.add(layoutButtonsCRUD, 3, 0, 1, 2);

        table_View_ListDrinds.getTable().setItems(bill_DrinkOrderDTOs);

        TableColumn<Bill_DrinkOrder, String> col_delete = new TableColumn<>("Xoá");
        col_delete.setCellValueFactory(new PropertyValueFactory<>(""));

        TableColumn<Bill_DrinkOrder, String> col_drinkname = new TableColumn<>("Tên đồ uống");
        col_drinkname.setCellValueFactory(new PropertyValueFactory<>("drinkname"));

        TableColumn<Bill_DrinkOrder, String> image_Col = new TableColumn<>("Ảnh");
        image_Col.setCellValueFactory(new PropertyValueFactory<>("image"));
        image_Col.setCellFactory(col -> new TableCell<Bill_DrinkOrder, String>() {
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

        TableColumn<Bill_DrinkOrder, String> col_price = new TableColumn<>("Giá");
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Bill_DrinkOrder, Integer> col_quantity = new TableColumn<>("Số lượng");
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_quantity.setCellFactory(col -> new TableCell<Bill_DrinkOrder, Integer>() {
            private final Spinner<Integer> spinner = new Spinner<>(0, 100, 1);
            {
                spinner.setEditable(false);
                spinner.setPrefHeight(40);
                spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
                    BillDetail billDetail = billDetailDAO
                            .findById(getTableView().getItems().get(getIndex()).getId());
                    if (billDetail != null) {
                        billDetail.setQuantity(newValue);
                        billDetailDAO.update(billDetail);
                    }
                    if (newValue == 0) {
                        billDetailDAO.delete(billDetail.getId());
                    }
                    fillTableDrinks(billDetailDAO.findBillDrinkOrdersByBillId(billDetail.getBillId()));
                });
            }

            @Override
            protected void updateItem(Integer value, boolean empty) {
                super.updateItem(value, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    spinner.getValueFactory().setValue(value);
                    setGraphic(spinner);
                }
            }
        });

        TableColumn<Bill_DrinkOrder, String> col_total = new TableColumn<>("Thành tiền");
        col_total.setCellValueFactory(new PropertyValueFactory<>("total"));

        table_View_ListDrinds.getTable().getColumns().addAll(col_drinkname, image_Col, col_price, col_quantity,
                col_total);

        tab_Manager.setTop(layoutTabManager);
        tab_Manager.setCenter(table_View_ListDrinds);
        tab_Manager.setBottom(btnAddDrink);
        tab_Manager.setAlignment(tab_Manager.getBottom(), Pos.CENTER);
        tab_Manager.setMargin(tab_Manager.getCenter(), new Insets(10, 0, 10, 0));

        // Add các sự kiện
        btnAddDrink.setOnMouseClicked(e -> {
            if (this.bill.getStatus() == 1) {
                this.bill.setStatus(0);
                billDAO.update(this.bill);
                fillTableBills(billDAO.findAllByDate(new Date()));
                getBillIndex(index);
            }
            Optional<Long> result = new ChoseDrinksDialog().showAndWait();
            result.ifPresent(idDrink -> {
                Drink drink = drinkDAO.findById(idDrink);
                if (drink != null) {
                    billDetailDAO.create(new BillDetailDTO(
                            this.bill.getId(),
                            drink.getId(),
                            drink.getUnitPrice(),
                            drink.getDiscount(),
                            1,
                            false));
                }
                fillTableDrinks(billDetailDAO.findBillDrinkOrdersByBillId(this.bill.getId()));
            });
        });
        btn_NewOder.setOnMouseClicked(e -> {
            Optional<Card> result = new ChoseCardDialog().showAndWait();
            result.ifPresent(selectedCard -> {
                cardIdChose = selectedCard.getId();
            });
            User user = userDAO.findById(SYSTEM_SESSION.idUser);
            billDAO.create(new BillDTOCreate(
                    user.getUsername(),
                    cardIdChose,
                    new Date(),
                    null,
                    0,
                    false));
            fillTableBills(billDAO.findAllByDate(new Date()));
        });
        table_View_ListOders.getTable().setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                index = table_View_ListOders.getTable().getSelectionModel().getSelectedIndex();
                getBillIndex(index);
                tabPane_View.focusTab(tab_Manager);
            }
        });
        btnFirst.setOnAction(e -> {
            index = 0;
            getBillIndex(index);
        });
        btnLast.setOnAction(e -> {
            index = bills.size() - 1;
            getBillIndex(index);

        });
        btnPrev.setOnAction(e -> {
            index--;
            if (index < 0) {
                index = bills.size() - 1;
            }
            getBillIndex(index);
        });
        btnNext.setOnAction(e -> {
            index++;
            if (index >= bills.size()) {
                index = 0;
            }
            getBillIndex(index);
            if (this.bill.getStatus() == 2) {
                this.bill = null;
                return;
            }
        });
        btnOrder.setOnAction(e -> {
            List<Bill_DrinkOrder> listbDrinkBills = billDetailDAO.findBillDrinkOrdersByBillId(this.bill.getId());
            if (listbDrinkBills.size() <= 0 || listbDrinkBills == null) {
                return;
            }
            this.bill.setStatus(1);
            billDAO.update(this.bill);
            fillTableBills(billDAO.findAllByDate(new Date()));
            tabPane_View.focusTab(tab_Table);
            this.bill = null;
            resetForm();
        });
        btn_Pay.setOnAction(e -> {
            if (this.bill.getStatus() == 0 || this.bill.getStatus() == 2) {
                return;
            }
            List<Bill_DrinkOrder> listbDrinkBills = billDetailDAO.findBillDrinkOrdersByBillId(this.bill.getId());
            if (listbDrinkBills.size() <= 0 || listbDrinkBills == null) {
                return;
            }
            Card card = cardDAO.findbyId(this.bill.getCardId());
            if (card != null) {
                card.setStatus(0);
                cardDAO.update(card);
            }
            this.bill.setStatus(2);
            this.bill.setCheckout(new Date());
            billDAO.update(bill);
            fillTableBills(billDAO.findAllByDate(new Date()));
            tabPane_View.focusTab(tab_Table);
            this.bill = null;
            resetForm();
        });
        btnDelete.setOnAction(e -> {
            if (this.bill.getStatus() == 1 || this.bill.getStatus() == 2) {
                return;
            }
            Card card = cardDAO.findbyId(this.bill.getCardId());
            if (card != null) {
                card.setStatus(0);
                cardDAO.update(card);
            }
            billDAO.delete(bill.getId());
            this.bill = null;
            resetForm();
            fillTableBills(billDAO.findAllByDate(new Date()));
            tabPane_View.focusTab(tab_Table);
        });
        this.setCenter(tabPane_View);
        this.setStyle("-fx-background-color: white; -fx-background-radius: 20;");
    }

    private void getBillIndex(int index) {
        if (bills.size() > 0) {
            if (index < 0) {
                index = 0;
            }
            if (index >= bills.size()) {
                index = bills.size() - 1;
            }
            this.bill = billDAO.findById(bills.get(index).getId());
            if (this.bill != null) {
                textBox_BillId.setText(this.bill.getId() + "");
                textBox_Fullname.setText(bills.get(index).getFullname());
                textBox_Checkin.setText(this.bill.getCheckin() + "");
                textBox_Checkout.setText(this.bill.getCheckout() + "");
                textBox_Status.setText(bill.getStatus() == 0 ? "Đang lên đơn"
                        : bill.getStatus() == 1 ? "Đang phục vụ" : "Đã thanh toán");
                label_CardId.setValue(this.bill.getCardId() + "");
                fillTableDrinks(billDetailDAO.findBillDrinkOrdersByBillId(this.bill.getId()));
            }

            table_View_ListDrinds.getTable().setDisable(this.bill.getStatus() == 2 || this.bill.getStatus() == 1);
            btnOrder.setDisable(this.bill.getStatus() == 2 || this.bill.getStatus() == 1);
            btn_Pay.setDisable(this.bill.getStatus() == 2 || this.bill.getStatus() == 0);
            btnDelete.setDisable(this.bill.getStatus() == 2 || this.bill.getStatus() == 1);
            btnAddDrink.setDisable(this.bill.getStatus() == 2);
        }
    }

    private void resetForm() {
        textBox_BillId.setText("");
        textBox_Checkin.setText("");
        textBox_Checkout.setText("");
        textBox_Fullname.setText("");
        textBox_Status.setText("");
        label_CardId.setValue("");
        bill_DrinkOrderDTOs.clear();
        this.bill = null;
        this.index = -1;

        btnOrder.setDisable(true);
        btn_Pay.setDisable(true);
        btnDelete.setDisable(true);
        table_View_ListDrinds.getTable().setDisable(true);
        btnAddDrink.setDisable(true);
    }

    private void fillTableBills(List<BillDTO> listBillDTOs) {
        Platform.runLater(() -> {
            bills.clear();
            bills.setAll(listBillDTOs);
        });
    }

    private void fillTableDrinks(List<Bill_DrinkOrder> lisBill_tDrinkOrderDTOs) {
        Platform.runLater(() -> {
            bill_DrinkOrderDTOs.clear();
            bill_DrinkOrderDTOs.setAll(lisBill_tDrinkOrderDTOs);
        });
    }

    private Long cardIdChose;
    private int index = -1;
    private Tab_View tab_Table, tab_Manager;
    private TabPane_View tabPane_View;
    private Table_View table_View_ListOders, table_View_ListDrinds;
    private GridPane layoutTabManager;
    private ObservableList<BillDTO> bills;
    private ObservableList<Bill_DrinkOrder> bill_DrinkOrderDTOs;
    private UserDAO userDAO;
    private DrinkDAO drinkDAO;
    private BillDAO billDAO;
    private CardDAO cardDAO;
    private BillDetailDAO billDetailDAO;
    private Text_Field textBox_BillId, textBox_Fullname, textBox_Checkin, textBox_Checkout, textBox_Status;
    private Button_View btn_NewOder, btnOrder, btnDelete, btnUpdate, btnClear, btnNext, btnPrev, btnFirst, btnLast,
            btnAddDrink, btn_Pay;
    private Label_View label_CardId;
    private Bill bill;
    private HBox layoutButtonsNatigation;
    private VBox layoutButtonsCRUD;
}
