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
import pl.devkrzyzanowski.butler.controller.fragments.TopButtonsBarController;

/**
 *
 * @author Michal Krzyzanowski
 */
public class MainPageController implements Initializable {

    /** main and center content on every page */
    @FXML 
    private AnchorPane anchorPane;
    
    /** resources to internationalization */
    private ResourceBundle resources;
    
    /** controller used to operate the top bar buttons */
    @FXML
    private TopButtonsBarController topButtonsBarController;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        topButtonsBarController = new TopButtonsBarController(this);
    }
    /**
     * This metod changing a page to new page from path
     * @param path to .fxml path to file with new page
     * @return true on success changing content, false on fail
     */
    public boolean setContent(String path) {
        boolean flag = false;
        FXMLLoader lLoader = new FXMLLoader(getClass().getResource(path), resources);
            Parent root;
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
        }
        return flag;
    }
}