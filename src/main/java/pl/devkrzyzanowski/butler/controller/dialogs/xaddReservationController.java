/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.controller.dialogs;

import butler.utils.Client;
import butler.utils.Room;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.Model.Database;

/**
 *
 * @author Michał
 */
public class xaddReservationController extends DialogBox implements Initializable {
    
    @FXML TextField selectClientTextField, selectRoomTextField;
    @FXML DatePicker fromDatePicker, toDatePicker;
    @FXML Button addReservationButton;
    @FXML TextField nightsNumberTextField;
    private Client selectedClient;
    private Room selectedRoom;
    private Database db;
   
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = MainApp.databaseManager;
    }
    
    private void sendReservationToDataBase(){
        Timestamp ts = Timestamp.valueOf(fromDatePicker.getValue().atStartOfDay());
        Timestamp ts2 = Timestamp.valueOf(toDatePicker.getValue().atStartOfDay());
        
        db.addBookingToDataBase(ts, ts2, selectedClient.getId(), selectedRoom.getId(), 1);
    }
    
    @FXML private void addReservation(ActionEvent event){
        sendReservationToDataBase();
        close();
    }
    
    @FXML private void openSelectClientDialog(ActionEvent event) throws IOException{
        Stage stage = new Stage();            
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/fxml/dialogs/selectClientDialog.fxml"));
        Parent parent = (Parent) fXMLLoader.load();
        xselectClientController sCC = fXMLLoader.getController();
        sCC.getSelectedButton().setOnAction(e -> {
            selectedClient = sCC.getTable().getSelectionModel().getSelectedItem();
            selectClientTextField.setText(selectedClient.getFirstName() + " " + selectedClient.getLastName());
            sCC.close();
        });
        
        Scene scene = new Scene(parent);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    @FXML private void openAddNewClientInReservation(ActionEvent event) throws IOException{
        Stage stage = new Stage();            
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/fxml/dialogs/addClientDialog.fxml"));
        Parent parent = (Parent) fXMLLoader.load();
        xaddClientController aCC = fXMLLoader.getController();
        aCC.getAddButton().setOnAction(e -> {
            selectedClient = aCC.createClient();
            selectClientTextField.setText(selectedClient.getFirstName() + " " + selectedClient.getLastName());
            aCC.close();
        });
        
        Scene scene = new Scene(parent);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();        
    }
    
    @FXML private void openSelectRoomDialog(ActionEvent event) throws IOException{
        Stage stage = new Stage();            
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/fxml/dialogs/selectRoomDialog.fxml"));
        Parent parent = (Parent) fXMLLoader.load();
        xselectRoomController sCC = fXMLLoader.getController();
        sCC.getSelectedButton().setOnAction(e -> {
            selectedRoom = sCC.getTable().getSelectionModel().getSelectedItem();
            selectRoomTextField.setText(selectedRoom.getRoomName());
            sCC.close();
        });
        
        Scene scene = new Scene(parent);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    
        public void close(){
        Stage stage = (Stage) addReservationButton.getScene().getWindow();
        stage.close();        
    }
}
