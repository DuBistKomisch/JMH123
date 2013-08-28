package acme_banking_system.beans;

import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface Create_CustomerRemote {

    public void addCustomer(String firstname, String lastname, Date dob, String address);
}
