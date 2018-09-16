/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Utils;

/**
 *
 * @author Admin
 */
public class Validator {
    
    
    public Validator(){}
    
    public int lengthBetween(String text, int min, int max) {
        int flag = 0;
        flag = (text.length() < min) ? flag += 1 : null;
        flag = (text.length() > max) ? flag += 2 : null;        
        return flag;
    }
    
}
