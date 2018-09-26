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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import pl.devkrzyzanowski.butler.Utils.validator.DbNameValidator;
import pl.devkrzyzanowski.butler.Utils.validator.DirValidator;
import pl.devkrzyzanowski.butler.Utils.validator.PasswordValidator;
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
    @FXML private FontAwesomeIconView dbUserValidIco, dbNameValidIco, 
            dbDirectoryValidIco, dbPasswordValidIco, dbPasswordCheckValidIco;
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Message Here...");
alert.setHeaderText("Look, an Information Dialog");
alert.setContentText("I have a great message for you!");
alert.showAndWait().ifPresent(rs -> {
    if (rs == ButtonType.OK) {
        System.out.println("Pressed OK.");
    }
});
    }

    @FXML
    private void addStructure(ActionEvent event) {
        valid();
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
    
    private boolean setValidView(FontAwesomeIconView ico, boolean value) {
        if (value) {
            ico.setGlyphName("CHECK");
            ico.setFill(Color.GREEN);
            return true;
        } else {
            ico.setGlyphName("TIMES");
            ico.setFill(Color.RED);
            return false;
        }
    }
    
    private void valid() {
        setValidView(dbUserValidIco, new DirValidator().validate(dbDirectoryTextField.getText()));
        setValidView(dbNameValidIco, new DbNameValidator().validate(dbNameTextField.getText()));
        setValidView(dbDirectoryValidIco, new UserValidator().validate(dbUserTextField.getText()));
        setValidView(dbPasswordValidIco, new PasswordValidator().validate(dbPasswordPasswordField.getText()));    
        setValidView(dbPasswordCheckValidIco, new PasswordValidator().validate(dbPasswordCheckPasswordField.getText()));
    }
    
}
