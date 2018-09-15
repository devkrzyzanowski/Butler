package pl.devkrzyzanowski.butler;

import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import pl.devkrzyzanowski.butler.menager.StageManager;


public class MainApp extends Application {
    public static StageManager stageManager;
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    
    @Override
    public void start(Stage stage) throws Exception {
        stageManager = new StageManager(stage, "/fxml/loginPage.fxml");
        
        
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/styles/Styles.css");
//        
//        stage.setTitle("JavaFX and Maven");
//        stage.setScene(scene);
//        stage.show();
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
