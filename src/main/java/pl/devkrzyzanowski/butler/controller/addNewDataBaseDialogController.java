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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;

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
            dbDir = selectedDirectory.getAbsolutePath();
            dbDirectoryTextField.setText(dbDir);
        }
    }
    
    private void updatedbUrlLabel() {
        dbUrlLabel.setText(dbDirectoryTextField.getText() + "\\" + dbNameTextField.getText());
    }
    
    private void valid() {
        if (validTextField(dbNameTextField, "[A-Za-z]+")) {
            dbNameValidIco.setGlyphName("CHECK");
            dbNameValidIco.setFill(Color.GREEN);
        } else {
            dbNameValidIco.setGlyphName("TIMES");
            dbNameValidIco.setFill(Color.RED);            
        }
        if (validTextField(dbUserTextField, "[A-Za-z]+")) {
            dbUserValidIco.setGlyphName("CHECK");
            dbUserValidIco.setFill(Color.GREEN);
        } else {
            dbUserValidIco.setGlyphName("TIMES");
            dbUserValidIco.setFill(Color.RED);            
        }
        if (validTextField(dbPasswordPasswordField, "[A-Za-z]+")) {
            dbPasswordValidIco.setGlyphName("CHECK");
            dbPasswordValidIco.setFill(Color.GREEN);
        } else {
            dbPasswordValidIco.setGlyphName("TIMES");
            dbPasswordValidIco.setFill(Color.RED);            
        }
        if (validTextField(dbPasswordCheckPasswordField, "[A-Za-z]+")) {
            dbPasswordCheckValidIco.setGlyphName("CHECK");
            dbPasswordCheckValidIco.setFill(Color.GREEN);
        } else {
            dbPasswordCheckValidIco.setGlyphName("TIMES");
            dbPasswordCheckValidIco.setFill(Color.RED);            
        }
    }
    
    private boolean validTextField(TextField tf, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tf.getText());
        boolean flag = matcher.matches();
        if (flag) {
            tf.setStyle("-fx-border-color: none;");
        } else {
            tf.setStyle("-fx-border-color: red;");    
        }
        return flag;
    }

//    private void initValidators() {
//        FormRow dburl = new FormRow(dbDirectoryTextField, dbDirectoryValidIco, rb);
//        FormRow dbu = new FormRow(dbUserTextField, dbUserValidIco, dbUserErrorLabel, rb);
//        FormRow dbn = new FormRow(dbNameTextField, dbNameValidIco, dbNameErrorLabel, rb);
//        
//        dbDirectoryTextField.textProperty().addListener((observable) -> {
//            dburl.validTextField();
//            updatedbUrlLabel();
//        });
//        dbNameTextField.textProperty().addListener((observable) -> {
//            dbn.validTextField();
//            updatedbUrlLabel();
//        });
//        dbUserTextField.textProperty().addListener(((observable) -> {
//            dbu.validTextField();
//        }));
//    }
}
