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
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 *
 * @author MichalKrzyzanowski
 */
public class StageManager {
    private ResourceBundle bundle;
    private FXMLLoader lLoader;
    protected final Logger logger = Logger.getLogger(getClass().getName());
    private Stage stage;
    /**
     *
     */
    public StageManager(Stage stage) {
        this.stage = stage;
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
    public Integer newStage(String fxml) {
        Integer flag = 0;
        if (stage == null) flag += 1;
        if (fxml == null || "".equals(fxml)) flag += 2;

        switch (flag) {
            case 0:
                stage.setScene(new Scene(getParent(fxml)));
                stage.setResizable(false);
                stage.sizeToScene();
                stage.setTitle(bundle.getString("error.undefined"));
                stage.show();   
                logger.log(Level.INFO, "Create a new stage from path : {0}", fxml);
                break;
            case 1: logger.log(Level.WARNING, "stage variable is null!");
                break;
            case 2: logger.log(Level.WARNING, "fxml variable is null!");
                break;
            case 3: logger.log(Level.WARNING, "stage and fxml variable is null!");
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
     * @return 0 on succes, 1 if stage is null, 2 if fxml is null, 3 if stage 
     * and fxml is null
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
            case 1: logger.log(Level.WARNING, "stage variable is null!");
                break;
            case 2: logger.log(Level.WARNING, "fxml variable is null!");
                break;
            case 3: logger.log(Level.WARNING, "stage and fxml variable is null!");
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
     * @return 0 on succes, 1 if stage is null, 2 if fxml is null, 3 if stage 
     * and fxml is null
     */
    public Stage addModalStage(Window owner, String fxml) {
        Stage newStage = new Stage();
        Integer flag = 0;
        if (owner == null) flag += 1;
        if (fxml == null || "".equals(fxml)) flag += 2;

        switch (flag) {
            case 0:
                Scene scene = new Scene(getParent(fxml));
                newStage.setScene(scene);
                newStage.initModality(Modality.WINDOW_MODAL);
                newStage.initOwner(owner);
                newStage.setTitle(bundle.getString("error.undefined"));
                newStage.show();   
                break;
            case 1: logger.log(Level.WARNING, "owner variable is null!");
                break;
            case 2: logger.log(Level.WARNING, "fxml variable is null!");
                break;
            case 3: logger.log(Level.WARNING, "owner and fxml variable is null!");
                break;
            default: 
                break;
        }
        return newStage;            
    }
    
    public Stage addModalStageWithoutMaximize(Window owner, String fxml) {
       Stage newStage = new Stage();
        Integer flag = 0;
        if (owner == null) flag += 1;
        if (fxml == null || "".equals(fxml)) flag += 2;
        
        switch (flag) {
            case 0:
                Scene scene = new Scene(getParent(fxml));
                newStage.setScene(scene);
                newStage.initModality(Modality.WINDOW_MODAL);
                newStage.initOwner(owner);
                newStage.setTitle(bundle.getString("error.undefined"));
                newStage.setResizable(false);
                newStage.sizeToScene();
                newStage.show();   
                break;
            case 1: logger.log(Level.WARNING, "owner variable is null!");
                break;
            case 2: logger.log(Level.WARNING, "fxml variable is null!");
                break;
            case 3: logger.log(Level.WARNING, "owner and fxml variable is null!");
                break;
            default: 
                break;
        }
        return newStage;           
    }
    
    private Parent getParent(String fxml) {
        lLoader = new FXMLLoader(getClass().getResource(fxml), bundle);
            Parent root;
        try {
            root = lLoader.load();
        } catch (IOException e) {
            System.err.println("error creating parent root! " + e);
            root = null;
        }
        return root;
    }
    
    public FXMLLoader getLoader() {
        return lLoader;
    }
}
