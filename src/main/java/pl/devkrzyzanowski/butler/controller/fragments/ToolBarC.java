/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.controller.fragments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import pl.devkrzyzanowski.butler.controller.MainPageController;

/**
 *
 * @author Admin
 */
public class ToolBarC {
    
    @FXML
    ToggleGroup toolBarButtons;
    
    private MainPageController mainPageController;    

    public void setMainPageController(MainPageController mpc) {
        this.mainPageController = mpc;
    }
        
    @FXML 
    private void setPageToBookingSchedule() {
        mainPageController.setContent("bookingSchedule");
    }    
    @FXML 
    private void setPageToRoomList() {
        mainPageController.setContent("roomList");        
    }           
    @FXML 
    private void setPageToClientList() {
        mainPageController.setContent("clientList");
    }
        
        
}
