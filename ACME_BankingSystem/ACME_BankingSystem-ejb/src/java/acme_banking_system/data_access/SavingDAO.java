package acme_banking_system.data_access;

/**
 *
 * @author morga_000
 */
import acme_banking_system.exceptions.BusinessException;
import acme_banking_system.exceptions.DataLayerException;
import java.util.ArrayList;

public interface SavingDAO {

    public void createSaving(Saving saving) throws BusinessException, DataLayerException;

    public void deleteSaving(String accNum) throws DataLayerException;

    public void deposit(int employeeId, String accNum, double amount, String description) throws BusinessException, DataLayerException;

    public void withdraw(int employeeId, String accNum, double amount, String description) throws BusinessException, DataLayerException;

    public ArrayList<Saving> getCustomerSavings(int customerId) throws DataLayerException;

    public ArrayList<Saving> getTransactionsHistory() throws DataLayerException;
}
