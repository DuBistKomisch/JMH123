/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.beans;

import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface View_Transaction_HistoryRemote {
    public void viewTransactionsHistory(String ACCNUM);
}
