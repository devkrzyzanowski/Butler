/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.utils;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Michał
 */
public class Client {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private StringProperty city = new SimpleStringProperty();
    private StringProperty street = new SimpleStringProperty();
    private IntegerProperty homeNumber = new SimpleIntegerProperty();
    private IntegerProperty flatNumber = new SimpleIntegerProperty();
    private IntegerProperty zipCode = new SimpleIntegerProperty();
    private IntegerProperty contactPhoneNumber = new SimpleIntegerProperty();
    private StringProperty email = new SimpleStringProperty();
    
    public Client(Integer id, String firstName, String lastName, 
                  String city, String street, Integer homeNumber,
                  Integer flatNumber, Integer zipCode,
                  Integer contactPhoneNumber, String email){
        this.id.set(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.city.set(city);
        this.street.set(street);
        this.homeNumber.set(homeNumber);
        this.flatNumber.set(flatNumber);
        this.zipCode.set(zipCode);
        this.contactPhoneNumber.set(contactPhoneNumber);
        this.email.set(email);
    }
    public Client(String firstName, String lastName, 
                  String city, String street, Integer homeNumber,
                  Integer flatNumber, Integer zipCode,
                  Integer contactPhoneNumber, String email){
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.city.set(city);
        this.street.set(street);
        this.homeNumber.set(homeNumber);
        this.flatNumber.set(flatNumber);
        this.zipCode.set(zipCode);
        this.contactPhoneNumber.set(contactPhoneNumber);
        this.email.set(email);
    }    
    public void setId(Integer id) {
        this.id.set(id);
    }
    public Integer getId(){
        return id.getValue();
    }

    public Integer getZipCode() {
        return zipCode.getValue();
    }

    public void setZipCode(IntegerProperty zipCode) {
        this.zipCode = zipCode;
    }
    
    public String getFirstName() {
        return firstName.getValue();
    }

    public void setFirstName(StringProperty firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName.getValue();
    }

    public void setLastName(StringProperty lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city.getValue();
    }

    public void setCity(StringProperty city) {
        this.city = city;
    }

    public String getStreet() {
        return street.getValue();
    }

    public void setStreet(StringProperty street) {
        this.street = street;
    }

    public Integer getHomeNumber() {
        return homeNumber.getValue();
    }

    public void setHomeNumber(IntegerProperty homeNumber) {
        this.homeNumber = homeNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber.getValue();
    }

    public void setFlatNumber(IntegerProperty flatNumber) {
        this.flatNumber = flatNumber;
    }

    public Integer getContactPhoneNumber() {
        return contactPhoneNumber.getValue();
    }

    public void setContactPhoneNumber(IntegerProperty contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getEmail() {
        return email.getValue();
    }

    public void setEmail(StringProperty email) {
        this.email = email;
    }
}
