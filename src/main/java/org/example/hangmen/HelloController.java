package org.example.hangmen;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class HelloController {
    @FXML
    public Image imageOne;
    @FXML
    public StackPane stack;
    public ImageView imageView;
    @FXML
    private FlowPane word;
    @FXML
    private TextField enteredWord;
    @FXML
    private TextField enteredLetter;
    private String chosenWord;
    private int counter = 0;
    private Image imageTwo;
    private Image imageThree;
    private Image imageFour;
    private Image imageFive;
    private Image imageSix;
    private Image imageSeven;

    private ArrayList<Box> listOfBoxes;

    private  int succesfullyEntered = 0;


    @FXML
    public void initialize() throws IOException {
        imageTwo = new Image(Objects.requireNonNull(getClass().getResource("pictureOne.png")).toString());
        imageThree = new Image(Objects.requireNonNull(getClass().getResource("pictureTwo.png")).toString());
        imageFour = new Image(Objects.requireNonNull(getClass().getResource("pictureThree.png")).toString());
        imageFive = new Image(Objects.requireNonNull(getClass().getResource("pictureFour.png")).toString());
        imageSix = new Image(Objects.requireNonNull(getClass().getResource("pictureFive.png")).toString());
        imageSeven = new Image(Objects.requireNonNull(getClass().getResource("pictureSix.png")).toString());
        imageSeven = new Image(Objects.requireNonNull(getClass().getResource("pictureSeven.png")).toString());
        startGame();
    }

    private void startGame() throws IOException {
        clearGame();
        succesfullyEntered = 0;
        imageView.setImage(imageOne);

        FlowPane flowPane = word;
        ChosenWord chosenWord = new ChosenWord();
        System.out.println(chosenWord.getSelectedWord());
        this.chosenWord = chosenWord.getSelectedWord();
        int amountOfWords = chosenWord.getSelectedWord().length();

        int i = 0;
        listOfBoxes = new ArrayList<>();
        while (i < amountOfWords) {
            Box text = new Box();
            text.getTextArea().setDisable(true);
            String letter = Character.toString(chosenWord.getSelectedWord().charAt(i));

            text.setContainLetter(letter.toUpperCase());
            text.getTextArea().setPrefSize(30.00, 30.00);
            listOfBoxes.add(text);
            flowPane.getChildren().add(text.getTextArea());
            i++;
        }
    }
    private void clearGame() {
        word.getChildren().clear();
        enteredLetter.clear();
        enteredWord.clear();
        counter = 0;
    }

    @FXML
    public void handleKeyReleased(KeyEvent keyEvent) throws IOException {
        String enteredW = enteredWord.getText().toUpperCase();
        String enteredL = enteredLetter.getText().toUpperCase();

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (!enteredW.isEmpty()) {
                if (enteredW.equals(chosenWord.toUpperCase())) {
                    for (var child : word.getChildren()) {
                        TextArea t = (TextArea) child;
                        t.setBackground(Background.fill(Paint.valueOf("green")));
                        t.setDisable(false);
                    }
                    for (var box : listOfBoxes) {
                            box.getTextArea().setBackground(Background.fill(Paint.valueOf("green")));
                            box.getTextArea().setText(box.getContainLetter());
                            box.getTextArea().setDisable(false);
                    }
                    success();
                } else {
                    imageView.setImage(imageSeven);
                    failure();
                }
            }
            if (!enteredL.isEmpty()) {
                if (enteredL.length() > 1) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, " Enter only one letter ");
                    alert.showAndWait();
                }
                boolean flag = true;
                for (var box : listOfBoxes) {
                    System.out.println(box.getContainLetter());
                    if (box.getContainLetter().toUpperCase().equals(enteredL)) {
                        box.getTextArea().setBackground(Background.fill(Paint.valueOf("green")));
                        box.getTextArea().setText(box.getContainLetter());
                        succesfullyEntered++;

                        box.getTextArea().setDisable(false);
                        flag = false;
                    }
                }
                if (flag) {
                    counter++;
                    switch (counter) {
                        case 1 -> imageView.setImage(imageTwo);
                        case 2 -> imageView.setImage(imageThree);
                        case 3 -> imageView.setImage(imageFour);
                        case 4 -> imageView.setImage(imageFive);
                        case 5 -> imageView.setImage(imageSix);
                        case 6 -> imageView.setImage(imageSeven);
                        default -> failure();
                    }
                }
                if (succesfullyEntered == chosenWord.length()  )  {
                  success();
                }
                enteredWord.setText("");
                enteredLetter.setText("");
            }
        }
    }


    public void failure() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, " You lost. Would you like to try again?  ", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            System.out.println( "You want play again");
            startGame();
        }
        if (alert.getResult() == ButtonType.NO) {
            System.out.println("No more ");
            Platform.exit();
        }
    }

    public void success() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, " Congratulations .You won !!!\n Play again? ", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            System.out.println( "You want play again");
            startGame();
        }
        if (alert.getResult() == ButtonType.NO) {
            System.out.println("No more ");
            Platform.exit();
        }
    }
}
