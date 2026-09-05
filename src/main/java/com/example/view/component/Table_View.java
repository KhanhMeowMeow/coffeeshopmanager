package com.example.view.component;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class Table_View extends BorderPane {

    public Table_View(String title) {
        
        tableView = new TableView<>();
        lableTitle = new Label(title);

        lableTitle.setStyle("-fx-font-size: 13px; -fx-font-family: 'Asap Condensed'; -fx-text-fill: #626262;");
            tableView.setPlaceholder(new Label("Đang tải..."));
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            this.setTop(lableTitle);
            this.setCenter(tableView);
            if (title != null) {
                this.setMargin(this.getTop(), new Insets(0, 0, 5, 0));
            }
    }

    public TableView getTable() {
        return this.tableView;
    }

    private TableView tableView;
    private Label lableTitle;

}
