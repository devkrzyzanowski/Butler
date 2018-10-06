/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.controller;

import butler.utils.Client;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.Model.Database;

/**
 *
 * @author Michał
 */
public class ClientBaseController implements Initializable {
    @FXML private TableView<Client> clientTableView;
    @FXML private TableColumn<Client, String> firstName, lastName, city, street,
            email;
    @FXML private TableColumn<Client, Integer> homeNumber, flatNumber, contactPhoneNumber, zipCode;
    private Database db;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = MainApp.databaseManager;
        try {
            clientTableView.setItems(db.getClientList());
        } catch (SQLException ex) {
            Logger.getLogger(OperationHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        street.setCellValueFactory(new PropertyValueFactory<>("street"));
        homeNumber.setCellValueFactory(new PropertyValueFactory<>("homeNumber"));
        flatNumber.setCellValueFactory(new PropertyValueFactory<>("flatNumber"));
        zipCode.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        contactPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("contactPhoneNumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
    
        @FXML private void openAddClientDialog(ActionEvent event) throws IOException{
        MainApp.stageManager.changeStage((Stage) ((Node) event.getSource())
                .getScene().getWindow(), "/butler/view/dialogs/addClientDialog.fxml");
    }
        
}
