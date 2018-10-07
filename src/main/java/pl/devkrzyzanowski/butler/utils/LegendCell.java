/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.utils;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author MichalKrzyzanowski
 */
public class LegendCell extends Pane {
    private Integer idLegend;
    private String text;
    private Color color;
    private final String formattedColor;
    private final Label label;
    
    public LegendCell(String text, Color color) {
        this.text = text;
        this.color = color;
        formattedColor = String.valueOf(color).substring(2, 8);
        this.setMinSize(200, 24);
        label = new Label(text);
        label.setLayoutX(16);
        label.setLayoutY(4);
        boolean add = this.getChildren().add(label);
        this.setStyle("-fx-background-color: #"+formattedColor +";");
    }

    public Integer getIdLegend() {
        return idLegend;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
