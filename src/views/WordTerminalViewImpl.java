package views;

import entities.Word;
import services.WordService;

import java.util.List;
import java.util.Scanner;

public class WordTerminalViewImpl implements TodoListView {
    private static final Scanner scanner = new Scanner(System.in);
    private final WordService wordService;

    public WordTerminalViewImpl(WordService wordService) {
        this.wordService = wordService;
    }

    public String input(String info) {
        System.out.print(info + " : ");
        return scanner.nextLine();
    }

    public void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showWordList(); // Menampilkan daftar kata
            System.out.println("\nMENU:");
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

            String selectedMenu = input("Pilih menu");

            switch (selectedMenu) {
                case "1":
                    showMenuShowMeaning();
                    break;
                case "2":
                    showMenuAddWord();
                    break;
                case "3":
                    showMenuRemoveWord();
                    break;
                case "4":
                    showMenuSearchByLetter();
                    break;
                case "5":
                    showMenuWordList();
                    break;
                case "6":
                    showMenuSearchByPrefixOrSuffix();
                    break;
                case "7":
                    showMenuEditWord();
                    break;
                case "8":
                    showMenuShowWordCount();
                    break;
                case "9":
                    showMenuShowWordsInAlphabeticalOrder();
                    break;
                case "10":
                    showMenuResetDictionary();
                    break;
                case "11":
                    showMenuSearchByLength();
                    break;
                case "12":
                    showMenuImportDefaultWords();
                    break;
                case "13":
                    isRunning = false;
                    System.out.println("Keluar dari aplikasi...");
                    break;
                default:
                    System.out.println("Pilih menu dengan benar.");
            }
        }
    }

    // Menampilkan Daftar Kata
    public void showWordList() {
        System.out.println("\nDAFTAR KATA:");
        List<Word> wordList = wordService.getWordList();
        if (wordList.isEmpty()) {
            System.out.println("Tidak ada kata.");
        } else {
            for (int i = 0; i < wordList.size(); i++) {
                Word word = wordList.get(i);
                System.out.println((i + 1) + ". " + word.getWord() + " : " + word.getMeaning());
            }
        }
    }

    // Fitur 1: Tampilkan Arti Kata
    public void showMenuShowMeaning() {
        System.out.println("\nTAMPILKAN ARTI KATA");
        String word = input("Masukkan kata yang ingin dilihat artinya (x untuk batal)");
        if (word.equals("x")) return;

        String meaning = wordService.getMeaning(word);
        if (meaning != null) {
            System.out.println("Arti kata '" + word + "' adalah: " + meaning);
        } else {
            System.out.println("Kata tidak ditemukan.");
        }
    }

    // Fitur 2: Tambah Kata Baru
    public void showMenuAddWord() {
        System.out.println("\nMENAMBAH KATA");
        String word = input("Masukkan kata (x untuk batal)");
        if (word.equals("x")) return;

        String meaning = input("Masukkan arti kata (x untuk batal)");
        if (meaning.equals("x")) return;

        wordService.addWord(word, meaning);
        System.out.println("Kata berhasil ditambahkan.");
    }

    // Fitur 3: Hapus Kata
    public void showMenuRemoveWord() {
        System.out.println("\nMENGHAPUS KATA");
        String word = input("Masukkan kata yang ingin dihapus (x untuk batal)");
        if (word.equals("x")) return;

        boolean success = wordService.removeWord(word);
        if (success) {
            System.out.println("Kata berhasil dihapus.");
        } else {
            System.out.println("Gagal menghapus kata: " + word);
        }
    }

    // Fitur 4: Cari Kata yang Mengandung Huruf
    public void showMenuSearchByLetter() {
        System.out.println("\nCARI KATA DENGAN HURUF");
        String letter = input("Masukkan huruf yang dicari");
        List<Word> foundWords = wordService.searchWordsByLetter(letter);
        if (foundWords.isEmpty()) {
            System.out.println("Tidak ada kata yang mengandung huruf tersebut.");
        } else {
            System.out.println("Kata-kata yang mengandung huruf '" + letter + "':");
            for (Word word : foundWords) {
                System.out.println(word.getWord() + " : " + word.getMeaning());
            }
        }
    }

    // Fitur 5: Tampilkan Daftar Kata
    public void showMenuWordList() {
        showWordList();
    }

    // Fitur 6: Cari Kata dengan Prefix atau Suffix
    public void showMenuSearchByPrefixOrSuffix() {
        System.out.println("\nCARI KATA DENGAN PREFIX / SUFFIX");
        String prefixOrSuffix = input("Masukkan prefix atau suffix yang dicari");
        List<Word> foundWords = wordService.searchWordsByPrefixOrSuffix(prefixOrSuffix);
        if (foundWords.isEmpty()) {
            System.out.println("Tidak ada kata dengan prefix atau suffix tersebut.");
        } else {
            System.out.println("Kata-kata dengan prefix atau suffix '" + prefixOrSuffix + "':");
            for (Word word : foundWords) {
                System.out.println(word.getWord() + " : " + word.getMeaning());
            }
        }
    }

    // Fitur 7: Perbarui Arti Kata
    public void showMenuEditWord() {
        System.out.println("\nMENGEDIT ARTI KATA");
        String word = input("Masukkan kata yang ingin diedit (x untuk batal)");
        if (word.equals("x")) return;

        String newMeaning = input("Masukkan arti kata yang baru (x untuk batal)");
        if (newMeaning.equals("x")) return;

        boolean success = wordService.editWord(word, newMeaning);
        if (success) {
            System.out.println("Arti kata berhasil diubah.");
        } else {
            System.out.println("Gagal mengedit arti kata.");
        }
    }

    // Fitur 8: Tampilkan Jumlah Kata dalam Kamus
    public void showMenuShowWordCount() {
        System.out.println("\nJUMLAH KATA DALAM KAMUS");
        int wordCount = wordService.getWordCount();
        System.out.println("Jumlah kata yang ada dalam kamus: " + wordCount);
    }

    // Fitur 9: Tampilkan Semua Kata dalam Urutan Abjad
    public void showMenuShowWordsInAlphabeticalOrder() {
        System.out.println("\nTAMPILKAN SEMUA KATA DALAM URUTAN ABJAD");
        List<Word> sortedWords = wordService.getWordsSortedAlphabetically();
        if (sortedWords.isEmpty()) {
            System.out.println("Tidak ada kata.");
        } else {
            System.out.println("Kata-kata dalam urutan abjad:");
            for (Word word : sortedWords) {
                System.out.println(word.getWord() + " : " + word.getMeaning());
            }
        }
    }

    // Fitur 10: Reset Kamus
    public void showMenuResetDictionary() {
        System.out.println("\nRESET KAMUS");
        wordService.resetDictionary();
        System.out.println("Semua kata dalam kamus telah dihapus.");
    }

    // Fitur 11: Cari Kata dengan Panjang Tertentu
    public void showMenuSearchByLength() {
        System.out.println("\nCARI KATA DENGAN PANJANG TERTENTU");
        int length = Integer.parseInt(input("Masukkan panjang kata yang dicari"));
        List<Word> foundWords = wordService.searchWordsByLength(length);
        if (foundWords.isEmpty()) {
            System.out.println("Tidak ada kata dengan panjang tersebut.");
        } else {
            System.out.println("Kata-kata dengan panjang " + length + ":");
            for (Word word : foundWords) {
                System.out.println(word.getWord() + " : " + word.getMeaning());
            }
        }
    }

    // Fitur 12: Import Kata-Kata Default
    public void showMenuImportDefaultWords() {
        System.out.println("\nIMPORT KATA-KATA DEFAULT");
        wordService.importDefaultWords();
        System.out.println("Kata-kata default berhasil diimpor.");
    }

    @Override
    public void run() {
        showMainMenu();
    }
}
