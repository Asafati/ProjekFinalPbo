package services;

import entities.Dictionary;
import repositories.DictionaryRepository;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DictionaryServiceImpl implements DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    public DictionaryServiceImpl(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    @Override
    public List<Dictionary> getAllWords() {
        return dictionaryRepository.getAll();
    }

    @Override
    public void addWord(Dictionary dictionary) {
        dictionaryRepository.add(dictionary);
    }

    @Override
    public Boolean removeWord(int id) {
        return dictionaryRepository.delete(id);
    }

    @Override
    public Boolean editWord(int id, String newMeaning) {
        return dictionaryRepository.update(id, newMeaning);
    }

    @Override
    public List<Dictionary> searchWordsContainingLetter(String letter) {
        return dictionaryRepository.searchWordsContainingLetter(letter);
    }

    @Override
    public List<Dictionary> searchWordsByPrefixSuffix(String prefix, String suffix) {
        return dictionaryRepository.searchByPrefixSuffix(prefix, suffix);
    }

    @Override
    public int countWords() {
        return dictionaryRepository.countWords();
    }

    @Override
    public void resetDictionary() {
        dictionaryRepository.resetDictionary();
    }

    @Override
    public List<Dictionary> sortWordsAlphabetically() {
        List<Dictionary> words = dictionaryRepository.getAll();
        words.sort(Comparator.comparing(Dictionary::getWord));
        return words;
    }

    @Override
    public void importDefaultWords() {
        // Logika untuk mengimpor kata-kata default
    }
}
