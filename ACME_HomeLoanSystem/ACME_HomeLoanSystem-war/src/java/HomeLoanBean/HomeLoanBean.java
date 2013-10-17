/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeLoanBean;

import java.util.Date;
import javax.inject.Named;

/**
 *
 * @author Howard Tseng
 */
@Named(value = "homeLoan")
public class HomeLoanBean {

    /**
     * Creates a new instance of HomeLoan
     */
    public HomeLoanBean() {
    }
    
    // page 1
    private String firstName;
    private String lastName;
    private Date DOB;
    private String address;
    
    // page 2
    private String currentJob;
    private double salary;
    
    // page 3
    private String email;
    private String phone;
    private String postcode;
    private int method;
    
    // page 4
    private double loan;
    private boolean agree;

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }
}
