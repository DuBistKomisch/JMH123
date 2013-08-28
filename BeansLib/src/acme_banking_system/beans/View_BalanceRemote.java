package acme_banking_system.beans;

import acme_banking_system.data_access.ISaving;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface View_BalanceRemote {

    public ArrayList<ISaving> ViewBalance(Integer C_ID);
}
