package com.example.view.frame;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.controller.DAO.RevenueDAO;
import com.example.controller.DAO.DAOImpl.RevenueDAOImpl;
import com.example.controller.lib.DateTimeSYSTEM;
import com.example.controller.lib.SYSTEM_SESSION;
import com.example.model.DTO.RevenueBill_UserDTO;
import com.example.view.component.Date_Picker;
import com.example.view.component.Group_Radio_Button;
import com.example.view.component.Radio_Button;
import com.example.view.component.Table_View;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class HistoriesFrame extends BorderPane {
    public HistoriesFrame() {

        revenueDAO = new RevenueDAOImpl();

        lauyoutHeader = new GridPane();
        lauyoutHeader.setHgap(10);
        lauyoutHeader.setVgap(10);
        date_From = new Date_Picker("Từ ngày", 339);
        date_To = new Date_Picker("Đến ngày", 339);
        group_Radio_Button_Time_Frame = new Group_Radio_Button<>(
                "Khung thời gian",
                439,
                new Radio_Button("Ngày", 0),
                new Radio_Button("Tuần", 1),
                new Radio_Button("Tháng", 2),
                new Radio_Button("Năm", 3));

        lauyoutHeader.add(date_From, 0, 0);
        lauyoutHeader.add(date_To, 1, 0);
        lauyoutHeader.add(group_Radio_Button_Time_Frame, 2, 0);

        table_Histories = new Table_View("Lịch sử lên đơn");
        revenueBill_UserDTOs = FXCollections.observableArrayList();
        table_Histories.getTable().setItems(revenueBill_UserDTOs);

        TableColumn<RevenueBill_UserDTO, String> col_Id = new TableColumn<>("Mã đơn");
        col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<RevenueBill_UserDTO, String> col_CardId = new TableColumn<>("Mã thẻ");
        col_CardId.setCellValueFactory(new PropertyValueFactory<>("cardId"));

        TableColumn<RevenueBill_UserDTO, Date> col_Checkin = new TableColumn<>("Ngày tạo đơn");
        col_Checkin.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        col_Checkin.setCellFactory(column -> new TableCell<RevenueBill_UserDTO, Date>() {
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

        TableColumn<RevenueBill_UserDTO, Date> col_Checkout = new TableColumn<>("Ngày thanh toán");
        col_Checkout.setCellValueFactory(new PropertyValueFactory<>("checkout"));
        col_Checkout.setCellFactory(column -> new TableCell<RevenueBill_UserDTO, Date>() {
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

        TableColumn<RevenueBill_UserDTO, Integer> col_Status = new TableColumn<>("Trạng thái");
        col_Status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_Status.setCellFactory(col -> new TableCell<RevenueBill_UserDTO, Integer>() {
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

        table_Histories.getTable().getColumns().addAll(col_Id, col_CardId, col_Checkin, col_Checkout, col_Status);

        fillTableBill_User(revenueDAO.findAllBill_User(SYSTEM_SESSION.idUser));

        date_From.getDate().valueProperty().addListener((obs, oldDate, newDate) -> {
            this.dateFrom = DateTimeSYSTEM.toUtilDate(newDate);
            fillTableBill_UserToAndFrom(this.dateFrom, this.dateTo);
        });

        date_To.getDate().valueProperty().addListener((obs, oldDate, newDate) -> {
            this.dateTo = DateTimeSYSTEM.toUtilDate(newDate);
            fillTableBill_UserToAndFrom(this.dateFrom, this.dateTo);
        });

        group_Radio_Button_Time_Frame.getChecked().selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                Radio_Button selected = (Radio_Button) newToggle;
                if ((int) selected.getValue() == 0) {
                    fillTableBill_User(revenueDAO.findByDateBill_User(SYSTEM_SESSION.idUser, new Date()));
                }
                if ((int) selected.getValue() == 1) {
                    fillTableBill_User(revenueDAO.findByWeekBill_User(SYSTEM_SESSION.idUser, new Date()));
                }
                if ((int) selected.getValue() == 2) {
                    fillTableBill_User(revenueDAO.findByMonthBill_User(SYSTEM_SESSION.idUser, new Date()));
                }
                if ((int) selected.getValue() == 3) {
                    fillTableBill_User(revenueDAO.findByYearBill_User(SYSTEM_SESSION.idUser, new Date()));
                }
            }
        });

        this.setTop(lauyoutHeader);
        this.setCenter(table_Histories);
        this.setMargin(this.getCenter(), new Insets(10, 0, 0, 0));
        this.setStyle("-fx-background-color: white; -fx-background-radius: 20; -fx-padding: 20");
    }

    private void fillTableBill_User(List<RevenueBill_UserDTO> listData) {
        Platform.runLater(() -> {
            revenueBill_UserDTOs.clear();
            revenueBill_UserDTOs.setAll(listData);
        });
    }

    private void fillTableBill_UserToAndFrom(Date dateFrom, Date dateTo) {
        if (dateFrom != null && dateTo == null) {
            fillTableBill_User(revenueDAO.findDate_FromBill_User(SYSTEM_SESSION.idUser, dateFrom));
        }
        if (dateFrom == null && dateTo != null) {
            fillTableBill_User(revenueDAO.findDate_ToBill_User(SYSTEM_SESSION.idUser, dateTo));
        }
        if (dateFrom != null && dateTo != null) {
            fillTableBill_User(revenueDAO.findDate_ToANdBill_User(SYSTEM_SESSION.idUser, dateFrom, dateTo));
        }
    }

    private Date dateTo;
    private Date dateFrom;
    private RevenueDAO revenueDAO;
    private Table_View table_Histories;
    private GridPane lauyoutHeader;
    private Date_Picker date_To, date_From;
    private Group_Radio_Button group_Radio_Button_Time_Frame;
    private ObservableList<RevenueBill_UserDTO> revenueBill_UserDTOs;
}
