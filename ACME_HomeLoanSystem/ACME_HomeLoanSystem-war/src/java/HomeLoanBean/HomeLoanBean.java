/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeLoanBean;

import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Howard Tseng
 */
@Named(value = "homeLoan")
@RequestScoped
public class HomeLoanBean {

    /**
     * Creates a new instance of HomeLoan
     */
    public HomeLoanBean() {
    }
    
    private String firstName;
    private String lastName;
    private Date DOB;
    private String addrss;
    
    private String currentJob;
    private double salary;
    private String email;
    private int phone;
    private int postcode;
    private double loan;

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

    public String getAddrss() {
        return addrss;
    }

    public void setAddrss(String addrss) {
        this.addrss = addrss;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }
    
    
}
