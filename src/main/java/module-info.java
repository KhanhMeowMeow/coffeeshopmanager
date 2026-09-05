module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires java.desktop;
    requires javafx.swing;
    requires javafx.base;
    requires javafx.graphics; 

    opens com.example to javafx.fxml, javafx.base;
    opens com.example.model to javafx.fxml, javafx.base;
    opens com.example.model.DTO to javafx.fxml, javafx.base;

    exports com.example;
}