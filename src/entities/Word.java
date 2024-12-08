package entities;

public class Word {
    private Integer id;
    private String word;
    private String meaning;

    public Word() {
    }
    public Word(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }
    public Word(Integer id, String word, String meaning) {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
    }
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
}
