/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author morga_000
 */
@Remote
public interface EmployeeSessionRemote {
    public boolean login(String firstName, String lastName, String password);
    public int getCounter();

    public ArrayList ViewBalance(Integer C_ID) throws Exception;

    public void takeBalance(Integer E_ID, String accnum, Double amount, String desc) throws Exception;

    public void InputBalance(Integer E_ID, String accnum, Double amount, String desc) throws Exception;

    public void createSaving(Integer C_ID, String accnum) throws Exception;

    public void addCustomer(String firstName, String lastName, Date dob, String address) throws Exception;
}
