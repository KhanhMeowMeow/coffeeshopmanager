package com.example.view.frame;

import com.example.view.component.Table_View;
import com.example.view.component.Text_Field;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class OrderFrame extends BorderPane {

    public OrderFrame() {

        header_OderFrame = new HBox();
        header_OderFrame.setSpacing(10);
        header_OderFrame.getChildren().addAll(
                new Text_Field("Tên nhân viên:", 335, false),
                new Text_Field("Mã đơn", 335, false),
                new Text_Field("Thẻ", 335, false));

        table_View = new Table_View("Bảng danh sách");

        this.setTop(header_OderFrame);
        this.setCenter(table_View);
        this.setMargin(this.getCenter(), new Insets(30, 0, 0, 0));
        this.setStyle("-fx-background-color: white; -fx-background-radius: 20;");
        this.setPadding(new Insets(20));
    }

    private HBox header_OderFrame;
    private Table_View table_View;
}