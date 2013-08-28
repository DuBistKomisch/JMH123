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

    public void deleteSaving(Saving saving) throws DataLayerException;

    public void deposit(Employee emp, Saving acc, double amount, String desc) throws BusinessException, DataLayerException;

    public void withdraw(Employee emp, Saving acc, double amount, String desc) throws BusinessException, DataLayerException;

    public ArrayList<Saving> getCustomerSavings(Saving saving) throws DataLayerException;

    public ArrayList<Saving> getTransactionsHistory(Saving saving) throws DataLayerException;
}
