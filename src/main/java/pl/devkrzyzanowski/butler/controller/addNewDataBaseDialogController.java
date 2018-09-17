/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.controller;

import java.io.File;
import java.net.URL;
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
import pl.devkrzyzanowski.butler.Utils.Validator;

/**
 * FXML Controller class
 *
 * @author MichalKrzyzanowski
 */
public class addNewDataBaseDialogController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button addButton;
    @FXML
    private Button testConnectionButton;
    @FXML
    private Label testConnectionResult;
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
        initValidators();
    }    

    @FXML
    private void cancelAction(ActionEvent event) {
    }

    @FXML
    private void addStructure(ActionEvent event) {
    }

    @FXML
    private void testConnection(ActionEvent event) {
    }

    @FXML
    private void openDirectoryChooseDialog(ActionEvent event) {
        DirectoryChooser dch = new DirectoryChooser();
        File selectedDirectory = 
                dch.showDialog(((Node) event.getSource()).getScene().getWindow());
        if(selectedDirectory == null) {
            dbDirectoryTextField.setText(rb.getString("name.noDirectorySelected"));
        } else {
            dbDirectoryTextField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    private void initValidators() {
        dbUserTextField.textProperty().addListener((observable) -> {
                if (Validator.lengthBetween(dbUserTextField.getText(), 6, 12) == 0) {
                 dbUserTextField.setStyle("-fx-border-color: none;");   
                } else {
                 dbUserTextField.setStyle("-fx-border-color: red;");
                }
            }
       );
        
        dbUserTextField.textProperty().addListener((observable) -> {
                if (Validator.lengthBetween(dbUserTextField.getText(), 6, 12) == 0) {
                 dbUserTextField.setStyle("-fx-border-color: none;");   
                } else {
                 dbUserTextField.setStyle("-fx-border-color: red;");
                }
            }
       );

    }
}
