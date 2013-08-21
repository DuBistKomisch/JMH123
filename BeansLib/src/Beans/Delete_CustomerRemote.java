/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface Delete_CustomerRemote {
    public void removeCustomer(String C_ID);
}
