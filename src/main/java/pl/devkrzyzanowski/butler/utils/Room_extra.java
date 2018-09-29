/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.utils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author MichalKrzyzanowski
 */
public class Room_extra {
    private BooleanProperty balcon = new SimpleBooleanProperty();
    private BooleanProperty beachScreen = new SimpleBooleanProperty();
    private BooleanProperty blanket = new SimpleBooleanProperty();
    private BooleanProperty sunbed = new SimpleBooleanProperty();
    private BooleanProperty tv = new SimpleBooleanProperty();
    private BooleanProperty wiFi = new SimpleBooleanProperty();
    private BooleanProperty individualEntrance = new SimpleBooleanProperty();
    private BooleanProperty friendlyAnimal = new SimpleBooleanProperty();
    private BooleanProperty kettle = new SimpleBooleanProperty();
    private BooleanProperty tableware = new SimpleBooleanProperty();
    private BooleanProperty tableLamp = new SimpleBooleanProperty();
    
    public Room_extra() {
        
    }
    
        public void setAdditionalRoomItems(ObservableList<AdditionalRoomItem> eI) {
        for (AdditionalRoomItem ari : eI){
            if (ari.getId() == 0) balcon.set(true);
            if (ari.getId() == 1) beachScreen.set(true);
            if (ari.getId() == 2) blanket.set(true);
            if (ari.getId() == 3) sunbed.set(true);
            if (ari.getId() == 4) tv.set(true);
            if (ari.getId() == 5) wiFi.set(true);
            if (ari.getId() == 6) individualEntrance.set(true);
            if (ari.getId() == 7) friendlyAnimal.set(true);
            if (ari.getId() == 8) kettle.set(true);
            if (ari.getId() == 9) tableware.set(true);
            if (ari.getId() == 10) tableLamp.set(true);
        }
    }

    public BooleanProperty getBalcon() {
        return balcon;
    }

    public void setBalcon(Boolean balcon) {
        this.balcon.setValue(balcon);
    }

    public BooleanProperty getBeachScreen() {
        return beachScreen;
    }

    public void setBeachScreen(Boolean beachScreen) {
        this.beachScreen.setValue(beachScreen);
    }

    public BooleanProperty getBlanket() {
        return blanket;
    }

    public void setBlanket(Boolean blanket) {
        this.blanket.setValue(blanket);
    }

    public BooleanProperty getSunbed() {
        return sunbed;
    }

    public void setSunbed(Boolean sunbed) {
        this.sunbed.setValue(sunbed);
    }

    public BooleanProperty getTv() {
        return tv;
    }

    public void setTv(Boolean tv) {
        this.tv.setValue(tv);
    }

    public BooleanProperty getWiFi() {
        return wiFi;
    }

    public void setWiFi(Boolean wiFi) {
        this.wiFi.setValue(wiFi);
    }

    public BooleanProperty getIndividualEntrance() {
        return individualEntrance;
    }

    public void setIndividualEntrance(Boolean individualEntrance) {
        this.individualEntrance.setValue(individualEntrance);
    }

    public BooleanProperty getFriendlyAnimal() {
        return friendlyAnimal;
    }

    public void setFriendlyAnimal(Boolean friendlyAnimal) {
        this.friendlyAnimal.setValue(friendlyAnimal);
    }

    public BooleanProperty getKettle() {
        return kettle;
    }

    public void setKettle(Boolean kettle) {
        this.kettle.setValue(kettle);
    }

    public BooleanProperty getTableware() {
        return tableware;
    }

    public void setTableware(Boolean tableware) {
        this.tableware.setValue(tableware);
    }

    public BooleanProperty getTableLamp() {
        return tableLamp;
    }

    public void setTableLamp(Boolean tableLamp) {
        this.tableLamp.setValue(tableLamp);
    }
}
