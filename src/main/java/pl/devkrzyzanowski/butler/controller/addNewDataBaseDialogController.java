/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import pl.devkrzyzanowski.butler.Utils.FormRow;
import pl.devkrzyzanowski.butler.Utils.validator.TextFieldValidator;
import pl.devkrzyzanowski.butler.Utils.validator.UserValidator;

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
    private Label dbUrlLabel;
    @FXML private Label dbNameErrorLabel, dbUserErrorLabel, dbPasswordLabel, 
            dbPasswordCheckLabel;
    @FXML private FontAwesomeIconView dbUserValidIco, dbNameValidIco;
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
    private String dbName ="";
    
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
    private void openDirectoryChooseDialog(ActionEvent event) {
        DirectoryChooser dch = new DirectoryChooser();
        File selectedDirectory = 
                dch.showDialog(((Node) event.getSource()).getScene().getWindow());
        if(selectedDirectory == null) {
            dbDirectoryTextField.setText(rb.getString("name.noDirectorySelected"));
        } else {
            dbDir = selectedDirectory.getAbsolutePath();
            
            dbDirectoryTextField.setText(dbDir);
        }
    }
    
    private void updatedbUrlLabel() {
        dbUrlLabel.setText(dbDirectoryTextField.getText() + "\\" + dbNameTextField.getText());
    }

    private void initValidators() {
        FormRow dbu = new FormRow(dbUserTextField, dbUserValidIco, dbUserErrorLabel, rb);
        FormRow dbn = new FormRow(dbNameTextField, dbNameValidIco, dbNameErrorLabel, rb);
        
        dbNameTextField.textProperty().addListener((observable) -> {
            new TextFieldValidator().valide(dbNameTextField);
        });
        dbDirectoryTextField.textProperty().addListener((observable) -> {
            updatedbUrlLabel();
        });
        dbNameTextField.textProperty().addListener((observable) -> {
            dbn.valid();
        });
        dbUserTextField.textProperty().addListener(((observable) -> {
            dbu.valid();
//           UserValidator uv = new UserValidator();
//           int v = uv.valide(dbUserTextField);
//           if (v == 0) {
//               dbUserValidIco.setGlyphName("CHECK");
//               dbUserValidIco.setFill(Color.GREEN);
//           } else {
//               dbUserValidIco.setGlyphName("TIMES");
//               dbUserValidIco.setFill(Color.RED);               
//           }
        }));
    }
}
