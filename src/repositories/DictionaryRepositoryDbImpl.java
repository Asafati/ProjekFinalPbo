package repositories;

import config.Database;
import entities.Dictionary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DictionaryRepositoryDbImpl implements DictionaryRepository {

    private final Database database;

    public DictionaryRepositoryDbImpl(final Database database) {
        this.database = database;
    }

    @Override
    public void add(Dictionary dictionary) {
        String sql = "INSERT INTO dictionary (meaning) VALUES (?)";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, dictionary.getMeaning());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    dictionary.setWord(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(int id, String meaning) {
        String sql = "UPDATE dictionary SET meaning = ? WHERE word = ?";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, meaning);
            stmt.setInt(2, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM dictionary WHERE word = ?";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Dictionary getById(int id) {
        String sql = "SELECT * FROM dictionary WHERE word = ?";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Dictionary(rs.getInt("word"), rs.getString("meaning"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Dictionary> getAll() {
        List<Dictionary> dictionaries = new ArrayList<>();
        String sql = "SELECT * FROM dictionary";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                dictionaries.add(new Dictionary(rs.getInt("word"), rs.getString("meaning")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dictionaries;
    }

    @Override
    public List<Dictionary> searchWordsContainingLetter(String letter) {
        List<Dictionary> dictionaries = new ArrayList<>();
        String sql = "SELECT * FROM dictionary WHERE meaning LIKE ?";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + letter + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    dictionaries.add(new Dictionary(rs.getInt("word"), rs.getString("meaning")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dictionaries;
    }

    @Override
    public List<Dictionary> searchByPrefixSuffix(String prefix, String suffix) {
        List<Dictionary> dictionaries = new ArrayList<>();
        String sql = "SELECT * FROM dictionary WHERE meaning LIKE ?";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prefix + "%" + suffix);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    dictionaries.add(new Dictionary(rs.getInt("word"), rs.getString("meaning")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dictionaries;
    }

    @Override
    public int countWords() {
        String sql = "SELECT COUNT(*) FROM dictionary";
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public void resetDictionary() {
        String sql = "TRUNCATE TABLE dictionary";
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
