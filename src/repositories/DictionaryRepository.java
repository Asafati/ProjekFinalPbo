package repositories;

import entities.Dictionary;
import java.util.List;

public interface DictionaryRepository {
    void add(Dictionary dictionary);
    boolean update(int id, String meaning);
    boolean delete(int id);
    Dictionary getById(int id);
    List<Dictionary> getAll();
    List<Dictionary> searchWordsContainingLetter(String letter);
    List<Dictionary> searchByPrefixSuffix(String prefix, String suffix);
    int countWords();
    void resetDictionary();
}
