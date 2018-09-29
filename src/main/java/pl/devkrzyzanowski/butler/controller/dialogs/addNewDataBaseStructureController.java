/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.controller.dialogs;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.Model.Database;

/**
 *
 * @author Michał
 */
public class addNewDataBaseStructureController extends DialogBox implements Initializable {
    @FXML private Button cancelButton;
    @FXML private TextField directoryTextField, dbNameTextField, dbUserTextField;
    @FXML private PasswordField dbPasswordPasswordField;
    @FXML private Label testConnectionResult;
    private Database db;
    
    private ObservableList<String> dbTypes = FXCollections.observableArrayList("JavaDB", "MySQL", "Oracle", "MSSQL");
    @FXML private Button addButton;
    @FXML private Button testConnectionButton;
    @FXML private Button selectDirectoryButton;
       
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = MainApp.databaseManager;
    }
    
    @FXML private void addStructure(ActionEvent event){
        db.loadDriver();
        //db.createDataBase(directoryTextField.getText(), dbNameTextField.getText(), dbUserTextField.getText(), dbPasswordPasswordField.getText());
    }
    
    @FXML    private void selectDirectory(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = 
                directoryChooser.showDialog(((Node) event.getSource()).getScene().getWindow());
                 
        if(selectedDirectory == null) {
            directoryTextField.setText("No Directory selected");
        } else {
            directoryTextField.setText(selectedDirectory.getAbsolutePath());
        }
    }
    
    @FXML private void testConnection(ActionEvent event) {
        if (true) {
            testConnectionResult.setText("UDANE");
            testConnectionResult.setStyle("-fx-background-color: #009900;");
        } else {
            testConnectionResult.setText("NIEUDANE");
            testConnectionResult.setStyle("-fx-background-color: #990000;");            
        }
    }

}
