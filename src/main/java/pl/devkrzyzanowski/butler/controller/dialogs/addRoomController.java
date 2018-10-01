/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package pl.devkrzyzanowski.butler.controller.dialogs;

import butler.utils.AdditionalRoomItem;
import butler.utils.AdditionalRoomItems;
import butler.utils.Room;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.ListSelectionView;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.Model.Database;

/**
 *
 * @author uwxyy
 */
public class addRoomController extends DialogBox implements Initializable {
    
    @FXML private TextField roomNameTextField;
    @FXML private ChoiceBox<Integer> numberOfSingleBedsChoiceBox;
    @FXML private ChoiceBox<Integer> numberOfDoubleBedsChoiceBox;
    @FXML private ChoiceBox<Integer> numberOfExtraBedsChoiceBox;
    @FXML private TextField floorNumberTextField;
    @FXML private TextField buildingTextField;
    @FXML private TextField smallDescriptionTextField;
    @FXML private TextArea bigDescriptionTextArea;
    @FXML private TextArea extraDescriptionTextArea;
    @FXML private TextField priceOfRoomTextField;
    @FXML private TextField priceOfAdultTextField;
    @FXML private TextField priceOfMinorTextField;
    private Database db;
    private ObservableList<Integer> intList;
    @FXML private ListSelectionView<AdditionalRoomItem> extraListSelectionView;
    AdditionalRoomItems additionalRoomItems = new AdditionalRoomItems();
    private ObservableList<AdditionalRoomItem> selectedItems;
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        db = MainApp.databaseManager;
        extraListSelectionView.getSourceItems().addAll(additionalRoomItems.getAdditionalRoomItems());
//        for( BooleanObject bO : booleanObjectObservableList) {
//            extraListSelectionView.getSourceItems().add(bO.getName());
//        }

        selectedItems = FXCollections.observableArrayList();
        intList = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numberOfSingleBedsChoiceBox.setItems(intList);
        numberOfDoubleBedsChoiceBox.setItems(intList);
        numberOfExtraBedsChoiceBox.setItems(intList);
    }
    
        @FXML private void addRoom(ActionEvent event) throws SQLException {
            selectedItems.setAll(extraListSelectionView.getTargetItems());
            String roomName = roomNameTextField.getText();
            Integer numberOfSingleBeds = numberOfSingleBedsChoiceBox.getValue();
            Integer numberOfDoubleBeds = numberOfDoubleBedsChoiceBox.getValue();
            Integer numberOfExtraBeds = numberOfExtraBedsChoiceBox.getValue();
            Integer floorNumber = Integer.valueOf(floorNumberTextField.getText());
            String building = buildingTextField.getText();
            String smallDescription = smallDescriptionTextField.getText();
            String bigDescription = bigDescriptionTextArea.getText();
            String extraDescription = extraDescriptionTextArea.getText();
            Double priceOfroom = Double.valueOf(priceOfRoomTextField.getText());
            Double priceOfAdult = Double.valueOf(priceOfAdultTextField.getText());
            Double priceOfMinor = Double.valueOf(priceOfMinorTextField.getText());    
                
            Room room = new Room(roomName, numberOfSingleBeds, numberOfDoubleBeds,
                    numberOfExtraBeds, floorNumber, priceOfroom, priceOfAdult,
                    priceOfMinor, smallDescription, bigDescription, 
                    extraDescription, building, selectedItems);
            db.addRoomToDataBase(room);

        }
}
