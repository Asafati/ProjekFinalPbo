package repositories;

import entities.Word;
import java.util.List;

public interface WordRepository {
    List<Word> getAllWords();
    void addWord(Word word);
    void removeWord(int id);
    void updateWord(Word word);
    List<Word> findWordsContainingChar(String character);
    Word findWordByPrefixOrSuffix(String prefix, String suffix);
    int getWordCount();
    void resetDictionary();
    List<Word> findWordsByLength(int length);
    void importDefaultWords();
}
