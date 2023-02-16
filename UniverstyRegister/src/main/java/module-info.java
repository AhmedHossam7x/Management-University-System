module com.example.universtyregister {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.universtyregister to javafx.fxml;
    exports com.example.universtyregister;
}