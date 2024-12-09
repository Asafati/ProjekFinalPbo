package repositories;

import entities.Word;
import java.util.List;

public interface WordRepository {
    // Mendapatkan semua kata dari database
    List<Word> getAll();

    // Menambahkan kata baru ke database
    void add(Word word);

    // Menghapus kata berdasarkan nama kata
    Boolean remove(String word);

    // Mengupdate kata yang sudah ada
    Boolean edit(Word word);
}
