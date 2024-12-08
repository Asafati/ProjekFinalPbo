package repositories;

import config.Database;
import entities.Word;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WordRepositoryDbImpl implements WordRepository {
    private final Database database;

    public WordRepositoryDbImpl(Database database) {
        this.database = database;
    }

    @Override
    public List<Word> getAll() {
        List<Word> wordList = new ArrayList<>();
        String sql = "SELECT * FROM kamus";
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String word = resultSet.getString("word");
                String meaning = resultSet.getString("meaning");
                wordList.add(new Word(id, word, meaning));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return wordList;
    }

    @Override
    public void add(Word word) {
        String sql = "INSERT INTO words (word, meaning) VALUES (?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, word.getWord());
            preparedStatement.setString(2, word.getMeaning());
            preparedStatement.executeUpdate();  // Menjalankan query
            System.out.println("Kata berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Boolean remove(String word) {
        String sql = "DELETE FROM words WHERE word = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, word);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Kata berhasil dihapus.");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean edit(Word word) {
        String sql = "UPDATE words SET meaning = ? WHERE word = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, word.getMeaning());
            preparedStatement.setString(2, word.getWord());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Arti kata berhasil diperbarui.");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}
