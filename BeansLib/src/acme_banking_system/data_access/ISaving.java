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
public interface ISaving {

    String getACCNUM();

    double getBALANCE();

    Date getCREATIONTIME();

    Integer getC_ID();

    String getDesc();

    Integer getE_ID();
    
}
