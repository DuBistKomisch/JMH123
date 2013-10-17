/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_homeloan.messenger;

import acme_banking_system.data_access.Customer;
import javax.ejb.Remote;

/**
 *
 * @author morga_000
 */
@Remote
public interface MessengerRemote {
    public Customer login(String firstname, String lastname);
    public double getBalance(Integer c_id);
    public Integer isRepaymentPossible(double value, Integer c_id);
    public boolean performRepayment(Integer transaction, Integer c_id, boolean perform);
    public boolean canCreateHomeLoan(Integer c_id);
}
