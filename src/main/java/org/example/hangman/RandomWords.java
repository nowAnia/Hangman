package org.example.hangman;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWords {
    private final List<String> wordList;
    private final Random random;

    private static final String RANDOM_WORDS_FILENAME = "RandomWords.txt";

    public RandomWords() throws IOException {
        random = new Random();
        wordList = Files.readAllLines(Paths.get(RANDOM_WORDS_FILENAME));
    }

    public String pickRandomWord() {
        return wordList.get(random.nextInt(wordList.size()));
    }
}
