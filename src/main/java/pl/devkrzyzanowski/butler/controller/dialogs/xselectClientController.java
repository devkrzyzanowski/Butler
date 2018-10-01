/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.controller.dialogs;

import butler.utils.Client;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.Model.Database;
import pl.devkrzyzanowski.butler.controller.OperationHistoryController;

/**
 *
 * @author MichalKrzyzanowski
 */
public class xselectClientController extends DialogBox implements Initializable {
    @FXML private TableView<Client> clientTableView;
    @FXML private TableColumn<Client, String> firstName, lastName, city, street,
            email;
    @FXML private TableColumn<Client, Integer> homeNumber, flatNumber, contactPhoneNumber, zipCode;
    @FXML private Button selectClientButton;
    private Database db;
    
    
    private Consumer<Client> clientSelectCallback;
    
    public void setClientSelectCallback(Consumer<Client> callback) {
        this.clientSelectCallback = callback;
    }
    
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
    
    public void close(){
        Stage stage = (Stage) selectClientButton.getScene().getWindow();
        stage.close();        
    }
    
    @FXML private void selectClient(ActionEvent event) {
        Stage stage = (Stage) selectClientButton.getScene().getWindow();
        stage.close();
    }
    
    public Button getSelectedButton () {
        return selectClientButton;
    }

    String getSelected() {
        if (!clientTableView.getSelectionModel().isEmpty()) {
            return clientTableView.getSelectionModel().getSelectedItem().getFirstName();
        } else {
            return "BRAK";
        }
    }

    TableView<Client> getTable() {
        return clientTableView;
    }

}
