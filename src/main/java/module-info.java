module org.example.hangman {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.hangman to javafx.fxml;
    exports org.example.hangman;
}