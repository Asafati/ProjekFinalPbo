package main;

import services.WordService;
import services.WordServiceImpl;
import repositories.WordRepository;
import repositories.WordRepositoryDbImpl;
import views.WordTerminalViewImpl;

public class Main {
    public static void main(String[] args) {
        WordRepository wordRepository = new WordRepositoryDbImpl();
        WordService wordService = new WordServiceImpl(wordRepository);
        WordTerminalViewImpl wordTerminalView = new WordTerminalViewImpl(wordService);

        wordTerminalView.showMenu();
    }
}
