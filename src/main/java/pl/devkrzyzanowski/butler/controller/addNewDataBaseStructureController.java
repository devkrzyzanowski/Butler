/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MichalKrzyzanowski
 */
public class addNewDataBaseStructureController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button addButton;
    @FXML
    private Button testConnectionButton;
    @FXML
    private Label testConnectionResult;
    @FXML
    private TextField dbNameTextField;
    @FXML
    private TextField dbUserTextField;
    @FXML
    private PasswordField dbPasswordPasswordField;
    @FXML
    private Button selectDirectoryButton;
    @FXML
    private TextField directoryTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void selectDirectory(ActionEvent event) {
    }
    
}
