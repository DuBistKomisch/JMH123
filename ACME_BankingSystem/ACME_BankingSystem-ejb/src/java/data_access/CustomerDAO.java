/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_access;

import java.util.ArrayList;

/**
 *
 * @author morga_000
 */
public interface CustomerDAO {
   public void createCustomer(Customer customer);
    public Customer readCustomer(Integer C_ID);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(Customer customer);
    public ArrayList<Customer>  getAllCustomer();
}
