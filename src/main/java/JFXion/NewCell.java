/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package JFXion;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author MichalKrzyzanowski
 */
public class NewCell extends Pane {
    private Integer columnIndex, rowIndex;
    private Integer columnSpan, rowSpan;
    private Boolean click = false;
    
    public NewCell(Integer columnIndex, Integer rowIndex){
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        this.setMinSize(120, 20);
        this.setPrefSize(120, 20);
        columnSpan = 1;
        rowSpan = 1;
        this.setStyle("-fx-background-color: white;"
        + " -fx-border-width : 1px;"
        + " -fx-border-color: lightgrey grey grey lightgrey;"
        );
    }
    

    public Integer getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Integer getColumnSpan() {
        return columnSpan;
    }

    public void setColumnSpan(Integer columnSpan) {
        this.columnSpan = columnSpan;
    }

    public Integer getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(Integer rowSpan) {
        this.rowSpan = rowSpan;
    }
}