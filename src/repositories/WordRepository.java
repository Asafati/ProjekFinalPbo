package repositories;

import entities.Word;

import java.util.List;

public interface WordRepository {
    List<Word> getAll();
    void add(Word word);
    Boolean remove(String word);
    Boolean edit(Word word);
}
