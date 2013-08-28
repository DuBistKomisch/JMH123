package Beans;

import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface Delete_CustomerRemote {

    public void removeCustomer(Integer C_ID);
}
