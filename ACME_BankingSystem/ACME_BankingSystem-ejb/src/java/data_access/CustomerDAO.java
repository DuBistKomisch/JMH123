/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_access;

/**
 *
 * @author morga_000
 */
public interface CustomerDAO {
    public void createCustomer(Customer customer);
    public Customer readCustomer(int C_ID);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(Customer customer);
}
