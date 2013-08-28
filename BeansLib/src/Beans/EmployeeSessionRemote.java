/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import exceptions.*;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author morga_000
 */
@Remote
public interface EmployeeSessionRemote {
    public void login(String firstName, String lastName, String password) throws LoggedInStateException, BusinessException, DataLayerException;

    public void logout() throws LoggedInStateException;
    
    public int getCounter();

    public ArrayList ViewBalance(Integer C_ID) throws Exception;

    public void takeBalance(String accnum, Double amount, String desc) throws Exception;

    public void InputBalance(String accnum, Double amount, String desc) throws Exception;

    public void createSaving(Integer C_ID, String accnum) throws Exception;

    public void addCustomer(String firstName, String lastName, Date dob, String address) throws Exception;
}
