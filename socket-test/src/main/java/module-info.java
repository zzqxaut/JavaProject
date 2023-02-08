module com.example.sockettest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.test to javafx.fxml;
    exports com.test;
}