/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.controller;

import butler.utils.OperationHistory;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.Model.Database;

/**
 *
 * @author Michał
 */
public class OperationHistoryController implements Initializable {
    @FXML private TableView<OperationHistory> operationTableView;
    @FXML private TableColumn<OperationHistory, String> operation, user, date;
    @FXML private DatePicker datePickerFrom, datePickerTo;
    @FXML private Button showDataButton, printDataButton;
    private Database db;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = MainApp.databaseManager;
        try {
            operationTableView.setItems(db.getOperationHistoryList());
        } catch (SQLException ex) {
            Logger.getLogger(OperationHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        operation.setCellValueFactory(new PropertyValueFactory<>("operation"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        setDatePickerDisplayMethod(datePickerFrom, "yyyy-MM-dd");
        setDatePickerDisplayMethod(datePickerTo, "yyyy-MM-dd");
        showDataButton = new Button();
        printDataButton = new Button();
    } 
    
        @FXML private void showData(ActionEvent event) {
            try {
            operationTableView.setItems(db.getOperationHistoryListFromTo(datePickerFrom.getValue(), datePickerTo.getValue()));
            } catch (SQLException ex) {
                //TODO init catch
            }

    }
        @FXML private void printData(ActionEvent event) {
            //TODO init Printing data from table
    }
        
    private void setDatePickerDisplayMethod(DatePicker datePicker, String displayMethod) {
        datePicker.setConverter(new StringConverter<LocalDate>() {          
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(displayMethod);
        
        @Override 
        public String toString(LocalDate date) {
            if (date != null) {
                return dateFormatter.format(date);
            } else {
                return "";
            }
        }

        @Override 
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                 return LocalDate.parse(string, dateFormatter);
            } else {
                return null;
            }
        }
        });
    }
        
}
