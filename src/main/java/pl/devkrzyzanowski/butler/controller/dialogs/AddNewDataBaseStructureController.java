/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.controller.dialogs;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.utils.validators.DbNameValidator;
import pl.devkrzyzanowski.butler.utils.validators.DirValidator;
import pl.devkrzyzanowski.butler.utils.validators.PasswordValidator;
import pl.devkrzyzanowski.butler.utils.validators.UserValidator;

/**
 * FXML Controller class
 *
 * @author MichalKrzyzanowski
 */
public class AddNewDataBaseStructureController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button addButton;
    @FXML
    private Label dbUrlLabel;
    @FXML private Label dbNameErrorLabel, dbUserErrorLabel, dbPasswordLabel, 
            dbPasswordCheckLabel;
    @FXML
    private TextField dbDirectoryTextField;    
    @FXML
    private TextField dbNameTextField;
    @FXML
    private TextField dbUserTextField;
    @FXML
    private PasswordField dbPasswordPasswordField;
    @FXML
    private PasswordField dbPasswordCheckPasswordField;
    @FXML
    private Button selectDirectoryButton;
    @FXML
    private TextField directoryTextField;
    
    private ResourceBundle rb;
    
    private String dbDir = "";
    private String dbName = "";
    private String dbUser = "";
    private String dbPassword = "";
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
        dbDirectoryTextField.textProperty().addListener((observable) -> {
            updatedbUrlLabel();
        });
        dbNameTextField.textProperty().addListener((observable) -> {
            updatedbUrlLabel();
        });
    }    

    @FXML
    private void cancelAction(ActionEvent event) {
    }

    @FXML
    private void addStructure(ActionEvent event) {
        if (valid()) {
        dbDir = dbDirectoryTextField.getText();
        dbName = dbNameTextField.getText();
        dbUser = dbUserTextField.getText();
        dbPassword = dbPasswordPasswordField.getText();
        
        MainApp.databaseManager.loadDriver();
        MainApp.databaseManager.create(dbDir, dbName);
        MainApp.databaseManager.addReadWriteUser(dbUser, dbPassword);
        MainApp.databaseManager.initAuthentication();
        MainApp.databaseManager.close();
        }
    }

    @FXML
    private void openDirectoryChooseDialog(ActionEvent event) {
        DirectoryChooser dch = new DirectoryChooser();
        File selectedDirectory = 
                dch.showDialog(((Node) event.getSource()).getScene().getWindow());
        if(selectedDirectory == null) {
            dbDirectoryTextField.setText(rb.getString("name.noDirectorySelected"));
        } else {
            dbDir = selectedDirectory.getAbsolutePath() + "\\";
            dbDirectoryTextField.setText(dbDir);
        }
    }
    
    private void updatedbUrlLabel() {
        dbUrlLabel.setText(dbDirectoryTextField.getText() + dbNameTextField.getText());
    }
    
    private boolean valid() {
        boolean flag = true;
        List<Boolean> validList = new ArrayList<>();
        validList.add(new DirValidator()
                .validate(dbDirectoryTextField.getText()));
        validList.add(new DbNameValidator()
                .validate(dbNameTextField.getText()));
        validList.add(new UserValidator()
                .validate(dbUserTextField.getText()));
        validList.add(new PasswordValidator()
                .validate(dbPasswordPasswordField.getText()));
        validList.add(new PasswordValidator()
                .validate(dbPasswordCheckPasswordField.getText()));
        
        for (Boolean b : validList) {
            System.out.println("create valid: " +flag);
            if (!b) { 
               flag = false;
            }
        }
        return flag;
    }
}
