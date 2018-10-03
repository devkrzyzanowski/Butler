/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import pl.devkrzyzanowski.butler.controller.fragments.ToolBarC;

/**
 *
 * @author Admin
 */
public class MainPageController implements Initializable {

    @FXML 
    private AnchorPane anchorPane;
    
    private ResourceBundle resources;
    
    @FXML
    private ToolBarC toolBarController;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        toolBarController.setMainPageController(this);
    }
    
    public void setContent(String path) {
        FXMLLoader lLoader = new FXMLLoader(getClass().getResource("/fxml/pages/" + path + ".fxml"), resources);
            Parent root = null;
        try {
            root = lLoader.load();
        } catch (IOException e) {
            System.err.println("error creating parent root! " + e);
            root = null;
        }
        anchorPane.getChildren().setAll(root);
        AnchorPane.setRightAnchor(root, 0d);
        AnchorPane.setLeftAnchor(root, 0d);
        AnchorPane.setBottomAnchor(root, 0d);
        AnchorPane.setTopAnchor(root, 0d);
    }
}