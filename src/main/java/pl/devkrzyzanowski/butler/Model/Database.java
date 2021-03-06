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
package pl.devkrzyzanowski.butler.Model;

import butler.sql.SQLcommands;
import butler.utils.AdditionalRoomItems;
import butler.utils.Booking;
import butler.utils.Client;
import butler.utils.Legend;
import butler.utils.OperationHistory;
import butler.utils.Room;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Michal Krzyzanowski
 */
public final class Database {
    /** string path to name of DRIVER begin used */
    private final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    /** */
    private final String DRIVER_PREFIX = "jdbc:derby:";
    /** connection */
    Connection con = null;
    
    private RoomController roomController;
    private ClientController clientController;
    
    /**
     *
     */
    public Database() {
        roomController = new RoomController();
        clientController = new ClientController();
        loadDriver();
    }
    
    /**
     * method for load defined driver = {@value #DRIVER}
     * @return true on success false on fail
     */
    public boolean loadDriver() {
        boolean success = false;
        try {
            Class.forName(DRIVER);
            success = true;
            System.out.println(DRIVER + " loaded.");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return success;
    }
    
    /**
     * connecting to a database of a specific user
     * @param dbURL url to database
     * @param username username to connect to database
     * @param password password to connect to datamase
     * @return
     */
    public boolean connect(String dbURL, String username, String password) {
        boolean success = false;
        try {
            con = DriverManager.getConnection(DRIVER_PREFIX + dbURL, username, password);
            success = true;
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return success;
    }
    
    /**
     *
     * @param databaseName
     * @return
     */
    public boolean open(String databaseName) {
        boolean success = false;
        try {
            con = DriverManager.getConnection(DRIVER);
            success = true;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return success;
    }
    
    /**
     *
     * @param directory directory path for new database
     * @param databaseName name of the new database
     * @return true on success, false on fail
     */
    public boolean create(String directory, String databaseName) {
        boolean success = false;
        try {
            con = DriverManager.getConnection("jdbc:derby:" + directory + 
                    databaseName + ";create=true;");
            addStructureToDatabase();
            success = true;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return success;
    }
    
    private boolean addStructureToDatabase() {      
        boolean success = false;
        try {
            Statement stmt = con.createStatement();
            stmt.execute(SQLcommands.CREATE_CLIENT);
            stmt.close();
          
            stmt = con.createStatement();
            stmt.execute(SQLcommands.CREATE_ROOM);
            stmt.close();

            stmt = con.createStatement();
            stmt.execute(SQLcommands.CREATE_LEGEND);
            stmt.close();
          
            stmt = con.createStatement();
            stmt.execute(SQLcommands.CREATE_BOOKING);
            stmt.close();
          
            stmt = con.createStatement();
            stmt.executeUpdate(SQLcommands.INSERT_LEGEND);
            stmt.close();  
            success = true;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return success;
    }
    
    /**
     *
     * @param username
     * @param password
     * @return
     */
    public boolean addReadWriteUser(String username, String password) {
        boolean success = false;
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                    "'derby.user." + username + "', '" + password + "')");
            stmt.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                    "'derby.database.fullAccessUsers', '" + username + "')");
            stmt.close();
        } catch (SQLException e) {
                System.err.println(e);
        }
        return success;
    }
    
    /**
     *
     * @return
     */
    public boolean initAuthentication() {
        boolean success = false;
        try {
        Statement stmt = con.createStatement();
        // Setting and Confirming requireAuthentication
        stmt.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.connection.requireAuthentication', 'true')");
        ResultSet rs = stmt.executeQuery(
            "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
            "'derby.connection.requireAuthentication')");
        rs.next();
        System.out.println("Value of requireAuthentication is " + rs.getString(1));
        // Setting authentication scheme to Derby
        stmt.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.authentication.provider', 'BUILTIN')");        
        } catch (SQLException e) {
            System.err.println(e);
        }
        return success;
    }
    
    /**
     *
     * @return
     */
    public boolean close() {
        boolean success = false;
        try {
            con.close();
            success = true;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return success;
    }
    
    /**
     *
     * @param dbName
     * @param username
     * @param password
     * @return
     */
    public boolean login(String dbName, String username, String password) {
        boolean success = false;
        try {
            con = DriverManager.getConnection("jdbc:derby:" + dbName + ";", username, password);
            success = true;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return success;
    }
    
    /**
     *
     * @param value
     * @param value0
     * @param selectedClientId
     * @param selectedRoomId
     * @param selectedLegendId
     * @return
     */
    public boolean addBookingToDataBase(Timestamp value, Timestamp value0, Integer selectedClientId, Integer selectedRoomId, Integer selectedLegendId) {
        try {
            System.out.println(value);
            con.createStatement().execute("INSERT INTO APP.BOOKING("
                    + "begin_of_booking, end_of_booking, Client_idClient,"
                    + " Room_idRoom, Legend_idLegend) VALUES ("
                    + "'"+value+"', '"+value0+"', "+selectedClientId+","
                                + " "+selectedRoomId+", "+selectedLegendId+")");
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     *
     * @return 
     */
    public ObservableList<Booking> getBookingList() {
        ObservableList<Booking> list = FXCollections.observableArrayList();
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.BOOKING");
            while (rs.next()){
            Integer id = rs.getInt("idBooking");
            Timestamp beginOfBooking = rs.getTimestamp("begin_of_booking");
            Timestamp endOfBooking = rs.getTimestamp("end_of_booking");
            Integer clientId = rs.getInt("Client_idClient");
            Integer roomId = rs.getInt("Room_idRoom");
            Integer legendId = rs.getInt("Legend_idLegend");
            System.out.println(id + " id||" + beginOfBooking.toString() + 
                    " begin||" + endOfBooking.toString() + " end||" + 
                    clientId + " client||" + roomId + " room||" + 
                    legendId + " legend||" );
            list.add(new Booking(id, String.valueOf(beginOfBooking),
                    String.valueOf(endOfBooking), clientId, roomId, legendId));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
 
    /**
     * 
     * @param message
     * @param user
     * @return true on successfully add or false on fail
     * @throws java.sql.SQLException
     */
    public boolean addToOperationHistory(String message, String user) throws SQLException {
        try {
            con.createStatement().execute("INSERT INTO APP.OPERATION (operation, date, dbUser_idDbUser) VALUES ('"+message+"', CURRENT_TIMESTAMP, 0)");
            return true;
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    /**
     *
     * @param room
     * @return
     */
    public Integer addRoomToDataBase(Room room) {
            return roomController.add(room, con);
    }
        
    /**
     *
     * @param client
     * @return
     */
    public Integer addClientToDataBase(Client client){
            return clientController.add(client, con);
}    
    
    /**
     *
     * @param idLegend
     * @return
     */
    public Legend getLegendById(Integer idLegend) {
        Legend legend = null;
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.LEGEND WHERE idLegend = "+idLegend+" ");
            while (rs.next()) {                
                Integer id = rs.getInt("idLegend");
                String legendName = rs.getString("legend_name");
                String color = rs.getString("color");
                legend = new Legend(id, legendName, color);
            }
        } catch (SQLException e) {System.out.println(e);}
        return legend;       
    }  
    
    /**
     *
     * @param bookingId
     * @param statusId
     */
    public void setBookingStatus(Integer bookingId, Integer statusId) {
        try {
            con.createStatement().execute("UPDATE APP.BOOKING SET Legend_idLegend = " + statusId + " WHERE idBooking = " + bookingId + " ");
        } catch (SQLException e) {
            System.out.println(e);
        }        
    }        
   
    /**
     *
     * @return
     */
    public ObservableList<Legend> getLegendList() {
        ObservableList<Legend> legends = FXCollections.observableArrayList();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.LEGEND");
            while (rs.next()) {                
                Integer id = rs.getInt("idLegend");
                String legendName = rs.getString("legend_name");
                String color = rs.getString("color");
                legends.add(new Legend(id, legendName, color));
            }
        } catch (SQLException e) {System.out.println(e);}
        return legends;
    }    
    
    /**
     *
     * @param idClient
     * @return
     */
    public Client getClientById(Integer idClient) {
            Client returnedClient = null;
       try (Statement stmt = con.createStatement()) {
           ResultSet rs = stmt.executeQuery("SELECT * FROM APP.CLIENT WHERE idClient = "+idClient+" ");
            while (rs.next()) {                
                Integer id = rs.getInt("idClient");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String city = rs.getString("city");
                String street = rs.getString("street");
                Integer homeNumber = rs.getInt("home_number");
                Integer flatNumber = rs.getInt("flat_number");
                Integer zipCode = rs.getInt("zip_code");
                Integer contactPhoneNumber = rs.getInt("contact_phone_number");
                String email = rs.getString("email");
                returnedClient = new Client(id, firstName, lastName, city, street,
                        homeNumber, flatNumber, zipCode, contactPhoneNumber,
                        email);
            }
       } catch (SQLException e){
           System.out.println(e);
       }
       return returnedClient;
    }    

    /**
     *
     * @return
     * @throws SQLException
     */
    public ObservableList<Client> getClientList() throws SQLException {
        ObservableList<Client> list = FXCollections.observableArrayList();
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.CLIENT");
            while (rs.next()) {                
                Integer id = rs.getInt("idClient");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String city = rs.getString("city");
                String street = rs.getString("street");
                Integer homeNumber = rs.getInt("home_number");
                Integer flatNumber = rs.getInt("flat_number");
                Integer zipCode = rs.getInt("zip_code");
                Integer contactPhoneNumber = rs.getInt("contact_phone_number");
                String email = rs.getString("email");
                list.add(new Client(id, firstName, lastName, city, street,
                        homeNumber, flatNumber, zipCode, contactPhoneNumber,
                        email));
            }
        }
        list.forEach((c) -> {
            System.out.println(c.getId() + "|" + c.getFirstName());
        });
        return list;        
    }    
    
    /**
     *
     * @param id
     */
    public void removeBookingById(Integer id) {
        try {
            con.createStatement().execute("DELETE FROM APP.BOOKING WHERE idBooking = "+id+"");
        } catch (SQLException e) {
        }
    }    
    
    /**
     *
     * @param roomId
     * @return
     * @throws SQLException
     */
    public Room getRoomById(Integer roomId) throws SQLException {
            Room returnedRoom = null;
       try (Statement stmt = con.createStatement()) {
           ResultSet rs = stmt.executeQuery("SELECT * FROM APP.ROOM WHERE idRoom = "+roomId+" ");
            while (rs.next()) {               
                Integer id = rs.getInt("idRoom");
                System.out.println("DD" + id);
                String roomName = rs.getString("room_name");
                Integer numberOfSingleBeds = rs.getInt("number_of_single_beds");
                Integer numberOfDoubleBeds = rs.getInt("number_of_double_beds");
                Integer numberOfExtraBeds = rs.getInt("number_of_extra_beds");
                Integer floorNumber = rs.getInt("floor_number");
                Double priceOfRoom = rs.getDouble("price_of_room");
                Double priceOfAdult = rs.getDouble("price_of_adult");
                Double priceOfMinor = rs.getDouble("price_of_minor");
                String smallDescription = rs.getString("small_description");
                String bigDescription = rs.getString("big_description");
                String extraDescription = rs.getString("extra_description");
                String building = rs.getString("building");
                Boolean balcon = rs.getBoolean("balcon");
                Boolean beachScreen = rs.getBoolean("beach_screen");
                Boolean blanket = rs.getBoolean("blanket");
                Boolean sunbed = rs.getBoolean("sunbed");
                Boolean tv = rs.getBoolean("tv");
                Boolean wiFi = rs.getBoolean("wi_fi");
                Boolean individualEntrance = rs.getBoolean("individual_entrance");
                Boolean friendlyAnimal = rs.getBoolean("friendly_animal");
                Boolean kettle = rs.getBoolean("kettle");
                Boolean tableware = rs.getBoolean("tableware");
                Boolean tableLamp = rs.getBoolean("table_lamp");
                AdditionalRoomItems e = new AdditionalRoomItems();
                returnedRoom = new Room(id, roomName, numberOfSingleBeds, numberOfDoubleBeds,
                        numberOfExtraBeds, floorNumber, priceOfRoom, priceOfAdult,
                        priceOfMinor, smallDescription, bigDescription, extraDescription,
                        building, balcon, beachScreen, blanket, sunbed, tv, wiFi,
                        individualEntrance, friendlyAnimal, kettle, tableware, tableLamp);
            }
       } catch (SQLException e){
           System.out.println(e);
       }
       return returnedRoom;
    }

    /**
     *
     * @return
     */
    public ObservableList<Room> getRoomList() {
        ObservableList<Room> list = FXCollections.observableArrayList();
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM APP.ROOM");
            while (rs.next()) {    
                Integer id = rs.getInt(("idRoom"));
                String roomName = rs.getString("room_name");
                Integer numberOfSingleBeds = rs.getInt("number_of_single_beds");
                Integer numberOfDoubleBeds = rs.getInt("number_of_double_beds");
                Integer numberOfExtraBeds = rs.getInt("number_of_extra_beds");
                Integer floorNumber = rs.getInt("floor_number");
                Double priceOfRoom = rs.getDouble("price_of_room");
                Double priceOfAdult = rs.getDouble("price_of_adult");
                Double priceOfMinor = rs.getDouble("price_of_minor");
                String smallDescription = rs.getString("small_description");
                String bigDescription = rs.getString("big_description");
                String extraDescription = rs.getString("extra_description");
                String building = rs.getString("building");
                Boolean balcon = rs.getBoolean("balcon");
                Boolean beachScreen = rs.getBoolean("beach_screen");
                Boolean blanket = rs.getBoolean("blanket");
                Boolean sunbed = rs.getBoolean("sunbed");
                Boolean tv = rs.getBoolean("tv");
                Boolean wiFi = rs.getBoolean("wi_fi");
                Boolean individualEntrance = rs.getBoolean("individual_entrance");
                Boolean friendlyAnimal = rs.getBoolean("friendly_animal");
                Boolean kettle = rs.getBoolean("kettle");
                Boolean tableware = rs.getBoolean("tableware");
                Boolean tableLamp = rs.getBoolean("table_lamp");
                AdditionalRoomItems e = new AdditionalRoomItems();
                list.add(new Room(id, roomName, numberOfSingleBeds, numberOfDoubleBeds,
                        numberOfExtraBeds, floorNumber, priceOfRoom, priceOfAdult,
                        priceOfMinor, smallDescription, bigDescription, extraDescription,
                        building, balcon, beachScreen, blanket, sunbed, tv, wiFi,
                        individualEntrance, friendlyAnimal, kettle, tableware, tableLamp));
            }
        } catch (SQLException e){
            System.out.println(e); 
        }
        return list;
    }

    private boolean timeBeetwen(Timestamp time, Timestamp after, Timestamp before) {
        return (time.after(after) && time.before(before));
    }

    /**
     *
     * @param idRoom
     * @param ts
     * @return
     */
    public String roomOwnedBy(Integer idRoom, Timestamp ts) {
        String s = "";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT begin_of_booking, end_of_booking, Client_idClient FROM APP.BOOKING WHERE Room_idRoom = " + idRoom + "");
        while (rs.next()) {
            Timestamp beginOfBooking = rs.getTimestamp("begin_of_booking");
            Timestamp endOfBooking = rs.getTimestamp("end_of_booking");
            Integer idClient = rs.getInt("Client_idClient");
            System.out.println(idClient);
            if (timeBeetwen(ts, beginOfBooking, endOfBooking)) {
                try (Statement stmt2 = con.createStatement()) {
                    ResultSet rs2 = stmt2.executeQuery("SELECT first_name, last_name FROM APP.CLIENT WHERE idClient = " + idClient + "");
                    while (rs2.next()) {
                        System.out.println("w");
                        s = rs2.getString("first_name") + " " + rs2.getString("last_name");
                    }
                } catch (SQLException e) {System.out.println(e);}
            }
        }
        } catch (SQLException e){
            System.out.println(e);
        }
        return s;
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public ObservableList<OperationHistory> getOperationHistoryList() throws SQLException {
        ObservableList<OperationHistory> list = FXCollections.observableArrayList();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT operation, date, nick FROM APP.OPERATION o INNER JOIN APP.DBUSER u ON u.idDBUser = o.DBUser_idDBUser");
            while (rs.next()){
                String operation = rs.getString("operation");
                String date = rs.getString("date");
                String user = rs.getString("nick");
                
                list.add(new OperationHistory(operation, date, user));
            }
        }
        return list;
    }

    /**
     * 
     * @param dateFrom
     * @param dateTo
     * @return 
     * @throws java.sql.SQLException 
     */
     public ObservableList<OperationHistory> getOperationHistoryListFromTo(LocalDate dateFrom, LocalDate dateTo) throws SQLException {
        ObservableList<OperationHistory> list = FXCollections.observableArrayList();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT operation, date,"
                    + " nick FROM APP.OPERATION o INNER JOIN APP.DBUSER u ON u.idDBUser = o.DBUser_idDBUser WHERE date BETWEEN TIMESTAMP('"+dateFrom+" 00:00:00') AND TIMESTAMP('"+dateTo+" 23:59:59')");
            while (rs.next()){
                String nick = rs.getString("nick");
                String operation = rs.getString("operation");
                String date = rs.getString("date");
                list.add(new OperationHistory(operation, date, nick));
            }
        }
        return list;
    }   
    /**
     * registerUser
     * @param login login for new user (must be unique)
     * @param password password for new user (must have more than 8 letters or
     * numbers, and the first character must be a letter)
     * @param password2 must be this same to parameter password
     * @param email email for new user (must be unique and have email rules)
     * @param regulationsAccepted regulations must be accepted
     * @return true if successfully create account or false if fail
     */
    public boolean registerUser(String login, String password, String password2, String email, boolean regulationsAccepted){
        //TODO create
        return false;
    }
    
    /**
     *
     * @return
     */
    public String getDataBaseName(){
        return "no implement";
    }

    /**
     *
     * @return
     */
    public String getUserName(){
        return "no implement";
    }    
}
