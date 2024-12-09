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
        return wordRepository.getAll(); // Mengambil daftar kata dari repository
    }

    @Override
    public void addWord(String word, String meaning) {
        // Validasi input kata dan arti
        if (word == null || word.isBlank()) {
            System.out.println("Masukkan kata dengan benar.");
            return;
        }
        if (meaning == null || meaning.isBlank()) {
            System.out.println("Masukkan arti kata dengan benar.");
            return;
        }

        // Membuat objek Word baru dan menambahkannya ke repository
        Word newWord = new Word(word, meaning);
        wordRepository.add(newWord);
        System.out.println("Kata berhasil ditambahkan.");
    }

    @Override
    public Boolean removeWord(String word) {
        // Menghapus kata dari repository
        return wordRepository.remove(word);
    }

    @Override
    public Boolean editWord(String word, String newMeaning) {
        // Validasi arti baru
        if (newMeaning == null || newMeaning.isBlank()) {
            System.out.println("Masukkan arti kata yang baru dengan benar.");
            return false;
        }

        // Membuat objek Word yang diperbarui dan mengeditnya di repository
        Word updatedWord = new Word(word, newMeaning);
        return wordRepository.edit(updatedWord);
    }
}
