package views;

import entities.Word;

import java.util.List;

public interface WordListView {
    void displayWordList(List<Word> words);
    void displayMessage(String message);
}
