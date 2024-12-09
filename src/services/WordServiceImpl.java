package services;

import entities.Word;
import repositories.WordRepository;

import java.util.List;

public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public List<Word> getWordList() {
        return wordRepository.getAllWords();
    }

    @Override
    public void addWord(String word, String meaning) {
        Word newWord = new Word(0, word, meaning);
        wordRepository.addWord(newWord);
    }

    @Override
    public void removeWord(int id) {
        wordRepository.removeWord(id);
    }

    @Override
    public void updateWord(Word word) {
        wordRepository.updateWord(word);
    }

    @Override
    public List<Word> findWordsContainingChar(String character) {
        return wordRepository.findWordsContainingChar(character);
    }

    @Override
    public Word findWordByPrefixOrSuffix(String prefix, String suffix) {
        return wordRepository.findWordByPrefixOrSuffix(prefix, suffix);
    }

    @Override
    public int getWordCount() {
        return wordRepository.getWordCount();
    }

    @Override
    public void resetDictionary() {
        wordRepository.resetDictionary();
    }

    @Override
    public List<Word> findWordsByLength(int length) {
        return wordRepository.findWordsByLength(length);
    }

    @Override
    public void importDefaultWords() {
        wordRepository.importDefaultWords();
    }
}
