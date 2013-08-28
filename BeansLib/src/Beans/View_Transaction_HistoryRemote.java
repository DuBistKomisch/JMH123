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
public interface View_Transaction_HistoryRemote {
    public void seeTrans(String ACCNUM);
}
