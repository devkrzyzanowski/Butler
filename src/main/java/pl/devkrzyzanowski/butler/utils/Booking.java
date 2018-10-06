/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.utils;

import java.sql.Timestamp;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Michał
 */
public class Booking {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty beginOfBooking = new SimpleStringProperty();
    private StringProperty endOfBooking = new SimpleStringProperty();
    private IntegerProperty idClient = new SimpleIntegerProperty();
    private IntegerProperty idRoom = new SimpleIntegerProperty();
    private IntegerProperty bookingDays = new SimpleIntegerProperty();
    private IntegerProperty idLegend = new SimpleIntegerProperty();
    
    public Booking(Integer id, String beginOfBooking, String endOfBooking, Integer clientId, Integer roomId, Integer idLegend){
        this.id.set(id);
        this.beginOfBooking.set(beginOfBooking);
        this.endOfBooking.set(endOfBooking);
        this.idClient.set(clientId);
        this.idRoom.set(roomId);
        this.idLegend.set(idLegend);
        Timestamp begin = Timestamp.valueOf(beginOfBooking);
        Timestamp end = Timestamp.valueOf(endOfBooking);        
        this.bookingDays.set(Math.toIntExact(Long.valueOf((end.getTime() - begin.getTime()) / 86400000l)));
    }
    
    public Integer getIdLegend() {
        return idLegend.getValue();
    }
    
    public void setIdLegend(Integer idLegend) {
        this.idLegend.setValue(idLegend);
    }

    public Integer getBookingDays() {
        return bookingDays.getValue();
    }

    public void setBookingDays(Integer bookingDays) {
        this.bookingDays.setValue(bookingDays);
    }

    @Override
    public String toString(){
        return "Id rezerwacji : " + id.getValue().toString();
    }
    
    public IntegerProperty getId() {
        return id;
    }

    public void setId(IntegerProperty id) {
        this.id = id;
    }

    public String getBeginOfBooking() {
        return beginOfBooking.getValue();
    }

    public void setBeginOfBooking(StringProperty beginOfBooking) {
        this.beginOfBooking = beginOfBooking;
    }

    public String getEndOfBooking() {
        return endOfBooking.getValue();
    }

    public void setEndOfBooking(StringProperty endOfBooking) {
        this.endOfBooking = endOfBooking;
    }

    public Integer getIdClient() {
        return idClient.getValue();
    }

    public void setIdClient(IntegerProperty idClient) {
        this.idClient = idClient;
    }

    public Integer getIdRoom() {
        return idRoom.getValue();
    }

    public void setIdRoom(IntegerProperty idRoom) {
        this.idRoom = idRoom;
    }

    public void setClientId(IntegerProperty clientId) {
        this.idClient = clientId;
    }

    public IntegerProperty getRoomId() {
        return idRoom;
    }

    public void setRoomId(IntegerProperty roomId) {
        this.idRoom = roomId;
    }
}
