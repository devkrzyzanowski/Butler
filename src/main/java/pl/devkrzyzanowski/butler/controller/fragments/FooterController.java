/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.controller.fragments;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.Model.Database;

/**
 *
 * @author Michał
 */
public class FooterController implements Initializable {
    
    @FXML Label nameOfConnectedDB, nameOfUserDB;
    private Database db;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = MainApp.databaseManager;
        nameOfConnectedDB.setText(db.getDataBaseName());
        nameOfUserDB.setText("USERNAME");
    }
    
}
