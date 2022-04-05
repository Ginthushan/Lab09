module com.example.lab9csci2020 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab9csci2020 to javafx.fxml;
    exports com.example.lab9csci2020;
}