package entities;

public class Dictionary {
    private int word; // Menggunakan int untuk ID (word)
    private String meaning;

    public Dictionary() {
    }

    public Dictionary(int word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    public int getWord() {
        return word;
    }

    public void setWord(int word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return word + ": " + meaning;
    }
}
