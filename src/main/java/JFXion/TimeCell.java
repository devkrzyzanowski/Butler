/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package JFXion;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author MichalKrzyzanowski
 */
public class TimeCell extends Pane {
    private Integer idRow;
    private Timestamp ts;
    private Label text;
    
    public TimeCell(Integer idRow, Timestamp ts) {
        this.idRow = idRow;
        this.ts = ts;
        this.setMinSize(120, 20);
        this.setPrefSize(120, 20);
        text = new Label(getFormattedTs("yyyy-MM-dd"));
        this.setStyle("-fx-background-color: grey;"
                + " -fx-border-width : 1px;"
                + " -fx-border-color: lightgrey grey grey lightgrey;"
        );
        text.setLayoutX(14);
        text.setLayoutY(2);
        this.getChildren().add(text);
//java.sql.Timestamp ts = rs.getTimestamp(1);
//java.util.GregorianCalendar cal = Calendar.getInstance();
//cal.setTime(ts);
//System.out.println(cal.get(java.util.Calendar.DAY_OF_WEEK));
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(ts);
        if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            text.setTextFill(Color.RED);
        }
        
    }

    public Timestamp getTs() {
        return ts;
    }
    public String getFormattedTs(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(ts);
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    public Integer getIdRow() {
        return idRow;
    }

    public void setIdRow(Integer idRow) {
        this.idRow = idRow;
    }   
}
