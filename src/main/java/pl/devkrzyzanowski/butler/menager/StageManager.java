/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.menager;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
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
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    /**
     *
     */
    public StageManager(Stage stage, String fxml) {
        this.newStage(stage, fxml);
        setBundle("bundles.messages", Locale.getDefault());
    }
    
    public boolean setBundle(String path, Locale locale) {
        try {
            bundle = ResourceBundle.getBundle("bundles.messages", locale);
            return true;
        } catch (MissingResourceException e) {
            System.err.println(e);
            return false;
        }
    }
    
    /**
     *
     * @param stage
     * @param fxml
     * @return 0 on succes, 1 if stage is null, 2 if fxml is null, 3 if stage 
     * and fxml is null
     */
    public Integer newStage(Stage stage, String fxml) {
        Integer flag = 0;
        if (stage == null) flag += 1;
        if (fxml == null || "".equals(fxml)) flag += 2;

        switch (flag) {
            case 0:
                stage.setScene(new Scene(getParent(fxml)));
                stage.setTitle(bundle.getString("error.undefined"));
                stage.show();   
                logger.log(Level.INFO, "Create a new stage from path : {0}", fxml.toString());
                break;
            case 1: System.err.println("stage is null!");
                break;
            case 2: System.err.println("fxml is null!");
                break;
            case 3: System.err.println("stage and fxml is null!");
                break;
            default: 
                break;
        }
        return flag;
    }
    
    /**
     *
     * @param stage
     * @param fxml
     * @return true on success, false on fail
     */
    public Integer changeStage(Stage stage, String fxml) {
        Integer flag = 0;
        if (stage == null) flag += 1;
        if (fxml == null || "".equals(fxml)) flag += 2;

        switch (flag) {
            case 0:
                Scene scene = new Scene(getParent(fxml), stage.getScene().getWidth(), stage.getScene().getHeight());
                stage.setScene(scene);
                stage.setTitle(bundle.getString("error.undefined"));
                stage.show();   
                break;
            case 1: System.err.println("stage is null!");
                break;
            case 2: System.err.println("fxml is null!");
                break;
            case 3: System.err.println("stage and fxml is null!");
                break;
            default: 
                break;
        }
        return flag;        

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
