/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.controller.dialogs;

import JFXion.IonSchedule;
import butler.utils.Booking;
import butler.utils.Legend;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.Model.Database;

/**
 *
 * @author MichalKrzyzanowski
 */
public class ModifyBookingController extends DialogBox implements Initializable {
    private Booking booking;
    @FXML private Label label;
    @FXML ComboBox<Legend> selectStatusComboBox;
    private IonSchedule ion;
    private Database db;
    
    public ModifyBookingController () {
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = MainApp.databaseManager;
        selectStatusComboBox.setItems(db.getLegendList());
    }

    public void init(Booking booking, IonSchedule ion) {
        this.ion = ion;
        this.booking = booking;
        label.setText(String.valueOf(booking.getBookingDays()));
    }
    
    @FXML private void edit(ActionEvent event) {
        Integer legendId = selectStatusComboBox.getSelectionModel().getSelectedItem().getIdLegend();
        if (legendId == null) legendId = 13;
        db.setBookingStatus(booking.getId().getValue(), legendId);
        updateAndClose((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
    
    @FXML private void removeReservation(ActionEvent event) {
        db.removeBookingById(booking.getId().getValue());
        updateAndClose((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
    
    private void updateAndClose(Stage stage){
        ion.update();
        stage.close();        
    }
}
