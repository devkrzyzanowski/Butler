/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author uwxyy
 */
public class LoginPageController {

    @FXML private TextField loginTextField, dbNameTextField;
    @FXML private PasswordField passwordTextField;

    public void initialize(URL url, ResourceBundle rb) {

    }   

    @FXML private void addDataBaseStructure(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/butler/view/dialogs/addNewDataBaseStructure.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML private void choser(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = 
                directoryChooser.showDialog(((Node) event.getSource()).getScene().getWindow());
                 
        if(selectedDirectory == null) {
            dbNameTextField.setText("No Directory selected");
        } else {
            dbNameTextField.setText(selectedDirectory.getAbsolutePath());
        }
    }
    @FXML private void handleLoginButton(ActionEvent event) throws IOException {   
    
    }
//    @FXML private void handleLoginButton(ActionEvent event) throws IOException {
//        butler.Butler.stageManager.changeStage((Stage) ((Node) event.getSource())
//                .getScene().getWindow(), "/butler/view/dialogs/connectingToDataBase.fxml");
//        connectingToDataBaseController ctdbc = butler.Butler.stageManager.getLoader().getController();
//        ctdbc.initialize(butler.Butler.stageManager.getLoader().getLocation(), loginTextField.getText(),
//                passwordTextField.getText(), dbNameTextField.getText());                    
//    }

    @FXML void showRegulations() {

    }  
    
    private void setPage(ActionEvent event, String path) throws IOException{
        ResourceBundle bundle = ResourceBundle.getBundle("resources.bundles.messages");
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/butler/view/"+path+".fxml"), bundle);

        Parent root = fXMLLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
