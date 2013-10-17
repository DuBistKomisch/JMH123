/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.beans;

import acme_banking_system.data_access.Customer;
import javax.ejb.Remote;

/**
 *
 * @author morga_000
 */
@Remote
public interface WebBeanRemote {
    public Customer login(String firstname, String lastname);
    public double getBalance(Integer id);
    public boolean canCreateHomeLoan(Integer id);
    public Integer isRepaymentPossible(double value, Integer c_id);
    public boolean performRepayment(Integer transaction, Integer c_id, boolean perform);
}
