package acme_banking_system.beans;

import acme_banking_system.data_access.Customer;
import acme_banking_system.exceptions.BusinessException;
import acme_banking_system.exceptions.DataLayerException;
import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface CustomerBeanRemote {

    public void createCustomer(String firstname, String lastname, Date dateOfBirth, String address) throws DataLayerException;

    public Customer readCustomer(int customerId) throws BusinessException, DataLayerException;

    public void updateCustomer(int customerId, String firstname, String lastname, Date dateOfBirth, String address) throws DataLayerException;

    public void deleteCustomer(int customerId) throws DataLayerException;
}
