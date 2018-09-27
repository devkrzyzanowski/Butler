/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Utils.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class PasswordValidator {
    
    private final Pattern pattern;
    private Matcher matcher;
    
    private static final String PATTERN = 
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,20})";
    
    public PasswordValidator() {
        pattern = Pattern.compile(PATTERN);
    }
    
    public boolean validate(final String password) {
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
