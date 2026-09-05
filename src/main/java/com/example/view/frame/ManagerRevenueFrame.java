package com.example.view.frame;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.controller.DAO.BillDAO;
import com.example.controller.DAO.RevenueDAO;
import com.example.controller.DAO.DAOImpl.BillDAOImpl;
import com.example.controller.DAO.DAOImpl.RevenueDAOImpl;
import com.example.controller.lib.DateTimeSYSTEM;
import com.example.model.User;
import com.example.model.DTO.BillDTO;
import com.example.model.DTO.RevenueBillDTO;
import com.example.model.DTO.RevenueUserDTO;
import com.example.view.component.Date_Picker;
import com.example.view.component.Group_Radio_Button;
import com.example.view.component.Radio_Button;
import com.example.view.component.TabPane_View;
import com.example.view.component.Tab_View;
import com.example.view.component.Table_View;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ManagerRevenueFrame extends BorderPane {

    public ManagerRevenueFrame() {

        revenueDAO = new RevenueDAOImpl();

        // Table Bill
        table_View_Bill = new Table_View(null);

        TableColumn<RevenueBillDTO, String> nameCol = new TableColumn<>("Loại đồ uống");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<RevenueBillDTO, String> totalPriceCol = new TableColumn<>("Tổng doanh thu");
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        TableColumn<RevenueBillDTO, String> totalQuantityCol = new TableColumn<>("Tổng số lượng");
        totalQuantityCol.setCellValueFactory(new PropertyValueFactory<>("totalQuantity"));

        TableColumn<RevenueBillDTO, String> minPriceCol = new TableColumn<>("Giá thấp nhât");
        minPriceCol.setCellValueFactory(new PropertyValueFactory<>("minPrice"));

        TableColumn<RevenueBillDTO, String> maxPriceCol = new TableColumn<>("Giá lớn nhất");
        maxPriceCol.setCellValueFactory(new PropertyValueFactory<>("maxPrice"));

        TableColumn<RevenueBillDTO, Double> avgPriceCol = new TableColumn<>("Giá trung bình");
        avgPriceCol.setCellValueFactory(new PropertyValueFactory<>("avgPrice"));
        avgPriceCol.setCellFactory(col -> new TableCell<RevenueBillDTO, Double>() {
            private final DecimalFormat df = new DecimalFormat("#0.0");

            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText(null);
                } else {
                    setText(df.format(value));
                }
            }
        });

        table_View_Bill.getTable().getColumns().addAll(nameCol, totalPriceCol, totalQuantityCol, minPriceCol,
                maxPriceCol, avgPriceCol);

        bills = FXCollections.observableArrayList();
        table_View_Bill.getTable().setItems(bills);

        // Table_UserBill
        table_View_UserBill = new Table_View(null);

        TableColumn<RevenueUserDTO, String> usernameCol = new TableColumn<>("Tên người dùng");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<RevenueUserDTO, String> totalPriceUserCol = new TableColumn<>("Tổng doanh thu");
        totalPriceUserCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        TableColumn<RevenueUserDTO, String> totalQuantityBillCol = new TableColumn<>("Số lượng hoá đơn");
        totalQuantityBillCol.setCellValueFactory(new PropertyValueFactory<>("totalQuantityBill"));

        TableColumn<RevenueUserDTO, Date> firsDateCol = new TableColumn<>("Đơn đâu tiên");
        firsDateCol.setCellValueFactory(new PropertyValueFactory<>("firsDate"));
        firsDateCol.setCellFactory(column -> new TableCell<RevenueUserDTO, Date>() {
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

        TableColumn<RevenueUserDTO, Date> finalDateCol = new TableColumn<>("Đơn cuối cùng");
        finalDateCol.setCellValueFactory(new PropertyValueFactory<>("finalDate"));
        finalDateCol.setCellFactory(column -> new TableCell<RevenueUserDTO, Date>() {
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

        table_View_UserBill.getTable().getColumns().addAll(usernameCol, totalPriceUserCol, totalQuantityBillCol,
                firsDateCol, finalDateCol);

        users = FXCollections.observableArrayList();
        table_View_UserBill.getTable().setItems(users);

        // Header_TabTable
        date_Picker_From = new Date_Picker("Từ ngày", 339);
        date_Picker_To = new Date_Picker("Đến ngày", 339);
        group_Radio_Period = new Group_Radio_Button<>(
                "Khung thời gian",
                339,
                new Radio_Button("Ngày", 0),
                new Radio_Button("Tháng", 1),
                new Radio_Button("Năm", 2));
        layoutTableView_Header = new GridPane();
        layoutTableView_Header.add(date_Picker_From, 0, 0);
        layoutTableView_Header.add(date_Picker_To, 1, 0);
        layoutTableView_Header.add(group_Radio_Period, 2, 0);
        layoutTableView_Header.setHgap(10);

        date_Picker_From.getDate().valueProperty().addListener((obs, oldDate, newDate) -> {
            this.dateFrom = DateTimeSYSTEM.toUtilDate(newDate);
            if (this.dateTo != null) {
                if (this.dateFrom.after(this.dateTo)) {
                    date_Picker_From.getDate().setValue(null);
                }
            }
            fillTableBills(this.dateFrom, this.dateTo);
            fillTableUsers(this.dateFrom, this.dateTo);
        });

        date_Picker_To.getDate().valueProperty().addListener((obs, oldDate, newDate) -> {
            this.dateTo = DateTimeSYSTEM.toUtilDate(newDate);
            if (this.dateFrom != null) {
                if (this.dateTo.before(this.dateFrom)) {
                    date_Picker_To.getDate().setValue(null);
                }
            }
            fillTableBills(this.dateFrom, this.dateTo);
            fillTableUsers(this.dateFrom, this.dateTo);
        });

        group_Radio_Period.getChecked().selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                Radio_Button selected = (Radio_Button) newToggle;
                fillTablePeriodBills((int) selected.getValue());
                fillTablePeriodUsers((int) selected.getValue());
            }
        });

        tab_Bill = new Tab_View("Thống kê doanh thu");
        tab_Bill.setCenter(table_View_Bill);

        tab_UserBill = new Tab_View("Thống kê người lên đơn");
        tab_UserBill.setCenter(table_View_UserBill);

        tabPane_Table = new TabPane_View(
                tab_Bill, tab_UserBill);
        tabPane_Table.setStyle(
                "-fx-background-color: white;-fx-background-radius: 20; -fx-border-insets: 0 0 0 0; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 5, 0.5, 0, 0);");

        fillTableBills(null, null);
        fillTableUsers(null, null);

        layoutTable = new BorderPane();
        layoutTable.setTop(layoutTableView_Header);
        layoutTable.setCenter(tabPane_Table);
        layoutTable.setMargin(layoutTable.getCenter(), new Insets(10, 0, 0, 0));
        tabTable = new Tab_View("Danh sách doanh thu");
        tabTable.setCenter(layoutTable);

        tabManager = new Tab_View("Thống kê doanh thu");
        tabPane_View = new TabPane_View(
                tabTable, tabManager);
        this.setCenter(tabPane_View);
        this.setStyle("-fx-background-color: #fff; -fx-background-radius: 20;");
    }

    public void fillTableBills(Date dateFrom, Date dateTo) {
        bills.clear();
        Platform.runLater(() -> {
            if (dateFrom == null && dateTo == null) {
                bills.setAll(revenueDAO.findAllBill());
            }
            if (dateFrom == null && dateTo != null) {
                bills.setAll(revenueDAO.findbyDateAfterBill(dateTo));
            }
            if (dateFrom != null && dateTo == null) {
                bills.setAll(revenueDAO.findbyDateBeforeBill(dateFrom));
            }
            if (dateFrom != null && dateTo != null) {
                bills.setAll(revenueDAO.findbyDateFromAndToBill(dateFrom, dateTo));
            }
        });
    }

    public void fillTableUsers(Date dateFrom, Date dateTo) {
        bills.clear();
        Platform.runLater(() -> {
            if (dateFrom == null && dateTo == null) {
                users.setAll(revenueDAO.findAllUser());
            }
            if (dateFrom == null && dateTo != null) {
                users.setAll(revenueDAO.findbyDateAfterUser(dateTo));
            }
            if (dateFrom != null && dateTo == null) {
                users.setAll(revenueDAO.findbyDateBeforeUser(dateFrom));
            }
            if (dateFrom != null && dateTo != null) {
                users.setAll(revenueDAO.findbyDateFromAndToUser(dateFrom, dateTo));
            }
        });
    }

    public void fillTablePeriodBills(int period) {
        bills.clear();
        Task<List<RevenueBillDTO>> loadData = new Task<List<RevenueBillDTO>>() {
            @Override
            protected List<RevenueBillDTO> call() throws Exception {
                List<RevenueBillDTO> list = null;
                if (period == 0) {
                    list = revenueDAO.findbyDateBill(new Date());
                }
                if (period == 1) {
                    list = revenueDAO.findbyMonthBill(new Date());
                }
                if (period == 2) {
                    list = revenueDAO.findbyYearBill(new Date());
                }
                return list;
            }
        };
        loadData.setOnSucceeded(e -> {
            bills.setAll(loadData.getValue());
        });
        loadData.setOnFailed(e -> {
            bills.clear();
        });
        new Thread(loadData).start();
    }

    public void fillTablePeriodUsers(int period) {
        bills.clear();
        Task<List<RevenueUserDTO>> loadData = new Task<List<RevenueUserDTO>>() {
            @Override
            protected List<RevenueUserDTO> call() throws Exception {
                List<RevenueUserDTO> list = null;
                if (period == 0) {
                    list = revenueDAO.findbyDateUser(new Date());
                }
                if (period == 1) {
                    list = revenueDAO.findbyMonthUser(new Date());
                }
                if (period == 2) {
                    list = revenueDAO.findbyYearUser(new Date());
                }
                return list;
            }
        };
        loadData.setOnSucceeded(e -> {
            users.setAll(loadData.getValue());
        });
        loadData.setOnFailed(e -> {
            users.clear();
        });
        new Thread(loadData).start();
    }

    private Date dateTo, dateFrom;
    private RevenueDAO revenueDAO;
    private TabPane_View tabPane_View, tabPane_Table;
    private Tab_View tabTable, tabManager, tab_Bill, tab_UserBill;
    private BorderPane layoutTable;
    private Table_View table_View_Bill, table_View_UserBill;
    private ObservableList<RevenueBillDTO> bills;
    private ObservableList<RevenueUserDTO> users;
    private GridPane layoutTableView_Header;
    private Date_Picker date_Picker_From, date_Picker_To;
    private Group_Radio_Button group_Radio_Period;
}
