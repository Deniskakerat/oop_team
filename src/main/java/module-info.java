module com.example.hashset {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hashset to javafx.fxml;
    exports com.example.hashset;
    exports com.example.hashset.graphics;
    opens com.example.hashset.graphics to javafx.fxml;
}