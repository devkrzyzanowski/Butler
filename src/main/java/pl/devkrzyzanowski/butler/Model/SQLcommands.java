/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.sql;

/**
 *
 * @author MichalKrzyzanowski
 */
public class SQLcommands {
    
    public static final String CREATE_DBUSER = "";
    
    public static final String CREATE_BOOKING = "CREATE TABLE APP.BOOKING\n" +
"(\n" +
"    idBooking INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,\n" +
"    begin_of_booking TIMESTAMP,\n" +
"    end_of_booking TIMESTAMP,\n" +
"    Client_idClient INT,\n" +
"    Room_idRoom INT,\n" +
"    Legend_idLegend INT,\n" +            
"    \n" +
"    FOREIGN KEY (Client_idClient) REFERENCES APP.CLIENT(idClient),\n" +
"    FOREIGN KEY (Room_idRoom) REFERENCES APP.ROOM(idRoom),\n" +
"    FOREIGN KEY (Legend_idLegend) REFERENCES APP.LEGEND(idLegend)\n" +
")";
    
    
    public static final String CREATE_CLIENT = "CREATE TABLE APP.CLIENT\n" +
"(\n" +
"    idClient INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,\n" +
"    first_name VARCHAR(45),\n" +
"    last_name VARCHAR(45),\n" +
"    city VARCHAR(45),\n" +
"    street VARCHAR(45),\n" +
"    home_number INT,\n" +
"    flat_number INT,\n" +
"    zip_code INT,\n" +
"    contact_phone_number INT,\n" +
"    email VARCHAR(45)\n" +
")";
    
    
    public static final String CREATE_LEGEND = "CREATE TABLE APP.LEGEND\n" +
"(\n" +
"    idLegend INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,\n" +
"    legend_name VARCHAR(45),\n" +
"    color VARCHAR(45)\n" +
")";
    
    
    public static final String CREATE_OPERATION = "CREATE TABLE APP.OPERATION\n" +
"(\n" +
"    idOperation INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
"    DBUser_idDBUser INT,\n" +
"    operation VARCHAR(45),\n" +
"    date TIMESTAMP,\n" +
"    PRIMARY KEY (idOperation),\n" +
"    FOREIGN KEY (DBUser_idDBUser) REFERENCES APP.DBUSER(idDBUser)\n" +
")";
    
    
    public static final String CREATE_ROOM = "CREATE TABLE APP.ROOM\n" +
"(\n" +
"    idRoom INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,\n" +
"    room_name VARCHAR(45),\n" +
"    number_of_single_beds INT,\n" +
"    number_of_double_beds INT,\n" +
"    number_of_extra_beds INT,\n" +
"    floor_number INT,\n" +
"    price_of_room DOUBLE,\n" +
"    price_of_adult DOUBLE,\n" +
"    price_of_minor DOUBLE,\n" +
"    small_description VARCHAR(45),\n" +
"    big_description VARCHAR(255),\n" +
"    extra_description VARCHAR(45),\n" +
"    building VARCHAR(45),\n" +
"    balcon BOOLEAN,\n" +
"    beach_screen BOOLEAN,\n" +
"    blanket BOOLEAN,\n" +
"    sunbed BOOLEAN,\n" +
"    tv BOOLEAN,\n" +
"    wi_fi BOOLEAN,\n" +
"    individual_entrance BOOLEAN,\n" +
"    friendly_animal BOOLEAN,\n" +
"    kettle BOOLEAN,\n" +
"    tableware BOOLEAN,\n" +
"    table_lamp BOOLEAN\n" +
")";
    
    public static final String INSERT_LEGEND = "INSERT INTO APP.LEGEND (legend_name, color) VALUES ('undefined', '#ffffff'),\n" +
"('pre_booking', '#00ff00'),\n" +
"('pre_booking', '#ffff00'),\n" +
"('pre_booking', '#00ffff'),\n" +
"('pre_booking', '#ff00ff'),\n" +
"('pre_booking', '#ff0000')";

    public SQLcommands() {}
}
