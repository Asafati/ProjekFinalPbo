package repositories;

import entities.Dictionary;
import java.util.ArrayList;
import java.util.List;

public class DictionaryRepositoryInMemoryImpl implements DictionaryRepository {

    private List<Dictionary> dictionaries;

    public DictionaryRepositoryInMemoryImpl() {
        this.dictionaries = new ArrayList<>();
    }

    @Override
    public List<Dictionary> getAll() {
        return dictionaries;
    }

    @Override
    public void add(Dictionary dictionary) {
        dictionaries.add(dictionary);
    }

    @Override
    public boolean update(int id, String meaning) {
        Dictionary dictionary = getById(id);
        if (dictionary != null) {
            dictionary.setMeaning(meaning);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Dictionary dictionary = getById(id);
        if (dictionary != null) {
            dictionaries.remove(dictionary);
            return true;
        }
        return false;
    }

    @Override
    public Dictionary getById(int id) {
        return dictionaries.stream()
                .filter(d -> Integer.parseInt(d.getMeaning()) == id)
                .findFirst()
                .orElse(null);
    }


    @Override
    public List<Dictionary> searchWordsContainingLetter(String letter) {
        List<Dictionary> result = new ArrayList<>();
        for (Dictionary dictionary : dictionaries) {
            if (dictionary.getMeaning().contains(letter)) {
                result.add(dictionary);
            }
        }
        return result;
    }

    @Override
    public List<Dictionary> searchByPrefixSuffix(String prefix, String suffix) {
        List<Dictionary> result = new ArrayList<>();
        for (Dictionary dictionary : dictionaries) {
            if (dictionary.getMeaning().startsWith(prefix) && dictionary.getMeaning().endsWith(suffix)) {
                result.add(dictionary);
            }
        }
        return result;
    }

    @Override
    public int countWords() {
        return dictionaries.size();
    }

    @Override
    public void resetDictionary() {
        dictionaries.clear();
    }
}
