package data_access;

/**
 *
 * @author morga_000
 */
import java.util.ArrayList;

public interface SavingDAO {

    public void createSaving(Saving saving) throws Exception;

    public void deleteSaving(Saving saving);

    public ArrayList<Saving> getUserAccount(Saving saving);
    
    public ArrayList<Saving> getTransHistory(Saving saving);

    public void withdraw(Employee emp, Saving acc, double amount, String desc) throws Exception;

    public void deposit(Employee emp, Saving acc, double amount, String desc) throws Exception;
}
