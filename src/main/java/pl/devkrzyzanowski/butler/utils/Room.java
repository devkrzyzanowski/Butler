/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.utils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author uwxyy
 */
public class Room {
    private Room_extra room_extra;
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty roomName = new SimpleStringProperty();
    private IntegerProperty numberOfSingleBeds = new SimpleIntegerProperty();
    private IntegerProperty numberOfDoubleBeds = new SimpleIntegerProperty();
    private IntegerProperty numberOfExtraBeds = new SimpleIntegerProperty();
    private IntegerProperty floorNumber = new SimpleIntegerProperty();
    private DoubleProperty priceOfRoom = new SimpleDoubleProperty();
    private DoubleProperty priceOfAdult = new SimpleDoubleProperty();
    private DoubleProperty priceOfMinor = new SimpleDoubleProperty();
    private StringProperty smallDescription = new SimpleStringProperty();
    private StringProperty bigDescription = new SimpleStringProperty();
    private StringProperty extraDescription = new SimpleStringProperty();
    private StringProperty building = new SimpleStringProperty();

    
    
    public Room(Integer id, String roomName, Integer numberOfSingleBeds, Integer numberOfDoubleBeds,
                Integer numberOfExtraBeds, Integer floorNumber, Double priceOfRoom,
                Double priceOfAdult, Double priceOfMinor, String smallDescription,
                String bigDescription, String extraDescription, String building, 
                Boolean balcon, Boolean beachScreen, Boolean blanket, Boolean sunbed, 
                Boolean tv, Boolean wiFi, Boolean individualEntrance, 
                Boolean friendlyAnimal, Boolean kettle,
                Boolean tableware, Boolean tableLamp){
        room_extra = new Room_extra();
        this.id.set(id);
        this.roomName.set(roomName);
        this.numberOfSingleBeds.set(numberOfSingleBeds);
        this.numberOfDoubleBeds.set(numberOfDoubleBeds);
        this.numberOfExtraBeds.set(numberOfExtraBeds);
        this.floorNumber.set(floorNumber);
        this.priceOfRoom.set(priceOfRoom);
        this.priceOfAdult.set(priceOfAdult);
        this.priceOfMinor.set(priceOfMinor);
        this.smallDescription.set(smallDescription);
        this.bigDescription.set(bigDescription);
        this.extraDescription.set(extraDescription);
        this.building.set(building);
        room_extra.setBalcon(balcon);
        room_extra.setBeachScreen(beachScreen);
        room_extra.setBlanket(blanket);
        room_extra.setSunbed(sunbed);
        room_extra.setTv(tv);
        room_extra.setWiFi(wiFi);
        room_extra.setIndividualEntrance(individualEntrance);
        room_extra.setFriendlyAnimal(friendlyAnimal);
        room_extra.setKettle(kettle);
        room_extra.setTableware(tableware);
        room_extra.setTableLamp(tableLamp);
    }
    
        public Room(Integer id, String roomName, Integer numberOfSingleBeds, Integer numberOfDoubleBeds,
                Integer numberOfExtraBeds, Integer floorNumber, Double priceOfRoom,
                Double priceOfAdult, Double priceOfMinor, String smallDescription,
                String bigDescription, String extraDescription, String building, 
                ObservableList<AdditionalRoomItem> eI){
                    room_extra = new Room_extra();
        this.id.set(id);
        this.roomName.set(roomName);
        this.numberOfSingleBeds.set(numberOfSingleBeds);
        this.numberOfDoubleBeds.set(numberOfDoubleBeds);
        this.numberOfExtraBeds.set(numberOfExtraBeds);
        this.floorNumber.set(floorNumber);
        this.priceOfRoom.set(priceOfRoom);
        this.priceOfAdult.set(priceOfAdult);
        this.priceOfMinor.set(priceOfMinor);
        this.building.set(building);
        room_extra.setAdditionalRoomItems(eI);
    }

    public Integer getId() {
        return id.getValue();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }
    public void setId(IntegerProperty id) {
        this.id = id;
    }
        public Room(String roomName, Integer numberOfSingleBeds, Integer numberOfDoubleBeds,
                Integer numberOfExtraBeds, Integer floorNumber, Double priceOfRoom,
                Double priceOfAdult, Double priceOfMinor, String smallDescription,
                String bigDescription, String extraDescription, String building, 
                ObservableList<AdditionalRoomItem> eI){
                    room_extra = new Room_extra();
        this.roomName.set(roomName);
        this.numberOfSingleBeds.set(numberOfSingleBeds);
        this.numberOfDoubleBeds.set(numberOfDoubleBeds);
        this.numberOfExtraBeds.set(numberOfExtraBeds);
        this.floorNumber.set(floorNumber);
        this.priceOfRoom.set(priceOfRoom);
        this.priceOfAdult.set(priceOfAdult);
        this.priceOfMinor.set(priceOfMinor);
        room_extra.setAdditionalRoomItems(eI);
    }
        
    @Override
    public String toString(){
        return roomName.getValue();
    }
        
    public Room getRoom(){
        return this;
    }

    public String getRoomName() {
        return roomName.getValue();
    }

    public void setName(StringProperty roomName) {
        this.roomName = roomName;
    }

    public Room_extra getRoom_extra() {
        return room_extra;
    }

    public void setRoom_extra(Room_extra room_extra) {
        this.room_extra = room_extra;
    }

    public Integer getNumberOfSingleBeds() {
        return numberOfSingleBeds.getValue();
    }

    public void setNumberOfSingleBeds(IntegerProperty numberOfSingleBeds) {
        this.numberOfSingleBeds = numberOfSingleBeds;
    }

    public Integer getNumberOfDoubleBeds() {
        return numberOfDoubleBeds.getValue();
    }

    public void setNumberOfDoubleBeds(IntegerProperty numberOfDoubleBeds) {
        this.numberOfDoubleBeds = numberOfDoubleBeds;
    }

    public Integer getNumberOfExtraBeds() {
        return numberOfExtraBeds.getValue();
    }

    public void setNumberOfExtraBeds(IntegerProperty numberOfExtraBeds) {
        this.numberOfExtraBeds = numberOfExtraBeds;
    }

    public Integer getFloorNumber() {
        return floorNumber.getValue();
    }

    public void setFloorNumber(IntegerProperty floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Double getPriceOfRoom() {
        return priceOfRoom.getValue();
    }

    public void setPriceOfRoom(DoubleProperty priceOfRoom) {
        this.priceOfRoom = priceOfRoom;
    }

    public Double getPriceOfAdult() {
        return priceOfAdult.getValue();
    }

    public void setPriceOfAdult(DoubleProperty priceOfAdult) {
        this.priceOfAdult = priceOfAdult;
    }

    public Double getPriceOfMinor() {
        return priceOfMinor.getValue();
    }

    public void setPriceOfMinor(DoubleProperty priceOfMinor) {
        this.priceOfMinor = priceOfMinor;
    }

    public String getSmallDescription() {
        return smallDescription.getValue();
    }

    public void setSmallDescription(StringProperty smallDescription) {
        this.smallDescription = smallDescription;
    }

    public String getBigDescription() {
        return bigDescription.getValue();
    }

    public void setBigDescription(StringProperty bigDescription) {
        this.bigDescription = bigDescription;
    }

    public String getExtraDescription() {
        return extraDescription.getValue();
    }

    public void setExtraDescription(StringProperty extraDescription) {
        this.extraDescription = extraDescription;
    }

    public String getBuilding() {
        return building.getValue();
    }

    public void setBuilding(StringProperty building) {
        this.building = building;
    }



  
}
