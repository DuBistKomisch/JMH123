package acme_banking_system.beans;

import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface View_CustomerRemote {

    public void seeCustomer(Integer C_ID);
}
