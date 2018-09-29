/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.controller.dialogs;

import butler.utils.Booking;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.Model.Database;

/**
 *
 * @author MichalKrzyzanowski
 */
public class BookingScheduleTableViewController implements Initializable {
    @FXML private TableView<Booking> bookingTableView;
    @FXML private TableColumn<Booking, String> beginBookingTableColumn, toBookingTableColumn;
    @FXML private TableColumn<Booking, Integer> roomTableColumn, clientTableColumn;
    private Database db;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = MainApp.databaseManager;
        bookingTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        bookingTableView.getSelectionModel().setCellSelectionEnabled(true);
        bookingTableView.setItems(db.getBookingList());

        beginBookingTableColumn.setCellValueFactory(new PropertyValueFactory<>("beginOfBooking"));
        toBookingTableColumn.setCellValueFactory(new PropertyValueFactory<>("endOfBooking"));
        roomTableColumn.setCellValueFactory(new PropertyValueFactory<>("idRoom"));
        clientTableColumn.setCellValueFactory(new PropertyValueFactory<>("idClient"));
    }
    
//            @FXML private void removeReservation(ActionEvent event) {
//            if (!bookingTableView.getSelectionModel().isEmpty()) {
//                model.removeBookingById(bookingTableView.getSelectionModel().getSelectedItem().getId().getValue());
//            }
//            refresh();
//    }
//            private void refresh(){
//            bookingTableView.setItems(model.getBookingList());
//        }
}
