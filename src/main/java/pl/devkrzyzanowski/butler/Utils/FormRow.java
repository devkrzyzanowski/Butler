/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Utils;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.util.ResourceBundle;
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
        private ResourceBundle rb;
        
    public FormRow() {}

    public FormRow(TextField textField, FontAwesomeIconView ico, Label errorLabel, ResourceBundle rb) {
        this.textField = textField;
        this.ico = ico;
        this.errorLabel = errorLabel;
        this.rb = rb;
    }
    
    public void valid() {
           UserValidator uv = new UserValidator();
           int vs = uv.valideTextToShort(textField);
           int vl = uv.valideTextToLong(textField);
           String errorMessage = "";
           if (vs != 0) {
               errorMessage = rb.getString("errorMessage.nameToLong");
           }
           if (vl != 0) {
                   errorMessage = rb.getString("errorMessage.nameToShort");
           }
           
           if (vs == 0 && vl == 0) {
               ico.setGlyphName("CHECK");
               ico.setFill(Color.GREEN);
               errorLabel.setText(null);
           } else {
               ico.setGlyphName("TIMES");
               ico.setFill(Color.RED);    
               errorLabel.setText(errorMessage);
           }
    }

}
