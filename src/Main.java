import config.Database;
import repositories.WordRepository;
import repositories.WordRepositoryDbImpl;
import services.WordService;
import services.WordServiceImpl;
import views.WordTerminalViewImpl;
import views.TodoListView;

public class Main {
    public static void main(String[] args) {

        Database database = new Database("final_pbo", "root", "", "localhost", "3306");
        database.setup();
        WordRepository wordRepository = new WordRepositoryDbImpl(database);
        WordService wordService = new WordServiceImpl(wordRepository);
        TodoListView wordView = new WordTerminalViewImpl(wordService);
        wordView.run();
    }
}
