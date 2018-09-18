/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Utils.validator;

import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Validator {
    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    public Validator(){}
    
    public static int lengthBetween(String text, int min, int max) {
        int flag = 0;
        flag = (text.length() < min) ? flag += 1 : flag;
        flag = (text.length() > max) ? flag += 2 : flag;
        return flag;
    }
    
    public static boolean isEmpty(String text) {
       return text.isEmpty();
    }
    
    public static int isEmailBuild(String text) {
        // TODO : INIT
        return 1;
    }
    
    public static int isPasswordQuality() {
        // TODO : INIT
        return 1;
    }
    
    public static int percentPasswordQuality() {
        // TODO : INIT
        return 1;
    }
    
}
