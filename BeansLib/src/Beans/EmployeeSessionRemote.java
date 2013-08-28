/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import javax.ejb.Remote;

/**
 *
 * @author morga_000
 */
@Remote
public interface EmployeeSessionRemote {
    public boolean login(String firstName, String lastName, String password);
    public Boolean addAction();
    public Integer getCounter();
}
