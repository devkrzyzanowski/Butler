/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.utils;
import java.util.prefs.Preferences;
import pl.devkrzyzanowski.butler.MainApp;

/**
 *
 * @author Admin
 */
public class Pref {
    Preferences userPrefs;
    Preferences systemPrefs;
    
    
    public Pref() {
        userPrefs = Preferences.userNodeForPackage(MainApp.class);
        //systemPrefs = Preferences.systemNodeForPackage(MainApp.class);
    }
    
    private void setValue(Preferences pref, String key, String value) {
        pref.put(key, value);
    }
    
    public void setPrefUserName(String value) {
        setValue(userPrefs, "username", value);
    }
    public void setPrefDir(String value) {
        setValue(userPrefs, "dir", value);
    }
    public void setSaveUserNameCheckbox(Boolean value) {
        setValue(userPrefs, "saveUserNameCheckbox", value.toString());
    }
    
    public String getPrefUserName() {
        return userPrefs.get("username", "");
    }
    public String getPrefDir() {
        return userPrefs.get("dir", "");
    }
    public Boolean getSaveUserNameCheckBox() {
        return userPrefs.getBoolean("saveUserNameCheckbox", false);
    }
    
    public void setExpirationPassword(Integer value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

}
