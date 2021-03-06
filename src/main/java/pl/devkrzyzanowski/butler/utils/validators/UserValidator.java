/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.utils.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Michał Krzyżanowski 
 */
public class UserValidator {
    
    private final Pattern pattern;
    private Matcher matcher;
    
    private static final String PATTERN = 
            "[a-zA-Z]{1}[a-zA-Z0-9]{6,20}";
    
    public UserValidator() {
        pattern = Pattern.compile(PATTERN);
    }
    
    /**
     * Validate username with regex
     * @param password for validation
     * @return true - valid user, false - invalid user
     */
    public boolean validate(final String password) {
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
