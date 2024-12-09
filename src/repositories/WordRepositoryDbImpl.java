package repositories;

import entities.Word;
import config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WordRepositoryDbImpl implements WordRepository {
    @Override
    public List<Word> getAllWords() {
        List<Word> words = new ArrayList<>();
        try (Connection connection = DatabaseConfig.connect()) {
            String query = "SELECT * FROM Words";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                words.add(new Word(resultSet.getInt("Id"), resultSet.getString("Word"), resultSet.getString("Meaning")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return words;
    }

    @Override
    public void addWord(Word word) {
        try (Connection connection = DatabaseConfig.connect()) {
            String query = "INSERT INTO Words (Word, Meaning) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, word.getWord());
            statement.setString(2, word.getMeaning());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeWord(int id) {
        try (Connection connection = DatabaseConfig.connect()) {
            String query = "DELETE FROM Words WHERE Id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateWord(Word word) {
        try (Connection connection = DatabaseConfig.connect()) {
            String query = "UPDATE Words SET Word = ?, Meaning = ? WHERE Id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, word.getWord());
            statement.setString(2, word.getMeaning());
            statement.setInt(3, word.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Word> findWordsContainingChar(String character) {
        List<Word> words = new ArrayList<>();
        try (Connection connection = DatabaseConfig.connect()) {
            String query = "SELECT * FROM Words WHERE Word LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + character + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                words.add(new Word(resultSet.getInt("Id"), resultSet.getString("Word"), resultSet.getString("Meaning")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return words;
    }

    @Override
    public Word findWordByPrefixOrSuffix(String prefix, String suffix) {
        Word word = null;
        try (Connection connection = DatabaseConfig.connect()) {
            String query = "SELECT * FROM Words WHERE Word LIKE ? OR Word LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, prefix + "%");
            statement.setString(2, "%" + suffix);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                word = new Word(resultSet.getInt("Id"), resultSet.getString("Word"), resultSet.getString("Meaning"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return word;
    }

    @Override
    public int getWordCount() {
        int count = 0;
        try (Connection connection = DatabaseConfig.connect()) {
            String query = "SELECT COUNT(*) FROM Words";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void resetDictionary() {
        try (Connection connection = DatabaseConfig.connect()) {
            String query = "DELETE FROM Words";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Word> findWordsByLength(int length) {
        List<Word> words = new ArrayList<>();
        try (Connection connection = DatabaseConfig.connect()) {
            String query = "SELECT * FROM Words WHERE LENGTH(Word) = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, length);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                words.add(new Word(resultSet.getInt("Id"), resultSet.getString("Word"), resultSet.getString("Meaning")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return words;
    }

    @Override
    public void importDefaultWords() {
        // Add default words into the database if needed
    }
}
