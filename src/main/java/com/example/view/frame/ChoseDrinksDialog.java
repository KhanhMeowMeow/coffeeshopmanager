package com.example.view.frame;

import java.util.List;

import com.example.controller.DAO.DrinkDAO;
import com.example.controller.DAO.DAOImpl.DrinkDAOImpl;
import com.example.controller.lib.FileSYSTEM;
import com.example.model.DTO.DrinkDTO;
import com.example.model.DTO.DrinkOrderDTO;
import com.example.view.component.Image_Lable;
import com.example.view.component.Table_View;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;

public class ChoseDrinksDialog extends Dialog {
    public ChoseDrinksDialog() {

        drinkDAO = new DrinkDAOImpl();

        table_View_ListDrinks = new Table_View("Danh sách đồ uống");
        table_View_ListDrinks.getStylesheets().add(getClass().getResource("/css/styleTable.css").toExternalForm());
        drinkOrderDTOs = FXCollections.observableArrayList();
        table_View_ListDrinks.getTable().setItems(drinkOrderDTOs);

        TableColumn<DrinkOrderDTO, String> col_name = new TableColumn<>("Tên đồ uống");
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<DrinkOrderDTO, String> col_unitPrice = new TableColumn<>("Đơn giá");
        col_unitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        TableColumn<DrinkOrderDTO, String> col_discount = new TableColumn<>("Giảm giá");
        col_discount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        TableColumn<DrinkOrderDTO, String> image_Col = new TableColumn<>("Ảnh");
        image_Col.setCellValueFactory(new PropertyValueFactory<>("image"));
        image_Col.setCellFactory(col -> new TableCell<DrinkOrderDTO, String>() {
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

        TableColumn<DrinkOrderDTO, Boolean> available_col = new TableColumn<>("Trạng thái");
        available_col.setCellValueFactory(new PropertyValueFactory<>("available"));
        available_col.setCellFactory(col -> new TableCell<DrinkOrderDTO, Boolean>() {
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

        TableColumn<DrinkOrderDTO, String> categoryname_col = new TableColumn<>("Tên loại");
        categoryname_col.setCellValueFactory(new PropertyValueFactory<>("categoryname"));

        table_View_ListDrinks.getTable().getColumns().addAll(col_name, col_unitPrice, col_discount, image_Col,
                available_col, categoryname_col);

        fillTableDrinks(drinkDAO.findAllDrinkOrderDTO());

        table_View_ListDrinks.getTable().setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                DrinkOrderDTO selectedDrink = (DrinkOrderDTO) table_View_ListDrinks.getTable().getSelectionModel()
                        .getSelectedItem();
                setResult(selectedDrink.getId());
                this.close();
            }
        });

        this.getDialogPane().setContent(table_View_ListDrinks);
        Font.loadFont(FileSYSTEM.fontApp.getName(), 16);
        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        this.getDialogPane().lookupButton(ButtonType.CLOSE).setVisible(false);
        this.getDialogPane().lookupButton(ButtonType.CLOSE).setManaged(false);
        this.getDialogPane().setStyle("-fx-background-color: white;");
        this.getDialogPane().setPrefSize(1000, 700);
        this.setResizable(false);
        this.setTitle("Chọn thẻ");
    }

    private void fillTableDrinks(List<DrinkOrderDTO> list) {
        Platform.runLater(() -> {
            drinkOrderDTOs.clear();
            drinkOrderDTOs.setAll(list);
        });
    }

    private DrinkDAO drinkDAO;
    private ObservableList<DrinkOrderDTO> drinkOrderDTOs;
    private Table_View table_View_ListDrinks;
}