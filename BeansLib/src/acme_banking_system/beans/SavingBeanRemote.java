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

    public void createSaving(int customerId, String accNum) throws BusinessException, DataLayerException;

    public void deposit(int employeeId, String accNum, double balance, String description) throws BusinessException, DataLayerException;

    public void withdraw(int employeeId, String accNum, double balance, String description) throws BusinessException, DataLayerException;

    public ArrayList<ISaving> viewBalance(int customerId) throws DataLayerException;

    public void viewTransactionsHistory() throws DataLayerException;
}
