package acme_banking_system.beans;

import acme_banking_system.exceptions.DataLayerException;
import acme_banking_system.exceptions.BusinessException;
import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface Create_SavingRemote {

    public void createSaving(Integer C_ID, String ACCNUM) throws BusinessException, DataLayerException;
}
