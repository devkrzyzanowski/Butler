/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Utils.validator;

import java.util.logging.Level;
import javafx.scene.control.TextField;
import static pl.devkrzyzanowski.butler.Utils.validator.Validator.lengthBetween;

/**
 *
 * @author Admin
 */
public class ValidatorTextField extends Validator{
    
    public ValidatorTextField(){}
    
    public int valideTextField(TextField tf) {
        int flag = lengthBetween(tf.getText(), 6, 12);
        if (flag == 0) {
            tf.setStyle("-fx-border-color: none;");
        } else {
            tf.setStyle("-fx-border-color: red;");
        }
        return flag;
    }
    
}
