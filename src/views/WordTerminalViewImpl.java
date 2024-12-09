package services;

import entities.Word;
import java.util.List;
import java.util.ArrayList;

public class WordServiceImpl implements WordService {

    private List<Word> wordList = new ArrayList<>();  // Daftar kata dan arti

    @Override
    public List<Word> getWordList() {
        return wordList;
    }

    @Override
    public void addWord(String word, String meaning) {
        wordList.add(new Word(word, meaning));  // Menambahkan kata baru beserta artinya
    }

    @Override
    public Boolean removeWord(String word) {
        for (Word w : wordList) {
            if (w.getWord().equals(word)) {
                wordList.remove(w);
                return true;  // Kata ditemukan dan dihapus
            }
        }
        return false;  // Kata tidak ditemukan
    }

    @Override
    public Boolean editWord(String word, String newMeaning) {
        for (Word w : wordList) {
            if (w.getWord().equals(word)) {
                w.setMeaning(newMeaning);  // Mengubah arti kata
                return true;
            }
        }
        return false;  // Kata tidak ditemukan
    }

    // Method getMeaning yang mengembalikan arti kata
    public String getMeaning(String word) {
        for (Word w : wordList) {
            if (w.getWord().equals(word)) {
                return w.getMeaning();  // Mengembalikan arti kata
            }
        }
        return null;  // Jika kata tidak ditemukan
    }
}
