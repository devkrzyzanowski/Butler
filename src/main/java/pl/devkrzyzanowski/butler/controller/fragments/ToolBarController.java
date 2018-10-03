/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.controller.fragments;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.controller.MainPageController;

/**
 *
 * @author Michał
 */
public class ToolBarController implements Initializable {

//    @FXML ToggleButton bookingScheduleButton, clientBaseButton, listOfRoomsButton,
//            statisticsButton, priceOfRoomsButton, settingsButton,
//            institutionButton, operationHistoryButton, sendBugButton;
//    
    @FXML
    ToggleGroup toolBarButtons;
    
    private ArrayList<ToggleButton> buttons;
    private MainPageController mainPageController;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        buttons = new ArrayList<>();
//        buttons.add(bookingScheduleButton);
//        buttons.add(clientBaseButton);
//        buttons.add(listOfRoomsButton);
//        buttons.add(statisticsButton);
//        buttons.add(priceOfRoomsButton);
//        buttons.add(settingsButton);
//        buttons.add(institutionButton);
//        buttons.add(operationHistoryButton);
//        buttons.add(sendBugButton);
        
//        buttons.stream().map((b) -> {
//            b.setOnMouseEntered((MouseEvent event) -> {
//                b.setStyle("-fx-background-color: #999999;"
//                        + "-fx-background-radius: 0;");
//            });
//            return b;
//        }).forEachOrdered((b) -> {
//            b.setOnMouseExited((MouseEvent event) -> {
//                b.setStyle("-fx-background-color: #555555;"
//                        + "-fx-background-radius: 0;");
//            });
//        });
    }
    
    public void setMainPageController(MainPageController mpc) {
        this.mainPageController = mpc;
    }
 
    @FXML private void setPageToBookingSchedule(ActionEvent event) {
        mainPageController.setContent("bookingSchedule");
    }    
    @FXML private void setPageToRoomList(ActionEvent event) {
        mainPageController.setContent("roomList");        
    }        
    
    @FXML private void setScreenToBookingSchedule(ActionEvent event) {
        setPage(event, "bookingSchedulePage");
    }    
    @FXML private void setScreenToClientBase(ActionEvent event) {
        setPage(event, "clientBasePage");
    }    
    @FXML private void setScreenToListOfRooms(ActionEvent event) {
        setPage(event, "listOfRoomsPage");
    }    
    @FXML private void setScreenToStatistics(ActionEvent event) {
        setPage(event, "statisticsPage");
    }
    @FXML private void setScreenToPriceOfRooms(ActionEvent event) {
        setPage(event, "priceOfRoomsPage");
    }
    @FXML private void setScreenToSettings(ActionEvent event) {
        setPage(event, "statisticsPage");
    }
    @FXML private void setScreenToInstitution(ActionEvent event) {
        setPage(event, "statisticsPage");
    }    
    @FXML private void setScreenToOperationHistory(ActionEvent event) {
        setPage(event, "operationHistoryPage");
    }
    
    private void setPage(ActionEvent event, String path) {
        MainApp.stageManager.changeStage((Stage) ((Node) event.getSource()).getScene().getWindow(), "/fxml/"+path+".fxml");
    }  
}
