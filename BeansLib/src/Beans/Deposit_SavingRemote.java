package Beans;

import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface Deposit_SavingRemote {

    public void InputBalance(Integer E_ID, String ACCNUM, Integer BALANCE, String desc);
}
