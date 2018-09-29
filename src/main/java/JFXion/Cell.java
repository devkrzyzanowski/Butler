package JFXion;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MichalKrzyzanowski
 */
public class Cell extends Pane {
    private Label text;
    final String FORMAT = "yyyy.MM.dd";
    final String NEW_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private Date d;
    private Timestamp ts;
    
    public Cell(String text2, Integer status){

        this.setMinSize(160, 30);
        this.setPrefSize(160, 30);
        text = new Label(text2);
        text.setTextFill(Paint.valueOf("#ffffff"));
        String col ;
        switch (status){
            case 1 : col = "#eeeeee"; break;
            case 2 : col = "green"; break;
            case 3 : col = "yellow"; break;
            case 4 : col = "grey"; break;
            default: col = "red"; break;
        }

        text.setLayoutX(14);
        text.setLayoutY(8);
        this.getChildren().add(text);
        this.setStyle("-fx-background-color: " + col + ";"
                + " -fx-border-width : 1px;"
                + " -fx-border-color: lightgrey grey grey lightgrey;"
        );
        
    }
}
