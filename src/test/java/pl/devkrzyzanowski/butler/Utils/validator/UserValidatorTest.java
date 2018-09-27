/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Utils.validator;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class UserValidatorTest {
    
    private static UserValidator userValidator;
    private static List<String> validStrings;
    private static List<String> invalidStrings;
    
    public UserValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        userValidator = new UserValidator();
        validStrings = new ArrayList<>();
        validStrings.add("User43qq");
        validStrings.add("Administrator");
        validStrings.add("d3d3Admin");
        validStrings.add("operator");
        
        invalidStrings = new ArrayList<>();
        invalidStrings.add("6Fdggggv");
        invalidStrings.add("qqqq.eee");
        invalidStrings.add(" Administrator");
        invalidStrings.add("");       
        invalidStrings.add("Admini_nos");            
    }
   


    /**
     * Test of valide method, of class UserValidator.
     */
    @Test
    public void validUserTest() {
        System.out.println("validUserTest");
        validStrings.forEach((s) -> {
            boolean expResult = Boolean.TRUE;
            boolean result = userValidator.validate(s);
            System.out.println("User is valid : " + s + " ->  " + result);
            assertEquals(expResult, result);
        });
    }
    
    @Test
    public void invalidUserTest() {
        System.out.println("validUserTest");
        invalidStrings.forEach((s) -> {
            boolean expResult = Boolean.FALSE;
            boolean result = userValidator.validate(s);
            System.out.println("User is invalid : " + s + " ->  " + result);
            assertEquals(expResult, result);
        });
    }
    
}
