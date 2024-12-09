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
        String sql = "SELECT * FROM words"; // Disesuaikan dengan tabel 'words'
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id"); // Ambil id dari kolom 'id'
                String word = resultSet.getString("word"); // Ambil kata dari kolom 'word'
                String meaning = resultSet.getString("meaning"); // Ambil arti dari kolom 'meaning'
                wordList.add(new Word(id, word, meaning)); // Menambahkan Word ke list
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return wordList;
    }

    @Override
    public void add(Word word) {
        String sql = "INSERT INTO words (word, meaning) VALUES (?, ?)"; // Menyusun query INSERT untuk tabel 'words'
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, word.getWord()); // Set nilai word
            preparedStatement.setString(2, word.getMeaning()); // Set nilai meaning
            preparedStatement.executeUpdate();  // Menjalankan query
            System.out.println("Kata berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Boolean remove(String word) {
        String sql = "DELETE FROM words WHERE word = ?"; // Menyusun query DELETE untuk tabel 'words'
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, word); // Set nilai word yang akan dihapus
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Kata berhasil dihapus.");
                return true; // Mengembalikan true jika kata berhasil dihapus
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false; // Mengembalikan false jika terjadi error atau kata tidak ditemukan
    }

    @Override
    public Boolean edit(Word word) {
        String sql = "UPDATE words SET meaning = ? WHERE word = ?"; // Menyusun query UPDATE untuk tabel 'words'
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, word.getMeaning()); // Set nilai meaning yang baru
            preparedStatement.setString(2, word.getWord()); // Set nilai word yang akan diupdate
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Arti kata berhasil diperbarui.");
                return true; // Mengembalikan true jika arti kata berhasil diperbarui
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false; // Mengembalikan false jika terjadi error atau kata tidak ditemukan
    }
}
