/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Utils.validator;

import pl.devkrzyzanowski.butler.utils.validator.PasswordValidator;
import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michał Krzyżanowski
 */
public class PasswordValidatorTest {
    
    private static PasswordValidator passwordValidator;
    private static List<String> validPassword;
    private static List<String> invalidPassword;
    
    public PasswordValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        passwordValidator = new PasswordValidator();
        validPassword = new ArrayList<>();
        validPassword.add("Reddd3$2v");
        validPassword.add("55ddd@Utt");
        validPassword.add("TRegg4##6sssjf");
        
        invalidPassword = new ArrayList<>();
        invalidPassword.add("passwordpassword");
        invalidPassword.add("invlass44R");
        invalidPassword.add("qwe$$$344fff");
    }

    /**
     * Test of validate method, of class PasswordValidator.
     */
    @Test()
    public void validPasswordTest() {
        System.out.println("validPasswordTest");
        validPassword.forEach((s) -> {
            boolean expResult = Boolean.TRUE;
            boolean result = passwordValidator.validate(s);
            System.out.println("Password is valid :" + s + " - " + result);
            assertEquals(expResult, result);
        });
    }
    @Test()
    public void invalidPasswordTest() {
        System.out.println("invalidPasswordTest");
        invalidPassword.forEach((s) -> {
            boolean expResult = Boolean.FALSE;
            boolean result = passwordValidator.validate(s);
            System.out.println("Password is invalid :" + s + " - " + result);
            assertEquals(expResult, result);
        });
    }    
}
