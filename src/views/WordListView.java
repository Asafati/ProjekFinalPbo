package views;

public interface WordListView {
    void run(); // Menjalankan tampilan utama aplikasi kamus
    void displayWords(); // Menampilkan daftar kata
    void showAddWordPrompt(); // Menampilkan prompt untuk menambah kata
    void showEditWordPrompt(); // Menampilkan prompt untuk mengedit kata
    void showRemoveWordPrompt(); // Menampilkan prompt untuk menghapus kata
}
