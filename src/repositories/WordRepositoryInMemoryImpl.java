package repositories;

import entities.Word;

import java.util.ArrayList;
import java.util.List;

public class WordRepositoryInMemoryImpl implements WordRepository {
    private List<Word> wordList = new ArrayList<>();

    @Override
    public List<Word> getAll() {
        return wordList;
    }

    @Override
    public void add(Word word) {
        wordList.add(word);
        System.out.println("Kata berhasil ditambahkan.");
    }

    @Override
    public Boolean remove(String word) {
        boolean removed = wordList.removeIf(w -> w.getWord().equals(word));
        if (removed) {
            System.out.println("Kata berhasil dihapus.");
        } else {
            System.out.println("Kata tidak ditemukan.");
        }
        return removed;
    }

    @Override
    public Boolean edit(Word word) {
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).getWord().equals(word.getWord())) {
                wordList.set(i, word);
                System.out.println("Arti kata berhasil diperbarui.");
                return true;
            }
        }
        System.out.println("Kata tidak ditemukan untuk diperbarui.");
        return false;
    }
}
