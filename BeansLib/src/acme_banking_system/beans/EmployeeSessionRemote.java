/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.beans;

import acme_banking_system.data_access.Saving;
import acme_banking_system.exceptions.LoggedInStateException;
import acme_banking_system.exceptions.DataLayerException;
import acme_banking_system.exceptions.BusinessException;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author morga_000
 */
@Remote
public interface EmployeeSessionRemote {

    public void login(String firstName, String lastName, String password) throws LoggedInStateException, BusinessException, DataLayerException;

    public void logout() throws LoggedInStateException;

    public int getActionCount();
    
    public int getActionLimit();

    public void createCustomer(String firstName, String lastName, Date dateOfBirth, String address) throws Exception;

    public void createSaving(int customerId, String accNum) throws LoggedInStateException, BusinessException, DataLayerException;

    public void deposit(String accNum, double amount, String description) throws LoggedInStateException, BusinessException, DataLayerException;

    public void withdraw(String accNum, double amount, String description) throws LoggedInStateException, BusinessException, DataLayerException;

    public ArrayList<Saving> viewBalance(int customerId) throws Exception;
}
