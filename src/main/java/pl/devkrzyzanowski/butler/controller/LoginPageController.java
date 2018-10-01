/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.menager.PreferencesMenager;

/**
 *
 * @author MichalKrzyzanowski
 */
public class LoginPageController implements Initializable {

    @FXML private TextField usernameTextField, dirTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private Button loginButton;
    @FXML private CheckBox rememberLoginCheckBox;
    
    private ResourceBundle rb;
    private PreferencesMenager preferencesMenager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
        preferencesMenager = new PreferencesMenager();
        
        usePreferences();


    }   

    @FXML private void addDataBaseStructure(ActionEvent event) {
         MainApp.stageManager.addModalStageWithoutMaximize((Stage) ((Node) event.getSource())
                .getScene().getWindow(), "/fxml/addNewDataBaseDialog.fxml");
    }
    
    @FXML private void handleLoginButton(ActionEvent event) throws IOException {
        updatePrefs();
        MainApp.databaseManager.loadDriver();
        
        if (MainApp.databaseManager.login(dirTextField.getText(), usernameTextField.getText(), passwordTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Message Here...");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("I have a great message for you!");
            alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
        MainApp.stageManager.changeStage((Stage) ((Node) event.getSource())
            .getScene().getWindow(), "/fxml/bookingSchedulePage.fxml");
            }
        });
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message Here...");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("I have a great message for you!");
            alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed ERROR.");
            }
        });   
        }
    }
    
    
    @FXML private void openDirectoryChooseDialog(ActionEvent event) {
        DirectoryChooser dch = new DirectoryChooser();
        File selectedDirectory = 
                dch.showDialog(((Node) event.getSource()).getScene().getWindow());
        if(selectedDirectory == null) {
            dirTextField.setText(rb.getString("name.noDirectorySelected"));
        } else {
            dirTextField.setText(selectedDirectory.getAbsolutePath());
        }
    }
    
    private void updatePrefs() {
        if (rememberLoginCheckBox.selectedProperty().getValue()) {
            new PreferencesMenager().setPrefUserName(usernameTextField.getText());
        } else {
            new PreferencesMenager().setPrefUserName("");            
        }
        if (!dirTextField.getText().equals("")) {
            new PreferencesMenager().setPrefDir(dirTextField.getText());
        }
        preferencesMenager.setSaveUserNameCheckbox(rememberLoginCheckBox.selectedProperty().getValue());        
    }
    
    private void usePreferences() {
        if (!preferencesMenager.getPrefUserName().equals("")) {
            usernameTextField.setText(preferencesMenager.getPrefUserName());
        } else {
            usernameTextField.setText("");
        }
        if (!preferencesMenager.getPrefDir().equals("")) {
            dirTextField.setText(preferencesMenager.getPrefDir());
        } else {
            dirTextField.setText("");
        }
            rememberLoginCheckBox.setSelected(preferencesMenager.getSaveUserNameCheckBox());
    }
    
    @FXML void showRegulations() {
        //TODO : INIT
    }  

    
    private void initValidators() {
        usernameTextField.textProperty().addListener((observable) -> {
        
        });
        passwordTextField.textProperty().addListener((observable) -> {
         
        });
    }
}
