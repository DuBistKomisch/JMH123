package Beans;

import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface Withdraw_SavingRemote {
    public void takeBalance(Integer E_ID, String ACCNUM, double BALANCE, String desc);
}
