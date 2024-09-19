package org.example.hangman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HangManApplication extends Application {

    private static final String MAIN_VIEW_FILE_NAME = "hangman-view.fxml";
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HangManApplication.class.getResource(MAIN_VIEW_FILE_NAME));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);

        stage.setTitle("HangMan Game!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}