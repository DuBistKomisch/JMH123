package acme_banking_system.beans;

import acme_banking_system.exceptions.DataLayerException;
import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface CustomerBeanRemote {

    public void createCustomer(String firstname, String lastname, Date dob, String address) throws DataLayerException;

    public void readCustomer(Integer C_ID) throws DataLayerException;

    public void updateCustomer(String firstname, String lastname, Date dob, String address, Integer C_ID) throws DataLayerException;

    public void deleteCustomer(Integer C_ID) throws DataLayerException;
}
