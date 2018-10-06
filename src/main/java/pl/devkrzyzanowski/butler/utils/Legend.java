/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.utils;

/**
 *
 * @author uwxyy
 */
public class Legend {
    private Integer idLegend;
    private String legendName;
    private String color;
    
    public Legend(Integer idLegend, String legendName, String color) {
        this.idLegend = idLegend;
        this.legendName = legendName;
        this.color = color;
    }
    
    public String toString(){
        return legendName;
    }

    public Integer getIdLegend() {
        return idLegend;
    }

    public void setIdLegend(Integer idLegend) {
        this.idLegend = idLegend;
    }

    public String getLegendName() {
        return legendName;
    }

    public void setLegendName(String legendName) {
        this.legendName = legendName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
