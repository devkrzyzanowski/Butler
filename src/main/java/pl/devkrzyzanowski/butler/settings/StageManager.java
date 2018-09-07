/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.settings;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
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

    /**
     *
     */
    public StageManager() {
        try {
        bundle = ResourceBundle.getBundle("bundles.messages", Locale.getDefault());
        } catch (MissingResourceException e) {
            bundle = null;
            System.err.println(e);
        }
    }
    
    /**
     *
     * @param stage
     * @param fxml
     */
    public boolean newStage(Stage stage, String fxml) {
        if (stage != null) {
            System.err.println("stage is null!");
            return false;
        } else if (fxml != null) {
            System.err.println("fxml is null!");
            return false;
        } else {
            Scene scene = new Scene(getParent(fxml));
            stage.setScene(scene);
            stage.setTitle(bundle.getString("error.undefined"));
            stage.show();
            return true;
        }
    }
    
    /**
     *
     * @param stage
     * @param fxml
     * @return true on success, false on fail
     */
    public boolean changeStage(Stage stage, String fxml) {
        if (stage != null) {
            System.err.println("stage is null!");
            return false;
        } else if (fxml != null) {
            System.err.println("fxml is null!");
            return false;
        } else {
            Scene scene = new Scene(getParent(fxml), stage.getScene().getWidth(), stage.getScene().getHeight());
            stage.setScene(scene);
            stage.setTitle(bundle.getString("error.undefined"));
            stage.show();
            return true;
        }

    }
    
    /**
     *
     * @param owner
     * @param fxml
     */
    public boolean addModalStage(Window owner, String fxml) {
        if (owner != null) {
            System.err.println("owner is null!");
            return false;
        } else if (fxml != null) {
            System.err.println("fxml is null!");
            return false;
        } else {
            Scene scene = new Scene(getParent(fxml));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(owner);
            stage.setTitle(bundle.getString("error.undefined"));
            stage.show();
            return true;
        }
    }
    
    private Parent getParent(String fxml) {
        lLoader = new FXMLLoader(this.getClass().getResource(fxml), bundle);
        Parent root = null;
        try {
            return root = lLoader.load();
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
        
    }
    
}
