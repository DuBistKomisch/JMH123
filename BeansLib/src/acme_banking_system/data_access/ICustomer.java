/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.data_access;

import java.sql.Date;

/**
 *
 * @author jake
 */
public interface ICustomer {

    String getAddress();

    Date getDateOfBirth();

    String getFirstName();

    Integer getId();

    String getLastName();
}
