package Beans;

import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface View_CustomerRemote {

    public void seeCustomer(Integer C_ID);
}
