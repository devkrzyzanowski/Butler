/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Utils.validator;

import pl.devkrzyzanowski.butler.utils.validators.DbNameValidator;
import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class DbNameValidatorTest {
    
    private static DbNameValidator dbNameValidator;
    private static List<String> validStrings;
    private static List<String> invalidStrings;
    
    public DbNameValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dbNameValidator = new DbNameValidator();
        validStrings = new ArrayList<>();
        validStrings.add("database");
        validStrings.add("datDDbase");
        validStrings.add("BASEssss");
        validStrings.add("databaSe");
        
        invalidStrings = new ArrayList<>();
        invalidStrings.add("data base");
        invalidStrings.add("data2Base");
        invalidStrings.add("dataBase!");
        invalidStrings.add("dat");
    }

    @Test
    public void validDbNameTest() {
        System.out.println("validDbNameTest");
        validStrings.forEach((s) -> {
            boolean expResult = Boolean.TRUE;
            boolean result = dbNameValidator.validate(s);
            System.out.println("DbName is valid : " + s + " ->  " + result);
            assertEquals(expResult, result);
        });
    }
    
    @Test
    public void invalidDbNameTest() {
        System.out.println("validDbNameTest");
        invalidStrings.forEach((s) -> {
            boolean expResult = Boolean.FALSE;
            boolean result = dbNameValidator.validate(s);
            System.out.println("DbName is invalid : " + s + " ->  " + result);
            assertEquals(expResult, result);
        });
    }
}
