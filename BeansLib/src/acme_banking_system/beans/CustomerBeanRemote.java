package acme_banking_system.beans;

import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface CustomerBeanRemote {

    public void createCustomer(String firstname, String lastname, Date dob, String address);
    
    public void removeCustomer(Integer C_ID);
    
    public void changeCustomerDetail(String firstname, String lastname, Date dob, String address, Integer C_ID);
    
    public void seeCustomer(Integer C_ID);
}
