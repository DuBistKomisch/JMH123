/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_homeloan.beans;

import acme_banking_system.beans.CustomerSessionRemote;
import acme_banking_system.beans.EmployeeSessionRemote;
import acme_banking_system.data_access.Customer;
import acme_banking_system.exceptions.BusinessException;
import acme_banking_system.exceptions.DataLayerException;
import acme_banking_system.exceptions.LoggedInStateException;
import acme_homeloan.data_access.Customerdetails;
import acme_homeloan.data_access.CustomerdetailsJpaController;
import acme_homeloan.data_access.Homeloans;
import acme_homeloan.data_access.HomeloansJpaController;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.NoSuchEJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/**
 *
 * @author Howard Tseng
 */
@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String firstName;
    private String lastName;
    private Homeloans homeloan;
    private Customerdetails customerdetails;
    private Customer customer;
    private CustomerdetailsJpaController cjc;
    private HomeloansJpaController hjc;
    private static CustomerSessionRemote customerSession;

    public LoginBean() throws Exception {
        cjc = new CustomerdetailsJpaController();
        hjc = new HomeloansJpaController();
        getCustomerSession();
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

    public Customer getCustomer() {
        return customer;
    }

    public Homeloans getHomeloan() {
        return homeloan;
    }

    public Customerdetails getCustomerdetails() {
        return customerdetails;
    }
    
    private static void getCustomerSession() throws NamingException {
        customerSession = (CustomerSessionRemote) PortableRemoteObject.narrow(new InitialContext().lookup("java:global/ACME_BankingSystem-ejb/CustomerSession!acme_banking_system.beans.CustomerSessionRemote"), CustomerSessionRemote.class);
    }

    public String login() {
        boolean authenticated = false;
        try {
            try {
                customerSession.login(firstName, lastName);
                authenticated = true;
            } catch (NoSuchEJBException ex) {
                // expired, get new stateful bean, try again
                getCustomerSession();
                customerSession.login(firstName, lastName);
                authenticated = true;
            }
        } catch (Exception ex) {
            System.out.println("bad login: " + ex.getClass());
        }
        if (authenticated) {
            return "HomePage";
        } else {
            firstName = "";
            lastName = "";
            return "Login";
        }
    }

    public String logout() throws LoggedInStateException {
        customerSession.logout();
        return "Login";
    }

    public String createHomeLoan() throws BusinessException, DataLayerException {
        homeloan = new Homeloans();
        customerdetails = new Customerdetails();
        customer = customerSession.getCustomer();
        return "Page1";
    }

    public String cancelHomeLoan() {
        homeloan = null;
        customerdetails = null;
        customer = null;
        return "HomePage";
    }

    public String confirmHomeLoan() {
        customerdetails.setCId(customer.getId());
        homeloan.setCId(customer.getId());
        homeloan.setAmountrepayed(BigDecimal.ZERO);
        try {
            cjc.create(customerdetails);
            hjc.create(homeloan);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return "HomeLoanConfirm";
        }

        homeloan = null;
        customerdetails = null;
        customer = null;
        return "HomePage";
    }
}
