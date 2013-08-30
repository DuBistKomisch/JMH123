package acme_banking_system.data_access;

import acme_banking_system.exceptions.BusinessException;
import acme_banking_system.exceptions.DataLayerException;
import java.util.ArrayList;

/**
 *
 * @author morga_000
 */

// CRUD for the Customer

public interface CustomerDAO {

    public void createCustomer(Customer customer) throws DataLayerException;

    public Customer readCustomer(int customerId) throws BusinessException, DataLayerException;

    public void updateCustomer(Customer customer) throws DataLayerException;

    public void deleteCustomer(int customerId) throws DataLayerException;

    public ArrayList<Customer> getAllCustomer() throws DataLayerException;
}
