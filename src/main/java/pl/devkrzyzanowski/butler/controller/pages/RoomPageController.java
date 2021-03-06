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
package pl.devkrzyzanowski.butler.controller.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import pl.devkrzyzanowski.butler.MainApp;

/**
 *
 * @author Michal Krzyzanowski
 */
public class RoomPageController {
    
    
    
            @FXML private void addRoomAction(ActionEvent event) {
                        MainApp.stageManager.addModalStage(((Node) event.getSource()).getScene().getWindow(),
                "/fxml/dialogs/addRoomDialog.fxml");
                
            }
            @FXML private void modifyRoomAction(ActionEvent event) {
                
            }
}

