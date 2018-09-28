/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.utils.validators;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import pl.devkrzyzanowski.butler.utils.validators.IValidator;

/**
 *
 * @author Admin
 */
public class DirValidator implements IValidator{
    
    private final Pattern pattern;
    private Matcher matcher;
    
    private static final String PATTERN =
            "([c-hC-H]:\\\\){1}([\\w]*\\\\)*";
    
    public DirValidator() {
        pattern = Pattern.compile(PATTERN);
    }

    @Override
    public boolean validate(String data) {
        matcher = pattern.matcher(data);
        return matcher.matches();
    }
    
}
