/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Utils.validator;

import pl.devkrzyzanowski.butler.utils.validator.DirValidator;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class DirValidatorTest {
    
    private static DirValidator dirValidator;
    private static List<String> validStrings;
    private static List<String> invalidStrings;
    
    
    public DirValidatorTest() {
        dirValidator = new DirValidator();
        validStrings = new ArrayList<>();
        validStrings.add("C:\\Users\\Admin\\");
        validStrings.add("D:\\g\\");
        validStrings.add("G:\\dir\\");
        
        invalidStrings = new ArrayList<>();
        invalidStrings.add("no dir selection");
        invalidStrings.add(":\\Users\\");
        invalidStrings.add("C:");
        invalidStrings.add("C:\\Documents");
        invalidStrings.add("");
    }
    
    @Test
    public void validDirTest() {
        System.out.println("validDirTest");
        validStrings.forEach((s) -> {
            boolean expResult = Boolean.TRUE;
            boolean result = dirValidator.validate(s);
            System.out.println("Dir is valid : " + s + " ->  " + result);
            assertEquals(expResult, result);
        });
    }
    
    @Test
    public void invalidDirTest() {
        System.out.println("validDirTest");
        invalidStrings.forEach((s) -> {
            boolean expResult = Boolean.FALSE;
            boolean result = dirValidator.validate(s);
            System.out.println("Dir is invalid : " + s + " ->  " + result);
            assertEquals(expResult, result);
        });
    }
    
}
