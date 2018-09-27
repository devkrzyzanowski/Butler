/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Utils.validator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author Admin
 */
public class PostCodeValidator {
    private final Pattern pattern;
    private Matcher matcher;
    
    private static final String PATTERN =
            "^\\d{2}-\\d{3}$";
    
    private PostCodeValidator() {
        pattern = Pattern.compile(PATTERN);
    }
    
    public boolean validate(final String data) {
        matcher = pattern.matcher(data);
        return matcher.matches();
    }
}
