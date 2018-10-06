/*
 * Copyright (C) 2018 Michal Krzyzanowski
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pl.devkrzyzanowski.butler.controller.fragments;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import pl.devkrzyzanowski.butler.controller.MainPageController;

/**
 *
 * @author Michal Krzyzanowski
 * 
 */
public class TopButtonsBarController {
    
    /** group of buttons */
    @FXML
    ToggleGroup toolBarButtons;

    /** a parent class using this controller to controll content  */
    private MainPageController mainPageController;
    
    
    /** path to booking schedule .fxml file */
    private final String BOOKING_SCHEDULE_PATH = "/fxml/pages/bookingSchedule.fxml";
    /** path to room list .fxml content file */
    private final String ROOM_LIST_PATH = "/fxml/pages/roomList.fxml";
    /** path to client list .fxml content file */
    private final String CLIENT_LIST_PATH = "/fxml/pages/clientList.fxml";
    
    /** set main page controller with the use class
     * @param mainPageController parent class which using this controller
     */       
    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    /**
     * This metod replaces main page content to booking schedule
     * @return true on success or false on fail changing main page content
     */
    @FXML 
    private boolean setPageToBookingSchedule() {
        boolean flag = mainPageController.setContent(BOOKING_SCHEDULE_PATH);
        return flag;
    }
    /**
     * This metod replaces main page content to room list
     * @return flag true on success or false on fail changing main page content
     */  
    @FXML 
    private boolean setPageToRoomList() {
        boolean flag = mainPageController.setContent(ROOM_LIST_PATH); 
        return flag;
    }
    /**
     * This metod replaces main page content client list
     * @return flag true on success or false on fail changing main page content
     */
    @FXML 
    private boolean setPageToClientList() {
        boolean flag = mainPageController.setContent(CLIENT_LIST_PATH);
        return flag;
    }
}
