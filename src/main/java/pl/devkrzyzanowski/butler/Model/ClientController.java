/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.Model;

import butler.utils.Client;
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
public class ClientController {
    
    public ClientController() {
        
    }
    
    public Integer add(Client client, Connection con) {
        Integer id = null;
            try {
                String sql = "INSERT INTO APP.CLIENT (first_name,"
                        + " last_name, city, street, home_number, flat_number,"
                        + " zip_code, contact_phone_number, email) VALUES "
                        + "('"+client.getFirstName()+"','"+client.getLastName()
                        +"','"+client.getCity()+"','"+client.getStreet()
                        +"',"+client.getHomeNumber()+","+client.getFlatNumber()
                        +","+client.getZipCode()+","+client.getContactPhoneNumber()
                        +",'"+client.getEmail()+"')";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating 'Client' failed");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                   client.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Creating 'Client' failed, no ID obtained");
                }
            }
            } catch (SQLException e) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
            }
            return id;
    }
}
