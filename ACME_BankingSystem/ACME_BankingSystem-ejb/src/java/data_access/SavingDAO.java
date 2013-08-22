/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_access;

/**
 *
 * @author morga_000
 */
import java.util.ArrayList;

public interface SavingDAO {
   public void createSaving(Customer customer, Saving saving) throws Exception;
   public void deleteSaving(Saving saving);
   public ArrayList<Saving> getUserAccount(Customer customer);
   public void withdraw(Employee emp, Saving acc, Integer amount, String desc) throws Exception;
   public void deposit(Employee emp, Saving acc, Integer amount, String desc) throws Exception;
}
