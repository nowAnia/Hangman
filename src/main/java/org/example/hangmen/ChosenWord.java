package org.example.hangmen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class ChosenWord {
    private String selectedWord;
    private ObservableList<String> wordList;
    public ChosenWord() throws IOException {
        Random random = new Random();
        loadToDoItems();
        this.selectedWord = wordList.get(random.nextInt(100));
    }

    public String getSelectedWord() {
        return selectedWord;
    }

    public void loadToDoItems()  throws IOException {
        wordList = FXCollections.observableArrayList();
        Path path = Paths.get("RandomWords.txt");
        BufferedReader br = Files.newBufferedReader(path);
        String input;
        try{
            while((input = br.readLine() ) != null) {
                String[] words = input.split("\t");
                String word = words[0].trim();
                wordList.add(word);
            }
        } finally{
            if (br != null ) {
                br.close();
            }
        }
    }
}
