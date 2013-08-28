/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.beans;

import acme_banking_system.data_access.ISaving;
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

    public int getCounter();

    public void createCustomer(String firstName, String lastName, Date dob, String address) throws Exception;

    public void createSaving(Integer C_ID, String accnum) throws LoggedInStateException, BusinessException, DataLayerException;

    public void deposit(String accnum, Double amount, String desc) throws LoggedInStateException, BusinessException, DataLayerException;

    public void withdraw(String accnum, Double amount, String desc) throws LoggedInStateException, BusinessException, DataLayerException;

    public ArrayList<ISaving> viewBalance(Integer C_ID) throws Exception;
}
