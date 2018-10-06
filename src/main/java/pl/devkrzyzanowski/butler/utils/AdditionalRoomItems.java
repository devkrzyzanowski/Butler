/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Michał
 */
public class AdditionalRoomItems {
    ObservableList<AdditionalRoomItem> additionalRoomItems;

    
    public AdditionalRoomItems(){
        additionalRoomItems = FXCollections.observableArrayList();
        additionalRoomItems.add(new AdditionalRoomItem(0, "Balkon", Boolean.FALSE));
        additionalRoomItems.add(new AdditionalRoomItem(1, "Parawan", Boolean.FALSE));
        additionalRoomItems.add(new AdditionalRoomItem(2, "Koc", Boolean.FALSE));
        additionalRoomItems.add(new AdditionalRoomItem(3, "Leżak", Boolean.FALSE));
        additionalRoomItems.add(new AdditionalRoomItem(4, "Telewizor", Boolean.FALSE));
        additionalRoomItems.add(new AdditionalRoomItem(5, "Wi-Fi", Boolean.FALSE));
        additionalRoomItems.add(new AdditionalRoomItem(6, "Indywidualne wejście", Boolean.FALSE));
        additionalRoomItems.add(new AdditionalRoomItem(7, "Przyjazne zwierzętą", Boolean.FALSE));
        additionalRoomItems.add(new AdditionalRoomItem(8, "Czajnik", Boolean.FALSE));
        additionalRoomItems.add(new AdditionalRoomItem(9, "Zastawa stołowa", Boolean.FALSE));
        additionalRoomItems.add(new AdditionalRoomItem(10, "Lampka", Boolean.FALSE));
    }
    
    
    public ObservableList<AdditionalRoomItem> getAdditionalRoomItems() {
        return additionalRoomItems;
    }

    public void setAdditionalRoomItems(ObservableList<AdditionalRoomItem> additionalRoomItems) {
        this.additionalRoomItems = additionalRoomItems;
    }
}
