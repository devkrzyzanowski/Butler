/*
 * Copyright (C) 2018 Michal Krzyzanowski
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
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

    /** */
    @FXML
    private TextField usernameTextField;
    /** */    
    @FXML
    private TextField dirTextField;
    /** */
    @FXML
    private PasswordField passwordTextField;
    /** */
    @FXML
    private Button loginButton;
    /** */
    @FXML
    private CheckBox rememberLoginCheckBox;
    /** */
    private ResourceBundle rb;
    /** */
    private PreferencesMenager preferencesMenager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
        preferencesMenager = new PreferencesMenager();      
        usePreferences();
    }   

    /**
     * Opening dialog with add database content
     * @param event 
     */
    @FXML
    private void addDataBaseStructure(ActionEvent event) {
         MainApp.stageManager.addModalStageWithoutMaximize((Stage) ((Node) event.getSource())
                .getScene().getWindow(), "/fxml/addNewDataBaseDialog.fxml");
    }
    
    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleLoginButton(ActionEvent event) {
        updatePrefs();
        MainApp.databaseManager.loadDriver();
        
        if (MainApp.databaseManager.login(dirTextField.getText(), usernameTextField.getText(), passwordTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText("Login success");
            alert.setContentText("Login success");
            alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
            MainApp.stageManager.changeStage((Stage) ((Node) event.getSource())
            .getScene().getWindow(), "/fxml/mainPage.fxml");
            }
        });
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login");
            alert.setHeaderText("Login failed");
            alert.setContentText("Login failed");
            alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed ERROR.");
            }
        });   
        }
    }
    
    /*
    
    /**
    * Open dialog with directory choose
    */
    @FXML
    private void openDirectoryChooseDialog(ActionEvent event) {
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
