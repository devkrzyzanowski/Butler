/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Utils.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.TextField;

/**
 *
 * @author Admin
 */
public class UserValidator {
    
    public UserValidator(){}
    
    public int valide(TextField tf) {
        int flag = 0;
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{6,}");
        Matcher matcher = pattern.matcher(tf.getText());
        if (matcher.matches() == true) {
            tf.setStyle("-fx-border-color: none;");
        } else {
            tf.setStyle("-fx-border-color: red;");
        }
        return flag;
    }
    

}
