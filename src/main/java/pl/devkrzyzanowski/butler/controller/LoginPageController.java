/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.Model.Database;
import pl.devkrzyzanowski.butler.utils.Pref;

/**
 *
 * @author MichalKrzyzanowski
 */
public class LoginPageController implements Initializable {

    @FXML private TextField usernameTextField, dirTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private Button loginButton;
    @FXML private CheckBox rememberLoginCheckBox;
    @FXML private ListView<String> infoListView;
    
    private ObservableList<String> infoObservableList;
    
    private ResourceBundle rb;
    private Pref pref;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
        pref = new Pref();
        infoObservableList = FXCollections.observableArrayList();
        infoListView.setItems(infoObservableList);
        infoObservableList.add("test");
        if (!pref.getPrefUserName().equals("")) {
        usernameTextField.setText(pref.getPrefUserName());
        } else {
            usernameTextField.setText("");
        }
        if (!pref.getPrefDir().equals("")) {
            dirTextField.setText(pref.getPrefDir());
        } else {
            dirTextField.setText("");
        }
            rememberLoginCheckBox.setSelected(pref.getSaveUserNameCheckBox());
    }   

    @FXML private void addDataBaseStructure(ActionEvent event) {
         MainApp.stageManager.addModalStageWithoutMaximize((Stage) ((Node) event.getSource())
                .getScene().getWindow(), "/fxml/addNewDataBaseDialog.fxml");
    }
    
    @FXML private void handleLoginButton(ActionEvent event) throws IOException {
        infoObservableList.add(0,LocalTime.now() + " : Próba połączenia");
        updatePrefs();
        MainApp.databaseManager.loadDriver();
        if (MainApp.databaseManager.login(dirTextField.getText(), usernameTextField.getText(), passwordTextField.getText())) {
        infoObservableList.add(0,LocalTime.now() +  ": Zalogowano jako: " + usernameTextField.getText());
        MainApp.stageManager.changeStage((Stage) ((Node) event.getSource())
            .getScene().getWindow(), "/fxml/bookingSchedulePage.fxml");
        } else {
            infoObservableList.add(0,LocalTime.now() +  ": Błąd logowania!");   
        }
    }
    
    
    @FXML private void openDirectoryChooseDialog(ActionEvent event) {
        DirectoryChooser dch = new DirectoryChooser();
        File selectedDirectory = 
                dch.showDialog(((Node) event.getSource()).getScene().getWindow());
        if(selectedDirectory == null) {
            dirTextField.setText(rb.getString("name.noDirectorySelected"));
        } else {
            dirTextField.setText(selectedDirectory.getAbsolutePath());
        }
    }
    
    private void updatePrefs() {
        if (rememberLoginCheckBox.selectedProperty().getValue()) {
            new Pref().setPrefUserName(usernameTextField.getText());
        } else {
            new Pref().setPrefUserName("");            
        }
        if (!dirTextField.getText().equals("")) {
            new Pref().setPrefDir(dirTextField.getText());
        }
        pref.setSaveUserNameCheckbox(rememberLoginCheckBox.selectedProperty().getValue());        
    }
    
    @FXML void showRegulations() {
        //TODO : INIT
    }  

    
    private void initValidators() {
        usernameTextField.textProperty().addListener((observable) -> {
        
        });
        passwordTextField.textProperty().addListener((observable) -> {
         
        });
    }
}
