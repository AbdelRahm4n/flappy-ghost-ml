module com.example.tp2v5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tp2v5 to javafx.fxml;
    exports com.example.tp2v5;
}