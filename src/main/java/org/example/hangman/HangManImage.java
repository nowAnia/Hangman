package org.example.hangman;

import javafx.scene.image.Image;

import java.util.Objects;

public class HangManImage {
    private Image imageOne;
    private Image imageTwo;
    private Image imageThree;
    private Image imageFour;
    private Image imageFive;
    private Image imageSix;
    private Image imageSeven;

    private int currentImageIndex;

    public static final int MAX_IMAGE_INDEX = 7;

    public HangManImage() {
        imageOne = loadImage("pictureOne.png");
        imageTwo = loadImage("pictureTwo.png");
        imageThree = loadImage("pictureThree.png");
        imageFour = loadImage("pictureFour.png");
        imageFive = loadImage("pictureFive.png");
        imageSix = loadImage("pictureSix.png");
        imageSeven = loadImage("pictureSeven.png");
        currentImageIndex = 0;
    }

    private Image loadImage(String image) {
        return new Image(Objects.requireNonNull(getClass().getResource(image)).toString());
    }

    public void increaseCurrentImageIndex() {
        currentImageIndex++;
    }

    public Image getCurrentImage() {
        return switch (currentImageIndex) {
            case 0 -> imageOne;
            case 1 -> imageTwo;
            case 2 -> imageThree;
            case 3 -> imageFour;
            case 4 -> imageFive;
            case 5 -> imageSix;
            case 6 -> imageSeven;
            default -> imageOne;
        };
    }

    public void reset() {
        currentImageIndex = 0;
    }
}


