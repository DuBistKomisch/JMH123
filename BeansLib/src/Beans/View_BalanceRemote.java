/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface View_BalanceRemote {
   public ArrayList ViewBalance(Integer C_ID);
}
