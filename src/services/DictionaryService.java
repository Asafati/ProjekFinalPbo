package services;

import entities.Dictionary;
import java.util.List;

public interface DictionaryService {
    List<Dictionary> getAllWords();

    void addWord(Dictionary dictionary);

    Boolean removeWord(int id);

    Boolean editWord(int id, String newMeaning);

    List<Dictionary> searchWordsContainingLetter(String letter);

    List<Dictionary> searchWordsByPrefixSuffix(String prefix, String suffix);

    int countWords();

    void resetDictionary();

    List<Dictionary> sortWordsAlphabetically();

    void importDefaultWords();
}
