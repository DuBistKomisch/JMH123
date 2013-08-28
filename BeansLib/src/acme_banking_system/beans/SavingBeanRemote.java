package acme_banking_system.beans;

import acme_banking_system.data_access.ISaving;
import acme_banking_system.exceptions.DataLayerException;
import acme_banking_system.exceptions.BusinessException;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface SavingBeanRemote {

    public void createSaving(Integer C_ID, String ACCNUM) throws BusinessException, DataLayerException;

    public void deposit(Integer E_ID, String ACCNUM, double BALANCE, String desc) throws BusinessException, DataLayerException;

    public void withdraw(Integer E_ID, String ACCNUM, double BALANCE, String desc) throws BusinessException, DataLayerException;

    public ArrayList<ISaving> viewBalance(Integer C_ID) throws DataLayerException;

    public void viewTransactionsHistory(String ACCNUM) throws DataLayerException;
}
