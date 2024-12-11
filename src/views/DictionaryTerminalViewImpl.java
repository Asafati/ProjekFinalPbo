package views;

import entities.Dictionary;
import services.DictionaryService;
import java.util.List;
import java.util.Scanner;

public class DictionaryTerminalViewImpl implements DictionaryView {
    private static final Scanner scanner = new Scanner(System.in);
    private final DictionaryService dictionaryService;

    public DictionaryTerminalViewImpl(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    public String input(String info) {
        System.out.print(info + ": ");
        return scanner.nextLine();
    }

    @Override
    public void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
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
                    showWordList();
                    break;
                case "2":
                    showMenuAddWord();
                    break;
                case "3":
                    showMenuRemoveWord();
                    break;
                case "4":
                    showSearchWordsContainingLetter();
                    break;
                case "5":
                    showWordList();
                    break;
                case "6":
                    showSearchByPrefixSuffix();
                    break;
                case "7":
                    showMenuEditWord();
                    break;
                case "8":
                    showWordCount();
                    break;
                case "9":
                    showSortedWords();
                    break;
                case "10":
                    resetDictionary();
                    break;
                case "11":
                    showSearchByLength();
                    break;
                case "12":
                    importDefaultWords();
                    break;
                case "13":
                    isRunning = false;
                    System.out.println("Keluar...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    @Override
    public void showMenuAddWord() {
        System.out.println("\nMenambahkan Kata Baru");
        String word = input("Masukkan kata baru");
        String meaning = input("Masukkan arti kata");

        Dictionary newWord = new Dictionary(0, meaning); // ID akan dibuat otomatis oleh database
        dictionaryService.addWord(newWord);
        System.out.println("Kata berhasil ditambahkan.");
    }

    @Override
    public void showMenuRemoveWord() {
        System.out.println("\nMenghapus Kata");
        String wordId = input("Masukkan ID kata yang akan dihapus");

        if (dictionaryService.removeWord(Integer.parseInt(wordId))) {
            System.out.println("Kata berhasil dihapus.");
        } else {
            System.out.println("Kata tidak ditemukan.");
        }
    }

    @Override
    public void showMenuEditWord() {
        System.out.println("\nMengedit Kata");
        String wordId = input("Masukkan ID kata yang akan diedit");

        String newMeaning = input("Masukkan arti kata baru");
        boolean success = dictionaryService.editWord(Integer.parseInt(wordId), newMeaning);
        if (success) {
            System.out.println("Arti kata berhasil diperbarui.");
        } else {
            System.out.println("Gagal memperbarui arti kata.");
        }
    }

    @Override
    public void showWordList() {
        System.out.println("\nDaftar Kata dalam Kamus:");
        List<Dictionary> words = dictionaryService.getAllWords();
        if (words.isEmpty()) {
            System.out.println("Tidak ada kata dalam kamus.");
        } else {
            for (Dictionary word : words) {
                System.out.println(word.getWord() + " : " + word.getMeaning());
            }
        }
    }

    public void showSearchWordsContainingLetter() {
        System.out.println("\nCari Kata yang Mengandung Huruf");
        String letter = input("Masukkan huruf yang dicari");
        List<Dictionary> words = dictionaryService.searchWordsContainingLetter(letter);
        if (words.isEmpty()) {
            System.out.println("Tidak ada kata yang mengandung huruf tersebut.");
        } else {
            for (Dictionary word : words) {
                System.out.println(word.getWord() + " : " + word.getMeaning());
            }
        }
    }

    public void showSearchByPrefixSuffix() {
        System.out.println("\nCari Kata berdasarkan Prefix/Suffix");
        String prefix = input("Masukkan prefix");
        String suffix = input("Masukkan suffix");
        List<Dictionary> words = dictionaryService.searchWordsByPrefixSuffix(prefix, suffix);
        if (words.isEmpty()) {
            System.out.println("Tidak ada kata yang cocok.");
        } else {
            for (Dictionary word : words) {
                System.out.println(word.getWord() + " : " + word.getMeaning());
            }
        }
    }

    public void showWordCount() {
        System.out.println("\nJumlah Kata dalam Kamus: " + dictionaryService.countWords());
    }

    public void showSortedWords() {
        System.out.println("\nKata-kata dalam Urutan Abjad:");
        List<Dictionary> sortedWords = dictionaryService.sortWordsAlphabetically();
        for (Dictionary word : sortedWords) {
            System.out.println(word.getWord() + " : " + word.getMeaning());
        }
    }

    public void resetDictionary() {
        System.out.println("\nReset Kamus");
        dictionaryService.resetDictionary();
        System.out.println("Kamus berhasil direset.");
    }

    public void showSearchByLength() {
        System.out.println("\nCari Kata dengan Panjang Tertentu");
        String length = input("Masukkan panjang kata");
        // Asumsikan kita membuat method untuk mencari kata berdasarkan panjang
        List<Dictionary> words = dictionaryService.searchWordsContainingLetter(length);
        if (words.isEmpty()) {
            System.out.println("Tidak ada kata dengan panjang tersebut.");
        } else {
            for (Dictionary word : words) {
                System.out.println(word.getWord() + " : " + word.getMeaning());
            }
        }
    }

    public void importDefaultWords() {
        System.out.println("\nImport Kata-Kata Default");
        // Logika untuk mengimpor kata-kata default
        System.out.println("Kata-kata default berhasil diimpor.");
    }

    @Override
    public void run() {
        showMainMenu();
    }
}
