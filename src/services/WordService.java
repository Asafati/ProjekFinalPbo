package services;

import entities.Word;

import java.util.List;

public interface WordService {
    List<Word> getWordList();
    void addWord(String word, String meaning);
    Boolean removeWord(String word);
    Boolean editWord(String word, String newMeaning);
}
