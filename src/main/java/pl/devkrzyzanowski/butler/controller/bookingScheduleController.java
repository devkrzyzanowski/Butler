/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.controller;

import JFXion.IonSchedule;
import butler.utils.Legend;
import butler.utils.LegendCell;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.Model.Database;

/**
 *
 * @author Admin
 */
public class bookingScheduleController implements Initializable {
    @FXML private AnchorPane main;
    @FXML private Pane legendPane;
    @FXML private ScrollPane legendScrollPane;
    @FXML private Button addReservationButton, removeReservationButton, modifyReservationButton;
    @FXML private Button selectClientButton, refreshButton;
    private GridPane legendGridPane;
    
    @FXML private AnchorPane ionScheduleBox;
    private Database db;
    private IonSchedule ionSchedule;
    @FXML ResourceBundle bundle;    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = MainApp.databaseManager;
        legendGridPane = new GridPane();
        legendPane.getChildren().add(legendGridPane);
        int i = 0;
        for (Legend l : db.getLegendList()) {
            legendGridPane.addRow(i, new LegendCell(l.getLegendName(), Color.web(l.getColor())));
            i++;
        }
        ionSchedule = new IonSchedule(db.getRoomList(), db.getBookingList());
        AnchorPane.setBottomAnchor(ionSchedule, 0d);
        AnchorPane.setTopAnchor(ionSchedule, 0d);
        AnchorPane.setLeftAnchor(ionSchedule, 0d);
        AnchorPane.setRightAnchor(ionSchedule, 0d);
        ionScheduleBox.getChildren().add(ionSchedule);
    }
    
    @FXML private void addReservation(ActionEvent event) {
        MainApp.stageManager.addModalStage(((Node) event.getSource()).getScene().getWindow(),
                "/butler/view/dialogs/addReservationDialog.fxml");
        
    }
    
    @FXML private void removeReservation(ActionEvent event) {
    }
    
    @FXML private void modifyReservation(ActionEvent event) {

    }
    
    @FXML private void refreshData(ActionEvent event) {
            refresh();
        }
        
    public void refresh(){
            ionSchedule.update();
}
    

}
