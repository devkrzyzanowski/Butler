/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.Model;

import butler.utils.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MichalKrzyzanowski
 */
public class RoomController {

    
    public RoomController(){
        
    }
    
    public Integer add(Room room, Connection con) {
        Integer id = null;
        try {
            String sql = "INSERT INTO APP.ROOM(room_name, number_of_single_beds,"
                    + " number_of_double_beds, number_of_extra_beds, floor_number,"
                    + " price_of_room, price_of_adult, price_of_minor, small_description,"
                    + " big_description, extra_description, building, balcon, "
                    + " beach_screen, blanket, sunbed, tv, wi_fi, individual_entrance,"
                    + " friendly_animal, kettle, tableware, table_lamp ) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, room.getRoomName());
            ps.setInt(2, room.getNumberOfSingleBeds());
            ps.setInt(3, room.getNumberOfDoubleBeds());
            ps.setInt(4, room.getNumberOfExtraBeds());
            ps.setInt(5, room.getFloorNumber());
            ps.setDouble(6, room.getPriceOfRoom());
            ps.setDouble(7, room.getPriceOfAdult());
            ps.setDouble(8, room.getPriceOfMinor());
            ps.setString(9, room.getSmallDescription());
            ps.setString(10, room.getBigDescription());
            ps.setString(11, room.getExtraDescription());
            ps.setString(12, room.getBuilding());
            ps.setBoolean(13, room.getRoom_extra().getBalcon().getValue());
            ps.setBoolean(14, room.getRoom_extra().getBeachScreen().getValue());
            ps.setBoolean(15, room.getRoom_extra().getBlanket().getValue());
            ps.setBoolean(16, room.getRoom_extra().getSunbed().getValue());
            ps.setBoolean(17, room.getRoom_extra().getTv().getValue());
            ps.setBoolean(18, room.getRoom_extra().getWiFi().getValue());
            ps.setBoolean(19, room.getRoom_extra().getIndividualEntrance().getValue());
            ps.setBoolean(20, room.getRoom_extra().getFriendlyAnimal().getValue());
            ps.setBoolean(21, room.getRoom_extra().getKettle().getValue());
            ps.setBoolean(22, room.getRoom_extra().getTableware().getValue());
            ps.setBoolean(23, room.getRoom_extra().getTableLamp().getValue());
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating 'Room' failed");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                    room.setId(id);
                } else {
                    throw new SQLException("Creating 'Room' failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    public void removeById(Room room, Connection con) {
        
    }
    
}
