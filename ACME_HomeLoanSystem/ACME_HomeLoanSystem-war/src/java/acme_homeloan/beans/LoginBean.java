/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_homeloan.beans;

import acme_banking_system.data_access.Customer;
import acme_homeloan.data_access.Customerdetails;
import acme_homeloan.data_access.Homeloans;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 *
 * @author Howard Tseng
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    private String username;
    private String password;
    
    private Homeloans homeloan;
    private Customerdetails customerdetails;
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public Homeloans getHomeloan() {
        return homeloan;
    }

    public Customerdetails getCustomerdetails() {
        return customerdetails;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String submit() {
        boolean authenticated = true;
        if (authenticated)
            return "HomePage";
        else
            return "Login";
    }
    
    public String logout() {
        return "Login";
    }
    
    public String createHomeLoan()
    {
        homeloan = new Homeloans();
        customerdetails = new Customerdetails();
        customer = new Customer(); // TODO get from system 1
        return "Page1";
    }
    
    public String cancelHomeLoan()
    {
        homeloan = null;
        customerdetails = null;
        customer = null;
        return "HomePage";
    }
    
    public String confirmHomeLoan()
    {
        // TODO
        homeloan = null;
        customerdetails = null;
        customer = null;
        return "HomePage";
    }
}
