import config.Database;
import repositories.DictionaryRepository;
import repositories.DictionaryRepositoryDbImpl;
import services.DictionaryService;
import services.DictionaryServiceImpl;
import views.DictionaryTerminalViewImpl;
import views.DictionaryView;

public class Main {
    public static void main(String[] args) {

        // Setup koneksi ke database
        Database database = new Database("databaseku", "root", "", "localhost", "3306");
        database.setup();

        // Inisialisasi Repository
        DictionaryRepository dictionaryRepository = new DictionaryRepositoryDbImpl(database);

        // Inisialisasi Service
        DictionaryService dictionaryService = new DictionaryServiceImpl(dictionaryRepository);

        // Inisialisasi View dan menjalankan aplikasi
        DictionaryView dictionaryView = new DictionaryTerminalViewImpl(dictionaryService);
        dictionaryView.run();
    }
}
