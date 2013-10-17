/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.beans;

import acme_banking_system.data_access.Customer;
import acme_banking_system.exceptions.LoggedInStateException;
import acme_banking_system.exceptions.DataLayerException;
import acme_banking_system.exceptions.BusinessException;
import javax.ejb.Remote;

/**
 *
 * @author morga_000
 */
@Remote
public interface CustomerSessionRemote {

    public void login(String firstName, String lastName) throws LoggedInStateException, BusinessException, DataLayerException;

    public void logout() throws LoggedInStateException;
    
    public Customer getCustomer() throws BusinessException, DataLayerException;
}
