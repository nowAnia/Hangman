package org.example.hangman;

import javafx.scene.control.TextArea;

public class Box {
    private TextArea textArea;
    private String containLetter;

    public Box(char letter) {
        textArea = new TextArea();
        textArea.setPrefSize(30.00, 30.00);
        textArea.setDisable(true);

        containLetter = String.valueOf(letter).toUpperCase();
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public String getContainLetter() {
        return containLetter;
    }

}
