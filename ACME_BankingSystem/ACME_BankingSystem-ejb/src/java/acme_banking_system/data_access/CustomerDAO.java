package acme_banking_system.data_access;

import acme_banking_system.exceptions.DataLayerException;
import java.util.ArrayList;

/**
 *
 * @author morga_000
 */
public interface CustomerDAO {

    public void createCustomer(Customer customer) throws DataLayerException;

    public Customer readCustomer(Integer C_ID) throws DataLayerException;

    public void updateCustomer(Customer customer) throws DataLayerException;

    public void deleteCustomer(Customer customer) throws DataLayerException;

    public ArrayList<Customer> getAllCustomer() throws DataLayerException;
}
