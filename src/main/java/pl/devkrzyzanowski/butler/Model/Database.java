/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Model;

import butler.utils.Booking;
import butler.utils.Client;
import butler.utils.Legend;
import butler.utils.OperationHistory;
import butler.utils.Room;
import butler.sql.SQLcommands;
import butler.utils.AdditionalRoomItems;
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
 * @author Admin
 */
public final class Database {
    private final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    Connection con = null;
    
    private RoomController roomController;
    private ClientController clientController;
    
    public Database() {
        loadDriver();
    }
    
    public boolean loadDriver() {
        boolean success = false;
        try {
            Class.forName(driver);
            success = true;
            System.out.println(driver + " loaded.");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return success;
    }
    
    public boolean connect(String dbURL, String username, String password) {
        boolean success = false;
        try {
            con = DriverManager.getConnection("jdbc:derby:" + dbURL, username, password);
            success = true;
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return success;
    }
    
    public boolean open(String databaseName) {
        boolean success = false;
        try {
            con = DriverManager.getConnection(driver);
            success = true;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return success;
    }
    
    public boolean create(String directory, String databaseName) {
        boolean success = false;
        try {
            con = DriverManager.getConnection("jdbc:derby:" + directory + 
                    databaseName + ";create=true;");
            addStructureToDatabase();
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
    
    public boolean addReadWriteUser(String username, String password) {
        boolean success = false;
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                    "'derby.user." + username + "', '" + password + "')");
            stmt.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
                    "'derby.database.readOnlyAccessUsers', '" + username + "')");
            stmt.close();
        } catch (SQLException e) {
                System.err.println(e);
        }
        return success;
    }
    
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
        public Integer addRoomToDataBase(Room room) {
            return roomController.add(room, con);
    }
        
        public Integer addClientToDataBase(Client client){
            return clientController.add(client, con);
}    
    
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
    
    public void setBookingStatus(Integer bookingId, Integer statusId) {
        try {
            con.createStatement().execute("UPDATE APP.BOOKING SET Legend_idLegend = "+statusId+" WHERE idBooking = "+bookingId+"");

        } catch (SQLException e) {System.out.println(e);}        
    }        
   
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
    
    public void removeBookingById(Integer id) {
        try {
            con.createStatement().execute("DELETE FROM APP.BOOKING WHERE idBooking = "+id+"");
        } catch (SQLException e) {
        }
    }    
    
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
    
    public String getDataBaseName(){
        return "no implement";
    }
    public String getUserName(){
        return "no implement";
    }    
}
