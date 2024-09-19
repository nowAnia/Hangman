package org.example.hangman;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;

public class LetterBox {
    private final TextArea textArea;
    private final String containLetter;

    private boolean isGuessed;


    public LetterBox(char letter) {
        isGuessed = false;
        textArea = new TextArea();
        textArea.setPrefSize(30.00, 30.00);
        textArea.setDisable(true);

        containLetter = String.valueOf(letter).toUpperCase();
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public boolean matchesLetter(String letter) {
        return containLetter.equals(letter);
    }

    public void markGuessed() {
        textArea.setBackground(Background.fill(Paint.valueOf("green")));
        textArea.setText(containLetter);
        textArea.setDisable(false);
        isGuessed = true;
    }
    public boolean isGuessed() {
        return isGuessed;
    }


}
