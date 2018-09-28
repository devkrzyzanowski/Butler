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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.utils.Pref;

/**
 *
 * @author MichalKrzyzanowski
 */
public class LoginPageController implements Initializable {

    @FXML private TextField loginTextField, dirTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private Button loginButton;
    @FXML private CheckBox rememberLoginCheckBox;
    private ResourceBundle rb;
    private Pref pref;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
        pref = new Pref();
        if (!pref.getPrefUserName().equals("")) {
        loginTextField.setText(pref.getPrefUserName());
        } else {
            loginTextField.setText("");
        }
        if (!pref.getPrefDir().equals("")) {
            dirTextField.setText(pref.getPrefDir());
        } else {
            dirTextField.setText("");
        }
            rememberLoginCheckBox.setSelected(pref.getSaveUserNameCheckBox());
    }   

    @FXML private void addDataBaseStructure(ActionEvent event) {
        
        Stage stage = MainApp.stageManager.addModalStage((Stage) ((Node) event.getSource())
                .getScene().getWindow(), "/fxml/addNewDataBaseDialog.fxml");
        stage.setResizable(false);
        stage.sizeToScene();
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

    @FXML private void handleLoginButton(ActionEvent event) throws IOException {
        if (rememberLoginCheckBox.selectedProperty().getValue()) {
            new Pref().setPrefUserName(loginTextField.getText());
        } else {
            new Pref().setPrefUserName("");            
        }
        if (!dirTextField.getText().equals("")) {
            new Pref().setPrefDir(dirTextField.getText());
        }
        pref.setSaveUserNameCheckbox(rememberLoginCheckBox.selectedProperty().getValue());
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
