package views;

import entities.Word;
import services.WordService;

import java.util.List;
import java.util.Scanner;

public class WordTerminalViewImpl implements WordListView {
    private final WordService wordService;
    private final Scanner scanner = new Scanner(System.in);

    public WordTerminalViewImpl(WordService wordService) {
        this.wordService = wordService;
    }

    @Override
    public void displayWordList(List<Word> words) {
        for (Word word : words) {
            System.out.println(word.getWord() + ": " + word.getMeaning());
        }
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void showMenu() {
        while (true) {
            System.out.println("MENU");
            System.out.println("1. Tampilkan Arti Kata");
            System.out.println("2. Tambah Kata Baru");
            System.out.println("3. Hapus Kata");
            System.out.println("4. Cari Kata yang Mengandung Huruf");
            System.out.println("5. Tampilkan Daftar Kata");
            System.out.println("6. Cari Kata dengan Prefix atau Suffix");
            System.out.println("7. Perbarui Arti Kata");
            System.out.println("8. Tampilkan Jumlah Kata dalam Kamus");
            System.out.println("9. Tampilkan Semua Kata dalam Urutan Abjad");
            System.out.println("10. Reset Kamus");
            System.out.println("11. Cari Kata dengan Panjang Tertentu");
            System.out.println("12. Import Kata-Kata Default");
            System.out.println("13. Keluar");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    // Implement functionality to display word meanings
                    break;
                case 2:
                    System.out.println("Masukkan kata: ");
                    String word = scanner.nextLine();
                    System.out.println("Masukkan arti kata: ");
                    String meaning = scanner.nextLine();
                    wordService.addWord(word, meaning);
                    break;
                case 3:
                    // Implement functionality to remove word
                    break;
                case 4:
                    System.out.println("Masukkan huruf yang ingin dicari: ");
                    String character = scanner.nextLine();
                    List<Word> words = wordService.findWordsContainingChar(character);
                    displayWordList(words);
                    break;
                case 5:
                    // Implement functionality to display all words
                    break;
                case 6:
                    // Implement functionality to find words by prefix or suffix
                    break;
                case 7:
                    // Implement functionality to update word meaning
                    break;
                case 8:
                    System.out.println("Jumlah kata dalam kamus: " + wordService.getWordCount());
                    break;
                case 9:
                    // Implement functionality to display words in alphabetical order
                    break;
                case 10:
                    // Implement functionality to reset dictionary
                    break;
                case 11:
                    // Implement functionality to find words by length
                    break;
                case 12:
                    wordService.importDefaultWords();
                    break;
                case 13:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
