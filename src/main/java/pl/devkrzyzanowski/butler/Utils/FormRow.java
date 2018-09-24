/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Utils;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 *
 * @author Admin
 */
public class FormRow {
        @FXML private Label nameLabel, errorLabel;
        @FXML private TextField textField;
        private FontAwesomeIconView ico;
        private ResourceBundle rb;
        
    public FormRow() {}

    public FormRow(TextField textField, FontAwesomeIconView ico, Label errorLabel, ResourceBundle rb) {
        this.textField = textField;
        this.ico = ico;
        this.errorLabel = errorLabel;
        this.rb = rb;
    }
    public FormRow(TextField textField, FontAwesomeIconView ico, ResourceBundle rb) {
        this.textField = textField;
        this.ico = ico;
        this.rb = rb;
        errorLabel = null;
    }
    
    public void validPasswordTextField() {
        
    }
    
    public boolean validText(String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(textField.getText());
        return matcher.matches();
    }
    
//    public void validTextField() {
//        boolean vIsFirstChar = validator.isFirstChar(textField.getText());
//        boolean vToShort = validator.isToShort(textField.getText(), 6);
//        boolean vToLong = validator.isToLong(textField.getText(), 12);
//        String errorMessage = "";
//        
//
//        if (vToShort == true) {
//            errorMessage = rb.getString("errorMessage.nameToShort");
//        }
//        if (vToLong == true) {
//            errorMessage = rb.getString("errorMessage.nameToLong");
//        }
//        if (vIsFirstChar == false) {
//            errorMessage = rb.getString("errorMessage.needFirstChar");           
//        }
//        
//        if (errorMessage.equals("")) {
//            ico.setGlyphName("CHECK");
//            ico.setFill(Color.GREEN);
//            errorLabel.setText("");            
//        } else {
//            ico.setGlyphName("TIMES");
//            ico.setFill(Color.RED);
//            errorLabel.setText(errorMessage);            
//        }
        
        
        
//        if (vToShort == false && vToLong == false) {
//            ico.setGlyphName("CHECK");
//            ico.setFill(Color.GREEN);
//            if (errorLabel != null){
//            errorLabel.setText(null);
//            }
//        } else {
//            ico.setGlyphName("TIMES");
//            ico.setFill(Color.RED);    
//            if (errorLabel != null){
//            errorLabel.setText(errorMessage);
//            }
//        }
//    }
}
