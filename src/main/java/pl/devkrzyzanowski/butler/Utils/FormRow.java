/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Utils;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.paint.Color;
import pl.devkrzyzanowski.butler.Utils.validator.UserValidator;
/**
 *
 * @author Admin
 */
public class FormRow {
        @FXML private Label nameLabel, errorLabel;
        @FXML private TextField textField;
        private FontAwesomeIconView ico;
        
    public FormRow() {}

    public FormRow(TextField textField, FontAwesomeIconView ico, Label errorLabel) {
        this.textField = textField;
        this.ico = ico;
        this.errorLabel = errorLabel;
    }
    
    public void valid() {
        
           UserValidator uv = new UserValidator();
           int v = uv.valide(textField);
           if (v == 0) {
               ico.setGlyphName("CHECK");
               ico.setFill(Color.GREEN);
           } else {
               ico.setGlyphName("TIMES");
               ico.setFill(Color.RED);               
           }
    }

    
}
