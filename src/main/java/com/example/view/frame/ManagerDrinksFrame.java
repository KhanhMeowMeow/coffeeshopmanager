package com.example.view.frame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.controller.DAO.CategoryDAO;
import com.example.controller.DAO.DrinkDAO;
import com.example.controller.DAO.DAOImpl.CategoryDAOImpl;
import com.example.controller.DAO.DAOImpl.DrinkDAOImpl;
import com.example.controller.lib.UploadImage;
import com.example.model.Category;
import com.example.model.Drink;
import com.example.model.DTO.DrinkDTO;
import com.example.model.DTO.DrinkDTOCreate;
import com.example.view.component.Button_View;
import com.example.view.component.Combo_Radiobox;
import com.example.view.component.Group_Radio_Button;
import com.example.view.component.Image_Lable;
import com.example.view.component.Number_Input;
import com.example.view.component.Radio_Button;
import com.example.view.component.TabPane_View;
import com.example.view.component.Tab_View;
import com.example.view.component.Table_View;
import com.example.view.component.Text_Field;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ManagerDrinksFrame extends BorderPane {

    public ManagerDrinksFrame() {

        drinkDAO = new DrinkDAOImpl();
        categoryDAO = new CategoryDAOImpl();
        tabTable = new Tab_View("Bảng danh sách đồ uống");
        layoutTable = new BorderPane();
        table_Drink = new Table_View("Bảng danh sách");
        tabManager = new Tab_View("Quản lý đồ uống");
        layoutManager = new GridPane();
        textBox_Id = new Text_Field("Mã sản phẩm", 339, false);
        textBox_Name = new Text_Field("Tên sản phẩm", 339, true);
        numberInput_UnitPrice = new Number_Input("Giá bán", 339,
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100000, 0, 1000));
        numberInput_Discount = new Number_Input("Giảm giá", 339,
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 1, 0.0, 0.01));
        group_Radio_Available = new Group_Radio_Button(
                "Trạng thái sản phẩm",
                339,
                new Radio_Button<Boolean>("Còn hàng", true),
                new Radio_Button<Boolean>("Hết hàng", false));
        mapCategory = new HashMap<Long, String>();
        listCategory = categoryDAO.findAll();
        for (int i = 0; i < listCategory.size(); i++) {
            mapCategory.put(listCategory.get(i).getId(), listCategory.get(i).getName());
        }
        combo_Radio_CategoryId = new Combo_Radiobox("Loại đồ uống", 339, mapCategory);
        textBox_Disable = new Text_Field("Đã xoá", 339, false);
        image_Lable = new Image_Lable(300, 300, null);
        btnCreate = new Button_View("Tạo mới", 100, 35);
        btnDelete = new Button_View("Xoá", 100, 35);
        btnUpdate = new Button_View("Sửa", 100, 35);
        btnClear = new Button_View("Làm sạch", 100, 35);
        layoutButtons = new VBox();
        layoutNavigation = new HBox();
        tabPane_View = new TabPane_View(tabTable, tabManager);
        btnNext = new Button_View(">>", 80, 35);
        btnPrev = new Button_View("<<", 80, 35);
        btnFirst = new Button_View("|<<", 80, 35);
        btnLast = new Button_View(">>|", 80, 35);
        textBox_Search = new Text_Field("Tìm kiếm", 500, true);
        layoutSearch = new HBox();
        btnSearch = new Button_View("Tìm kiếm", 80, 43);
        combo_Radio_CategoryId_Search = new Combo_Radiobox("Chọn loại", 339, mapCategory);

        layoutSearch.getChildren().addAll(
                textBox_Search, btnSearch, combo_Radio_CategoryId_Search);
        layoutSearch.setSpacing(10);
        layoutSearch.setAlignment(Pos.CENTER);
        layoutSearch.setMargin(btnSearch, new Insets(0, 20, 0, 0));

        drinks_ObservableList = FXCollections.observableArrayList();
        table_Drink.getTable().setItems(drinks_ObservableList);

        layoutTable.setTop(layoutSearch);
        layoutTable.setCenter(table_Drink);
        layoutTable.setMargin(layoutTable.getTop(), new Insets(5, 0, 0, 0));
        tabTable.setCenter(layoutTable);

        TableColumn<DrinkDTO, String> col_Id = new TableColumn<>("Mã đồ uống");
        col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<DrinkDTO, String> col_name = new TableColumn<>("Tên đồ uống");
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<DrinkDTO, String> col_unitPrice = new TableColumn<>("Đơn giá");
        col_unitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        TableColumn<DrinkDTO, String> col_discount = new TableColumn<>("Giảm giá");
        col_discount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        TableColumn<DrinkDTO, String> col_image = new TableColumn<>("Hình ảnh");
        col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        col_image.setCellFactory(col -> new TableCell<DrinkDTO, String>() {
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

        TableColumn<DrinkDTO, Boolean> col_available = new TableColumn<>("Trạng thái");
        col_available.setCellValueFactory(new PropertyValueFactory<>("available"));
        col_available.setCellFactory(col -> new TableCell<DrinkDTO, Boolean>() {
            private final Label label = new Label();

            @Override
            protected void updateItem(Boolean disable, boolean empty) {
                super.updateItem(disable, empty);
                if (empty || disable == null) {
                    setGraphic(null);
                } else {
                    if (disable) {
                        label.setText("Còn hàng");
                        label.setStyle("-fx-text-fill: blue;");
                    } else {
                        label.setText("Hết hàng");
                        label.setStyle("-fx-text-fill: red;");
                    }
                    setGraphic(label);
                }
            }
        });

        TableColumn<DrinkDTO, String> col_categoryName = new TableColumn<>("Loại");
        col_categoryName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));

        TableColumn<DrinkDTO, Boolean> disableCol = new TableColumn<>("Vô hiệu");
        disableCol.setCellValueFactory(new PropertyValueFactory<>("disable"));
        disableCol.setCellFactory(col -> new TableCell<DrinkDTO, Boolean>() {
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

        table_Drink.getTable().getColumns().addAll(col_Id, col_name, col_image, col_unitPrice, col_discount,
                col_available, col_categoryName, disableCol);

        fillTtable_Drink(drinkDAO.findAll());

        layoutManager.setVgap(10);
        layoutManager.setHgap(10);
        layoutManager.add(textBox_Id, 0, 0);
        layoutManager.add(textBox_Name, 1, 0);
        layoutManager.add(numberInput_UnitPrice, 0, 1);
        layoutManager.add(numberInput_Discount, 1, 1);
        layoutManager.add(combo_Radio_CategoryId, 0, 2);
        layoutManager.add(group_Radio_Available, 1, 2);
        layoutManager.add(textBox_Disable, 0, 3);
        layoutManager.add(image_Lable, 3, 0, 1, 4);
        layoutButtons.setSpacing(10);
        layoutButtons.getChildren().addAll(
                btnCreate, btnDelete, btnUpdate, btnClear);
        layoutNavigation.setSpacing(10);
        layoutNavigation.getChildren().addAll(
                btnFirst, btnPrev, btnNext, btnLast);
        layoutManager.add(layoutButtons, 4, 0, 1, 3);
        layoutManager.add(layoutNavigation, 0, 4, 2, 1);
        tabManager.setCenter(layoutManager);

        // Các sự kiện hành động
        combo_Radio_CategoryId_Search.getBody().textProperty().addListener(e -> {
            fillTtable_Drink(drinkDAO.findByCardId(combo_Radio_CategoryId_Search.getData()));
        });
        textBox_Search.setOnKeyReleased(e -> {
            if (textBox_Search.getText().isEmpty()) {
                fillTtable_Drink(drinkDAO.findAll());
            }
        });
        btnSearch.setOnMouseClicked(e -> {
            if (!textBox_Search.getText().isEmpty()) {
                fillTtable_Drink(drinkDAO.findByName(textBox_Search.getText().trim().toLowerCase()));
            }
        });
        btnFirst.setOnAction(e -> {
            index = 0;
            getDrinkIndex(index);
        });
        btnLast.setOnAction(e -> {
            index = drinks_ObservableList.size() - 1;
            getDrinkIndex(index);
        });
        btnPrev.setOnAction(e -> {
            index--;
            if (index < 0) {
                index = drinks_ObservableList.size() - 1;
            }
            getDrinkIndex(index);
        });
        btnNext.setOnAction(e -> {
            index++;
            if (index >= drinks_ObservableList.size()) {
                index = 0;
            }
            getDrinkIndex(index);
        });
        table_Drink.getTable().setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                index = table_Drink.getTable().getSelectionModel().getSelectedIndex();
                getDrinkIndex(index);
                tabPane_View.focusTab(tabManager);
            }
        });
        image_Lable.setOnMouseClicked(e -> {
            image_Lable.setImage((Image) UploadImage.choseImage(this.getScene().getWindow()));
        });
        btnClear.setOnAction(e -> {
            resetForm();
        });
        btnCreate.setOnAction(e -> {
            if (!validateForm()) {
                return;
            }
            if (this.drinkData == null) {
                drinkDAO.create(new DrinkDTOCreate(
                        textBox_Name.getText(),
                        numberInput_UnitPrice.getValue(),
                        numberInput_Discount.getValue(),
                        UploadImage.loadImagetoLocal((Image) image_Lable.getImage()),
                        group_Radio_Available.getValue(),
                        combo_Radio_CategoryId.getData(),
                        false));
                resetForm();
                fillTtable_Drink(drinkDAO.findAll());
            }
        });
        btnUpdate.setOnAction(e -> {
            if (!validateForm()) {
                return;
            }
            if (this.drinkData != null) {
                drinkDAO.update(new Drink(
                        Long.valueOf(textBox_Id.getText()),
                        textBox_Name.getText(),
                        numberInput_UnitPrice.getValue(),
                        numberInput_Discount.getValue(),
                        UploadImage.loadImagetoLocal((Image) image_Lable.getImage()),
                        group_Radio_Available.getValue(),
                        combo_Radio_CategoryId.getData(),
                        false));
                resetForm();
                fillTtable_Drink(drinkDAO.findAll());
            }
        });
        btnDelete.setOnAction(e -> {
            if (this.drinkData != null) {
                drinkDAO.delete(this.drinkData.getId());
                resetForm();
                fillTtable_Drink(drinkDAO.findAll());
            }
        });
        this.setCenter(tabPane_View);
        this.setStyle("-fx-background-color: #fff; -fx-background-radius: 20;");
    }

    private void resetForm() {
        textBox_Id.setText("");
        textBox_Id.setWarning("");
        textBox_Name.setText("");
        textBox_Name.setWarning("");
        textBox_Disable.setText("");
        group_Radio_Available.setValue(null);
        group_Radio_Available.setWarning("");
        combo_Radio_CategoryId.setData(null);
        combo_Radio_CategoryId.setWarning("");
        numberInput_UnitPrice.setValue(0.0);
        numberInput_UnitPrice.setWarning("");
        numberInput_Discount.setValue(0.0);
        numberInput_Discount.setWarning("");
        image_Lable.showImage(null);

        this.drinkData = null;
        this.index = -1;

        btnCreate.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void fillTtable_Drink(List<DrinkDTO> listsDrinkDTOs) {
        Platform.runLater(() -> {
            drinks_ObservableList.clear();
            drinks_ObservableList.setAll(listsDrinkDTOs);
        });
    }

    private void getDrinkIndex(int index) {
        if (drinks_ObservableList.size() > 0) {
            if (index < 0) {
                index = 0;
            }
            if (index >= drinks_ObservableList.size()) {
                index = drinks_ObservableList.size() - 1;
            }
            this.drinkData = drinkDAO.findById(drinks_ObservableList.get(index).getId());
            if (this.drinkData != null) {
                textBox_Id.setText(this.drinkData.getId() + "");
                textBox_Name.setText(this.drinkData.getName());
                numberInput_UnitPrice.setValue(this.drinkData.getUnitPrice());
                numberInput_Discount.setValue(this.drinkData.getDiscount());
                textBox_Disable.setText(this.drinkData.isDisable() ? "Đã xoá" : "Chưa xoá");
                group_Radio_Available.setValue(this.drinkData.isAvailable());
                combo_Radio_CategoryId.setData(this.drinkData.getCategoryId());
                image_Lable.showImage(this.drinkData.getImage());
            }

            btnCreate.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    private boolean validateForm() {
        if (textBox_Name.getText().equals("")) {
            textBox_Name.setWarning("Bạn chưa nhập thông tin");
            return false;
        } else {
            textBox_Name.setWarning("");
        }

        if (group_Radio_Available.getValue() == null) {
            group_Radio_Available.setWarning("Bạn chưa nhập thông tin");
            return false;
        } else {
            group_Radio_Available.setWarning("");
        }

        if (combo_Radio_CategoryId.getData() == null) {
            combo_Radio_CategoryId.setWarning("Bạn chưa nhập thông tin");
            return false;
        } else {
            combo_Radio_CategoryId.setWarning("");
        }

        return true;
    }

    private int index;
    private TabPane_View tabPane_View;
    private Tab_View tabTable, tabManager;
    private BorderPane layoutTable;
    private Table_View table_Drink;
    private CategoryDAO categoryDAO;
    private DrinkDAO drinkDAO;
    private Drink drinkData;
    private Text_Field textBox_Name, textBox_Disable, textBox_Id, textBox_Search;
    private Number_Input numberInput_UnitPrice, numberInput_Discount;
    private GridPane layoutManager;
    private HBox layoutNavigation, layoutSearch;
    private VBox layoutButtons;
    private Button_View btnCreate, btnDelete, btnUpdate, btnClear, btnFirst, btnLast, btnNext, btnPrev, btnSearch;
    private Image_Lable image_Lable;
    private Group_Radio_Button<Boolean> group_Radio_Available;
    private Combo_Radiobox combo_Radio_CategoryId, combo_Radio_CategoryId_Search;
    private Map<Long, String> mapCategory;
    private List<Category> listCategory;
    private ObservableList<DrinkDTO> drinks_ObservableList;
}
