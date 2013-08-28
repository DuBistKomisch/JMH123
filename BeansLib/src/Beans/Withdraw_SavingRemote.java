package Beans;

import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface Withdraw_SavingRemote {

    public void takeBalance(Integer E_ID, String ACCNUM, Double BALANCE, String desc);
}
