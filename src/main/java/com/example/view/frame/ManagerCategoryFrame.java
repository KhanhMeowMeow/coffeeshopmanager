package com.example.view.frame;

import java.util.List;

import com.example.controller.DAO.CategoryDAO;
import com.example.controller.DAO.DAOImpl.CategoryDAOImpl;
import com.example.model.Category;
import com.example.model.DTO.CategoryDTO;
import com.example.view.component.Button_View;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ManagerCategoryFrame extends BorderPane {

    public ManagerCategoryFrame() {
        categoryDAO = new CategoryDAOImpl();

        tabTable = new Tab_View("Danh sách loại đồ uống");
        layoutTable = new BorderPane();
        table_View_Category = new Table_View("Bảng danh sách");
        tabManager = new Tab_View("Quản lý loại đồ uống");
        layoutManager = new GridPane();
        textBox_Id = new Text_Field("Mã loại", 339, false);
        textBox_Name = new Text_Field("Tên loại", 339, true);
        textBox_Disable = new Text_Field("Đã xoá", 339, false);
        layoutButtons = new VBox();
        btnCreate = new Button_View("Tạo mới", 100, 35);
        btnDelete = new Button_View("Xoá", 100, 35);
        btnUpdate = new Button_View("Sửa", 100, 35);
        btnClear = new Button_View("Làm sạch", 100, 35);
        layoutNavigation = new HBox();
        btnNext = new Button_View(">>", 80, 35);
        btnPrev = new Button_View("<<", 80, 35);
        btnFirst = new Button_View("|<<", 80, 35);
        btnLast = new Button_View(">>|", 80, 35);
        tabPane_View = new TabPane_View(tabTable, tabManager);
        layoutSearch = new HBox();
        btnSearch = new Button_View("Tìm kiếm", 100, 43);
        textBox_Search = new Text_Field("Tìm kiếm", 500, true);

        category_ObservableList = FXCollections.observableArrayList();
        table_View_Category.getTable().setItems(category_ObservableList);

        TableColumn<Category, String> col_Id = new TableColumn<>("Mã loại");
        col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Category, String> col_Name = new TableColumn<>("Tên loại");
        col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Category, Boolean> disableCol = new TableColumn<>("Vô hiệu");
        disableCol.setCellValueFactory(new PropertyValueFactory<>("disable"));
        disableCol.setCellFactory(col -> new TableCell<Category, Boolean>() {
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

        table_View_Category.getTable().getColumns().addAll(col_Id, col_Name, disableCol);

        fillTable_Category(categoryDAO.findAll());

        layoutSearch.getChildren().addAll(textBox_Search, btnSearch);
        layoutSearch.setSpacing(10);
        layoutSearch.setAlignment(Pos.CENTER);
        layoutTable.setTop(layoutSearch);
        layoutTable.setCenter(table_View_Category);
        layoutTable.setMargin(layoutTable.getCenter(), new Insets(5, 0, 5, 0));
        tabTable.setCenter(layoutTable);

        layoutManager.setVgap(10);
        layoutManager.setHgap(10);
        layoutManager.add(textBox_Id, 0, 0);
        layoutManager.add(textBox_Name, 1, 0);
        layoutManager.add(textBox_Disable, 2, 0);
        layoutButtons.setSpacing(10);
        layoutButtons.getChildren().addAll(
                btnCreate, btnDelete, btnUpdate, btnClear);
        layoutNavigation.setSpacing(10);
        layoutNavigation.getChildren().addAll(
                btnFirst, btnPrev, btnNext, btnLast);
        layoutManager.add(layoutButtons, 3, 0, 1, 3);
        layoutManager.add(layoutNavigation, 0, 1, 2, 1);
        tabManager.setCenter(layoutManager);

        resetForm();

        textBox_Search.setOnKeyReleased(e -> {
            if (textBox_Search.getText().isEmpty()) {
                fillTable_Category(categoryDAO.findAll());
            }
        });
        btnSearch.setOnMouseClicked(e -> {
            if (!textBox_Search.getText().isEmpty()) {
                fillTable_Category(categoryDAO.findByName(textBox_Search.getText().trim().toLowerCase()));
            }
        });
        table_View_Category.getTable().setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                this.index = table_View_Category.getTable().getSelectionModel().getSelectedIndex();
                getCategoryIndex(index);
                tabPane_View.focusTab(tabManager);
            }
        });
        btnFirst.setOnAction(e -> {
            index = 0;
            getCategoryIndex(index);
        });
        btnLast.setOnAction(e -> {
            index = category_ObservableList.size() - 1;
            getCategoryIndex(index);
        });
        btnPrev.setOnAction(e -> {
            index--;
            if (index < 0) {
                index = category_ObservableList.size() - 1;
            }
            getCategoryIndex(index);
        });
        btnNext.setOnAction(e -> {
            index++;
            if (index >= category_ObservableList.size()) {
                index = 0;
            }
            getCategoryIndex(index);
        });
        btnClear.setOnAction(e -> {
            resetForm();
        });
        btnCreate.setOnAction(e -> {
            if (!validateForm()) {
                return;
            }
            categoryDAO.create(new CategoryDTO(textBox_Name.getText(), false));
            fillTable_Category(categoryDAO.findAll());
            resetForm();
        });
        btnDelete.setOnAction(e -> {
            categoryDAO.delete(this.categoryData.getId());
            fillTable_Category(categoryDAO.findAll());
            resetForm();
        });
        btnUpdate.setOnAction(e -> {
            if (!validateForm()) {
                return;
            }
            categoryDAO.update(
                    new Category(Long.valueOf(textBox_Id.getText()), textBox_Name.getText(), false));
            fillTable_Category(categoryDAO.findAll());
            resetForm();
        });
        this.setCenter(tabPane_View);
        this.setStyle("-fx-background-color: #fff; -fx-background-radius: 20;");
    }

    private void fillTable_Category(List<Category> listCategory) {
        Platform.runLater(() -> {
            category_ObservableList.clear();
            category_ObservableList.setAll(listCategory);
        });
    }

    private boolean validateForm() {
        if (textBox_Name.getText().equals("")) {
            textBox_Name.setWarning("Bạn chưa nhập thông tin");
            return false;
        } else {
            textBox_Name.setWarning("");
        }

        return true;
    }

    private void getCategoryIndex(int index) {
        if (category_ObservableList.size() > 0) {
            if (index < 0) {
                index = 0;
            }
            if (index >= category_ObservableList.size()) {
                index = category_ObservableList.size() - 1;
            }
            this.categoryData = categoryDAO.findById(category_ObservableList.get(index).getId());
            if (this.categoryData != null) {
                textBox_Id.setText(this.categoryData.getId() + "");
                textBox_Name.setText(this.categoryData.getName());
                textBox_Disable.setText(this.categoryData.isDisable() ? "Đã xoá" : "Chưa xoá");
            }
            btnCreate.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    private void resetForm() {
        textBox_Id.setText("");
        textBox_Id.setWarning("");
        textBox_Name.setText("");
        textBox_Name.setWarning("");
        textBox_Disable.setText("");

        this.categoryData = null;
        this.index = -1;

        btnCreate.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private int index;
    private TabPane_View tabPane_View;
    private Tab_View tabTable, tabManager;
    private BorderPane layoutTable;
    private Table_View table_View_Category;
    private CategoryDAO categoryDAO;
    private Category categoryData;
    private Text_Field textBox_Name, textBox_Disable, textBox_Id, textBox_Search;
    private GridPane layoutManager;
    private HBox layoutNavigation, layoutSearch;
    private VBox layoutButtons;
    private ObservableList<Category> category_ObservableList;
    private Button_View btnCreate, btnDelete, btnUpdate, btnClear, btnFirst, btnLast, btnNext, btnPrev, btnSearch;
}
