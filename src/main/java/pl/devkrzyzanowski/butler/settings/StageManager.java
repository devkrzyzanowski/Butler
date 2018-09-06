/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.settings;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author MichalKrzyzanowski
 */
public class StageManager {
    private ResourceBundle bundle;
    private FXMLLoader lLoader;

    public StageManager() {
        bundle = ResourceBundle.getBundle("resources.bundles.messages", new Locale("pl"));
    }
    
    public void newStage(Stage stage, String fxml) {
        Scene scene = new Scene(getParent(fxml));
        stage.setScene(scene);
        stage.setTitle(bundle.getString("error.undefined"));
        stage.show();
    }
    
    public void changeStage(Stage stage, String fxml) {
        Scene scene = new Scene(getParent(fxml), stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stage.setTitle(bundle.getString("error.undefined"));
        stage.show();
    }
    
    public void addModalStage(Window owner, String fxml) {
        Scene scene = new Scene(getParent(fxml));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setTitle(bundle.getString("error.undefined"));
        stage.show();
    }
    
    private Parent getParent(String fxml) {
        lLoader = new FXMLLoader(this.getClass().getResource(fxml), bundle);
        Parent root = null;
        try {
           return root = lLoader.load();
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
        
    }
    
}
