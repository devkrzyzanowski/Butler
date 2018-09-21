/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Utils.validator;

import javafx.scene.control.TextField;
import static pl.devkrzyzanowski.butler.Utils.validator.Validator.lengthBetween;

/**
 *
 * @author Admin
 */
public class TextFieldValidator extends Validator{
    
    public TextFieldValidator(){}
    
    public int valide(TextField tf) {
        int flag = lengthBetween(tf.getText(), 6, 12);
        if (flag == 0) {
            tf.setStyle("-fx-border-color: none;");
        } else {
            tf.setStyle("-fx-border-color: red;");
        }
        return flag;
    }
    
}
