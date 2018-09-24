/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import pl.devkrzyzanowski.butler.MainApp;

/**
 *
 * @author MichalKrzyzanowski
 */
public class LoginPageController implements Initializable {

    @FXML private TextField loginTextField, dbNameTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private Button loginButton;
    private ResourceBundle rb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
    }   

    @FXML private void addDataBaseStructure(ActionEvent event) {
        MainApp.stageManager.addModalStage((Stage) ((Node) event.getSource())
                .getScene().getWindow(), "/fxml/addNewDataBaseDialog.fxml");
    }
    
    @FXML private void openDirectoryChooseDialog(ActionEvent event) {
        DirectoryChooser dch = new DirectoryChooser();
        File selectedDirectory = 
                dch.showDialog(((Node) event.getSource()).getScene().getWindow());
        if(selectedDirectory == null) {
            dbNameTextField.setText(rb.getString("name.noDirectorySelected"));
        } else {
            dbNameTextField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML private void handleLoginButton(ActionEvent event) throws IOException {
  
    }

    @FXML void showRegulations() {
        //TODO : INIT
    }  

    
    private void initValidators() {
        loginTextField.textProperty().addListener((observable) -> {
        
        });
        passwordTextField.textProperty().addListener((observable) -> {
         
        });
    }
}
