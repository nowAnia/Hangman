module org.example.hangmen {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.hangmen to javafx.fxml;
    exports org.example.hangmen;
}