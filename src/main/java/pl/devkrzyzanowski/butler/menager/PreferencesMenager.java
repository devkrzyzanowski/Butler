/*
 * Copyright (C) 2018 Michal Krzyzanowski
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pl.devkrzyzanowski.butler.menager;
import java.util.prefs.Preferences;
import pl.devkrzyzanowski.butler.MainApp;

/**
 *
 * @author Admin
 */
public class PreferencesMenager {
    /** logged user preferences */
    private final Preferences userPrefs;
    /** system preferences */
    private Preferences systemPrefs;
    /** key name for save username checkbox */
    private final String SAVE_USERNAME_CHECKBOX = "save_username_checkbox";
    /** key name for username */
    private final String USERNAME = "username";
    /** key name for directory */
    private final String DIR = "dir";
    /** key name for selected leanguage */
    private final String LOCATION = "Location";
    
    public PreferencesMenager() {
        userPrefs = Preferences.userNodeForPackage(MainApp.class);
        //systemPrefs = Preferences.systemNodeForPackage(MainApp.class);
    }
    
    private void setValue(Preferences pref, String key, String value) {
        pref.put(key, value);
    }
    
    public void setPrefUserName(String value) {
        setValue(userPrefs, USERNAME, value);
    }
    public void setPrefDir(String value) {
        setValue(userPrefs, DIR, value);
    }
    public void setSaveUserNameCheckbox(Boolean value) {
        setValue(userPrefs, SAVE_USERNAME_CHECKBOX, value.toString());
    }
    
    public String getPrefUserName() {
        return userPrefs.get(USERNAME, "");
    }
    public String getPrefDir() {
        return userPrefs.get(DIR, "");
    }
    public Boolean getSaveUserNameCheckBox() {
        return userPrefs.getBoolean(SAVE_USERNAME_CHECKBOX, false);
    }
    
    public void setExpirationPassword(Integer value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

}
