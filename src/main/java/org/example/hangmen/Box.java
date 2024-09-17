package org.example.hangmen;

import javafx.scene.control.TextArea;

public class Box {
    private TextArea textArea;
    private String containLetter;

    public Box () {
        textArea = new TextArea();
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public String getContainLetter() {
        return containLetter;
    }

    public void setContainLetter(String containLetter) {
        this.containLetter = containLetter;
    }
}
