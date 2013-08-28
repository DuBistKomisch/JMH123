package Beans;

import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface View_BalanceRemote {

    public ArrayList ViewBalance(Integer C_ID);
}
