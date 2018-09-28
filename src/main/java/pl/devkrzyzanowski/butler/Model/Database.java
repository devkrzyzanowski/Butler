/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public final class Database {
    private final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    Connection con = null;
    
    public Database() {
        loadDriver();
    }
    
    public boolean loadDriver() {
        boolean success = false;
        try {
            Class.forName(driver);
            System.out.println(driver + " loaded.");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
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
            
        } catch (SQLException e) {
            System.err.println(e);
        }
        return success;
    }
    
}
