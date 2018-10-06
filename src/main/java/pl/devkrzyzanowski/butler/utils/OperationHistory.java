/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.utils;

/**
 *
 * @author Michał
 */
public class OperationHistory {
    private String operation;
    private String date;
    private String user;
    
    //TODO change date format from String to DATE
    public OperationHistory(String operation, String date, String user){
        this.operation = operation;
        this.date = date;
        this.user = user;
    }
    public void setOperation(String operation){
        this.operation = operation;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setUser(String user){
        this.user = user;
    }
    public String getOperation(){
        return operation;
    }
    public String getDate(){
        return date;
    }
    public String getUser(){
        return user;
    }
}
