/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.data_access;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author jake
 */
public interface ISaving {

    int getId();

    String getAccNum();

    double getBalance();

    Date getDateTimeCreated();
}
