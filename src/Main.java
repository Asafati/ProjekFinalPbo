import config.Database;
import repositories.WordRepository;
import repositories.WordRepositoryDbImpl;
import services.WordService;
import services.WordServiceImpl;
import views.WordTerminalViewImpl;
import views.WordListView;

public class Main {
    public static void main(String[] args) {
        Database database = new Database("databasefinalpbo", "root", "", "localhost", "3306");
        database.setup();
        WordRepository wordRepository = new WordRepositoryDbImpl(database);
        WordService wordService = new WordServiceImpl(wordRepository);
        WordListView wordView = new WordTerminalViewImpl(wordService); // Mengganti TodoListView dengan WordListView
        wordView.run();
    }
}
