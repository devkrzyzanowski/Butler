/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package butler.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Michał
 */
public class FXMLDocumentController implements Initializable {
    @FXML private Button exitButton;
    @FXML private Button minimizeButton;
   
    private double xOffset = 0;
    private double yOffset = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML private void handleExitButton() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    @FXML private void handleMinimizeButton(ActionEvent event) {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML private void onMouseDragging(MouseEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }
    @FXML private void onMousePressed(MouseEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }
    @FXML private void setScreenToRegisterPage(ActionEvent event) throws IOException{
    setPage(event, "RegisterPage");
    }
    @FXML private void setScreenToLoginPage(ActionEvent event) throws IOException{
    setPage(event, "LoginPage");
    }    
    @FXML private void handleLoginButton(ActionEvent event) throws IOException {
    setPage(event, "ClientBasePage");     
        //TODO init
    }
    @FXML private void handleRegisterButton(ActionEvent event) throws IOException {
        //TODO init
    }
        @FXML private void setScreenBookingSchedule(ActionEvent event) throws IOException{
        setPage(event, "BookingSchedulePage");
    }
    @FXML private void setScreenStatistics(ActionEvent event) throws IOException{
        setPage(event, "StatisticsPage");
    }
    @FXML private void setScreenClientBase(ActionEvent event) throws IOException{
        setPage(event, "ClientBasePage");
    }   
    @FXML private void setScreenOperationHistory(ActionEvent event) throws IOException, SQLException{
        setPage(event, "OperationHistoryPage");
    } 

    @FXML void showRegulations() {

    }    
    private void setPage(ActionEvent event, String path) throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("/butler/view/"+path+".fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
}
