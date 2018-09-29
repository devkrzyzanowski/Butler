/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package JFXion;

import butler.utils.Room;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author MichalKrzyzanowski
 */
public class RoomCell extends Pane {
    private Integer idColumn;
    private Integer idRoom;
    private Label text;
    
    public RoomCell(Integer idColumn, Room room) {
        this.idColumn = idColumn;
        this.idRoom = room.getId();
        this.setMinSize(120, 20);
        this.setPrefSize(120, 20);
        text = new Label(room.getRoomName());
                this.setStyle("-fx-background-color: grey;"
                + " -fx-border-width : 1px;"
                + " -fx-border-color: lightgrey grey grey lightgrey;"
        );
        text.setLayoutX(14);
        text.setLayoutY(2);
        this.getChildren().add(text);                
    }

    public Integer getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(Integer idColumn) {
        this.idColumn = idColumn;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Label getText() {
        return text;
    }

    public void setText(Label text) {
        this.text = text;
    }
    
}
