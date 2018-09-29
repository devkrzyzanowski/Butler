/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.dialogs;

import butler.model.Model;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 *
 * @author MichalKrzyzanowski
 */
public class connectingToDataBaseController implements Initializable{

    @FXML private ListView<String> infoListView;
    private ObservableList<String> infoObservableList;

    private Model model;
    private String nick, password, dbName;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = butler.Butler.model;
        infoObservableList = FXCollections.observableArrayList();
        infoListView.setItems(infoObservableList);
    }
    
        public void initialize(URL location, String nick, String password, String dbName) {
            initialize(location, ResourceBundle.getBundle("resources.bundles.messages", new Locale("pl")));
            this.nick = nick;
            this.password = password;
            this.dbName = dbName;
            init();
    }
        
    @FXML private void goStart(ActionEvent event) {
        butler.Butler.stageManager.changeStage((Stage) ((Node) event.getSource())
            .getScene().getWindow(), "/butler/view/BookingSchedulePage.fxml");
        }   
    
    private void init() {
        infoObservableList.add("Ładowanie sterownika");
        infoObservableList.add(((model.loadDriver()) ? "Ładowanie sterownika udane" : "Błąd ładowania sterownika"));
//        infoObservableList.add("Uruchamianie bazy danych");
//        infoObservableList.add(new String((model.startDataBase(dbName)) ? "Uruchamianie bazy danych udane" : "Uruchamianie bazy danych nieudane"));
        infoObservableList.add("Próba logowania");
        infoObservableList.add(((model.logInToDataBase(nick, password, dbName)) ? "Połączenie z bazą danych udane" : "Połączenie z bazą danych nieudane"));
        butler.Butler.USERNAME = nick;
    }


    
}
