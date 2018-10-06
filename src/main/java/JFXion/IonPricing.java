/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package JFXion;


import butler.utils.Booking;
import butler.utils.Room;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import pl.devkrzyzanowski.butler.MainApp;
import pl.devkrzyzanowski.butler.Model.Database;

/**
 *
 * @author MichalKrzyzanowski
 */
public class IonPricing extends ScrollPane {
    private GridPane gridPane;
    private List<NewCell> gridPaneList;
    private List<TimeCell> timeCells;
    private List<RoomCell> roomCells;
    private List<PriceCell> priceCells;
    private ObservableList<Room> roomList;
    private ObservableList<Booking> bookingList;
    private Database db;
    
    public IonPricing(ObservableList<Room> roomList, ObservableList<Booking> bookingList){
        this.db = MainApp.databaseManager;
        this.roomList = roomList;
        this.bookingList = bookingList;
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPaneList = new ArrayList<>();
        timeCells = new ArrayList<>();
        roomCells = new ArrayList<>();
        priceCells = new ArrayList<>();
        setUpData();
        draw();
        this.setContent(gridPane);
        addListeners();
    }
    
    private void setUpData(){
        addRoomsRow(db.getRoomList());
        addTimeColumn();
        addSchedules(db.getBookingList(), timeCells, roomCells);        
    }
    
    private void addListeners() {
        priceCells.forEach((sc) -> {
            sc.setOnMouseClicked((MouseEvent event) -> {
                MainApp.stageManager.addModalStage(((Node) event.getSource()).getScene().getWindow(), 
                        "/butler/view/dialogs/modifyBooking.fxml");
//                ModifyPricingController mdf = MainApp.stageManager.getLoader().getController();
//                mdf.init(sc.getBooking(), IonPricing.this); TODO
            });
        });
    }
    
    private void draw() {
        for (int i = 0; i <= priceCells.size(); i++) {
            for (int j = 0; j <= timeCells.size(); j++) {
                gridPane.add(new NewCell(i, j), i, j);
            }
        }
        priceCells.forEach((rc) -> {
            gridPane.add(rc, rc.getIdColumn(), 0);
        }); 
        timeCells.forEach((tc) -> {
            gridPane.add(tc, 0, tc.getIdRow());
        });
        priceCells.forEach((sc) -> {
            gridPane.add(sc, sc.getIdColumn(), sc.getIdRow(), 1, sc.getBookingDays());
        });
    }

    private void addRoomsRow(ObservableList<Room> roomList) {
        priceCells.clear();
        int i = 1;
        for (Room r : roomList) {
            RoomCell rc = new RoomCell(i, r);
            roomCells.add(rc);
            i++;
        }
    }
    
    private void addTimeColumn() {
        timeCells.clear();
        for (int i = 1; i < 120; i++) {
            TimeCell tc = new TimeCell(i, new Timestamp(System.currentTimeMillis()- (86400000l * 15) + 86400000l*i));
            timeCells.add(tc);
        }
    }
    
    public void addSchedules(ObservableList<Booking> bookingList, List<TimeCell> tcl, List<RoomCell> rcl) {
        priceCells.clear();
        Integer x = 0, y = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             
        for (Booking b : bookingList) {
            for (TimeCell tc : tcl) {
                Timestamp ts = Timestamp.valueOf(b.getBeginOfBooking());
                if (tc.getFormattedTs("yyyy-MM-dd").equals(sdf.format(ts))) {
                    y = tc.getIdRow();
                }
            }           
        for (RoomCell rc : rcl) {
            if (Objects.equals(rc.getIdRoom(), b.getIdRoom())) {
                x = rc.getIdColumn();
            }
        }
            PriceCell sc = new PriceCell(x, y, b, 40);
            priceCells.add(sc);
        }
    }

    public void update() {
        gridPane.getChildren().clear();
        setUpData();
        addListeners();
        draw();
    }
}
