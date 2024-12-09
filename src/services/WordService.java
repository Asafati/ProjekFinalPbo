package services;

import entities.Word;

import java.util.List;

public interface WordService {
    List<Word> getWordList();
    void addWord(String word, String meaning);
    void removeWord(int id);
    void updateWord(Word word);
    List<Word> findWordsContainingChar(String character);
    Word findWordByPrefixOrSuffix(String prefix, String suffix);
    int getWordCount();
    void resetDictionary();
    List<Word> findWordsByLength(int length);
    void importDefaultWords();
}
