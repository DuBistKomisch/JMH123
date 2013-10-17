/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.data_access;

/**
 *
 * @author morga_000
 */
import acme_banking_system.exceptions.BusinessException;
import acme_banking_system.exceptions.DataLayerException;

public interface PendingDAO {
    public void createPending(Pending pending) throws DataLayerException, BusinessException;
    public void deletePending(int c_id) throws DataLayerException;
    public Pending getPending(int c_id) throws DataLayerException;
}
