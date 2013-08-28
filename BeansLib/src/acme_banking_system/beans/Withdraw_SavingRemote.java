package acme_banking_system.beans;

import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface Withdraw_SavingRemote {
    public void withdraw(Integer E_ID, String ACCNUM, double BALANCE, String desc);
}
