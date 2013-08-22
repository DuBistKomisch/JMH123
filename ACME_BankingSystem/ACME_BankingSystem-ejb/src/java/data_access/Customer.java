/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_access;

/**
 *
 * @author morga_000
 */
import java.util.Calendar;
import java.sql.Date;

public class Customer {
    private Integer C_ID;
    private String C_FirstName;
    private String C_LastName;
    private Date DOB;
    private String Address;

    

    public Customer(String firstName, String lastName, Date dateOfBirth, String address) {
        this.C_FirstName = firstName;
        this.C_LastName = lastName;
        this.DOB = dateOfBirth;
        this.Address = address;
    }
    
    public Customer(Integer C_ID){
        this.C_ID = C_ID;
    }

    public Customer(String firstName, String lastName, Date dateOfBirth, String address, Integer C_ID) {
        this.C_FirstName = firstName;
        this.C_LastName = lastName;
        this.DOB = dateOfBirth;
        this.Address = address;
        this.C_ID = C_ID;
    }
    
    public Integer getC_ID() {
        return C_ID;
    }

    public void setC_ID(Integer C_ID) {
        this.C_ID = C_ID;
    }

    public String getFirstName() {
        return C_FirstName;
    }

    public void setFirstName(String firstName) {
        this.C_FirstName = firstName;
    }

    public String getLastName() {
        return C_LastName;
    }

    public void setLastName(String lastName) {
        this.C_LastName = lastName;
    }

    public Date getDateOfBirth() {
        return DOB;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.DOB = dateOfBirth;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }
}