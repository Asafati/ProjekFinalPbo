package entities;

public class Word {
    private Integer id;  // ID akan otomatis di-generate oleh database
    private String word;
    private String meaning;

    // Default constructor
    public Word() {
    }

    // Constructor for creating a new Word (without ID)
    public Word(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    // Constructor with ID (used for updating or fetching from DB)
    public Word(Integer id, String word, String meaning) {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
    }

    // Getter and Setter methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    // Optional: toString method to display the word object clearly
    @Override
    public String toString() {
        return "Word{id=" + id + ", word='" + word + "', meaning='" + meaning + "'}";
    }
}
