/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface Update_CustomerRemote {
    public void changeCustomerDetail(String firstname, String lastname, Date dob, String address, Integer C_ID);
}
