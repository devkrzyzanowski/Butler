/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.controller.fragments;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.devkrzyzanowski.butler.MainApp;

/**
 *
 * @author Michał
 */
public class MenuBarController implements Initializable {
    @FXML MenuBar menuBar;
    @FXML AnchorPane anchorPane;
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    @FXML public void openLicenseDialog(ActionEvent event) {
        
        MainApp.stageManager.addModalStage((Stage) anchorPane.getScene().getWindow(), "/butler/view/dialogs/licenseDialog.fxml");
    }
}
