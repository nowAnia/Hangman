package org.example.hangman;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class HangManApplicationController {
    @FXML
    public Image imageOne;
    @FXML
    public StackPane stack;
    public ImageView imageView;
    @FXML
    private FlowPane word;
    @FXML
    private TextField wordTextField;
    @FXML
    private TextField letterTextField;
    private String chosenWord;
    private int mistakes = 0;
    private Image imageTwo;
    private Image imageThree;
    private Image imageFour;
    private Image imageFive;
    private Image imageSix;
    private Image imageSeven;

    private HangManImage hangManImage;

    private ArrayList<LetterBox> listOfLetterBoxes;

    private int correctGuesses = 0;
    private RandomWords randomWords;


    @FXML
    public void initialize() throws IOException {
        hangManImage = new HangManImage();
        imageTwo = new Image(Objects.requireNonNull(getClass().getResource("pictureOne.png")).toString());
        imageThree = new Image(Objects.requireNonNull(getClass().getResource("pictureTwo.png")).toString());
        imageFour = new Image(Objects.requireNonNull(getClass().getResource("pictureThree.png")).toString());
        imageFive = new Image(Objects.requireNonNull(getClass().getResource("pictureFour.png")).toString());
        imageSix = new Image(Objects.requireNonNull(getClass().getResource("pictureFive.png")).toString());
        imageSeven = new Image(Objects.requireNonNull(getClass().getResource("pictureSix.png")).toString());
        imageSeven = new Image(Objects.requireNonNull(getClass().getResource("pictureSeven.png")).toString());
        randomWords = new RandomWords();
        startGame();
    }

    private void startGame() {
        clearGame();
        correctGuesses = 0;
        imageView.setImage(imageOne);

        chosenWord = randomWords.pickRandomWord();
        System.out.println(chosenWord);

        listOfLetterBoxes = new ArrayList<>();

        chosenWord.chars()
                .mapToObj(charAsInt -> (char) charAsInt)
                .forEach(
                        c -> {
                            LetterBox textLetterBox = new LetterBox(c);
                            listOfLetterBoxes.add(textLetterBox);
                            word.getChildren().add(textLetterBox.getTextArea());
                        }
                );
    }

    private void clearGame() {
        word.getChildren().clear();
        letterTextField.clear();
        wordTextField.clear();
        mistakes = 0;
    }

    @FXML
    public void handleKeyReleased(KeyEvent keyEvent) throws IOException {
        String enteredWord = this.wordTextField.getText().toUpperCase();
        String enteredLetter = this.letterTextField.getText().toUpperCase();

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (!enteredWord.isEmpty()) {
                handleEnteredWord();
            }
            if (!enteredLetter.isEmpty()) {
                handleEnteredLetter();

            }
        }
    }

    private void handleEnteredWord() {
        String enteredWord = this.wordTextField.getText().toUpperCase();
        if (enteredWord.equals(chosenWord.toUpperCase())) {
            for (var child : word.getChildren()) {
                TextArea t = (TextArea) child;
                t.setBackground(Background.fill(Paint.valueOf("green")));
                t.setDisable(false);
            }
            for (var box : listOfLetterBoxes) {
                box.markGuessed();
            }
            success();
        } else {
            imageView.setImage(imageSeven);
            failure();
        }
    }

    private void handleEnteredLetter() {
        String enteredLetter = this.letterTextField.getText().toUpperCase();

        if (enteredLetter.length() > 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING, " Enter only one letter ");
            alert.showAndWait();
        }

        boolean incorrectGuess = true;

        for (var box : listOfLetterBoxes) {
            if (!box.isGuessed() && box.matchesLetter(enteredLetter)) {
                box.markGuessed();

                correctGuesses++;
                incorrectGuess = false;
            }
        }

        if (incorrectGuess) {
            mistakes++;
            if (mistakes == HangManImage.MAX_IMAGE_INDEX) {
                failure();
            } else {
                hangManImage.increaseCurrentImageIndex();
                imageView.setImage(hangManImage.getCurrentImage());
            }
        }

        if (correctGuesses == chosenWord.length()) {
            success();
        }

        this.wordTextField.setText("");
        this.letterTextField.setText("");
    }




    public void failure() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, " You lost. Would you like to try again?  ", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            System.out.println("You want play again");
            startGame();
        }
        if (alert.getResult() == ButtonType.NO) {
            System.out.println("No more ");
            Platform.exit();
        }
    }

    public void success() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, " Congratulations .You won !!!\n Play again? ", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            System.out.println("You want play again");
            startGame();
        }
        if (alert.getResult() == ButtonType.NO) {
            System.out.println("No more ");
            Platform.exit();
        }
    }
}
