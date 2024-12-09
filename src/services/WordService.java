package services;

import entities.Word;

import java.util.List;

public interface WordService {
    List<Word> getWordList();                    // Mengambil daftar kata
    void addWord(String word, String meaning);    // Menambahkan kata beserta artinya
    Boolean removeWord(String word);              // Menghapus kata berdasarkan nama kata
    Boolean editWord(String word, String newMeaning); // Mengubah arti kata
}
