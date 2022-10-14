module com.example.hashset {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hashset to javafx.fxml;
    exports com.example.hashset;
}