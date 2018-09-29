package pl.devkrzyzanowski.butler;

import java.time.LocalTime;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.devkrzyzanowski.butler.Model.Database;
import pl.devkrzyzanowski.butler.menager.StageManager;


public class MainApp extends Application {
    public static StageManager stageManager;
    public static Database databaseManager;
    public static LocalTime time;
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    
    @Override
    public void start(Stage stage) throws Exception {
        stageManager = new StageManager(stage);
        databaseManager = new Database();
        stageManager.newStage("/fxml/loginPage.fxml");
    }
    

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
