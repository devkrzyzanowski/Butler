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
package pl.devkrzyzanowski.butler.controller;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import pl.devkrzyzanowski.butler.controller.fragments.ToolBarController;

/**
 *
 * @author Michal Krzyzanowski
 */
public class MainPageController implements Initializable {

    @FXML 
    private AnchorPane anchorPane;
    
    private ResourceBundle resources;
    
    @FXML
    private ToolBarController toolBarController;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        toolBarController.setMainPageController(this);
    }
    /**
     * This metod changing a page to new page from path
     * @param path path to .fxml file with new page
     * @return true on success changing content, false on fail
     */
    public boolean setContent(String path) {
        boolean flag = false;
        FXMLLoader lLoader = new FXMLLoader(getClass().getResource("/fxml/pages/" + path + ".fxml"), resources);
            Parent root = null;
        try {
            root = lLoader.load();
            flag = true;
            anchorPane.getChildren().setAll(root);
            AnchorPane.setRightAnchor(root, 0d);
            AnchorPane.setLeftAnchor(root, 0d);
            AnchorPane.setBottomAnchor(root, 0d);
            AnchorPane.setTopAnchor(root, 0d);
        } catch (IOException e) {
            System.err.println("error creating parent root! " + e);
            root = null;
        }
        return flag;
    }
}