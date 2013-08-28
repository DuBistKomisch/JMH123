package Beans;

import exceptions.*;
import javax.ejb.Remote;

/**
 *
 * @author Howard Tseng
 */
@Remote
public interface Create_SavingsRemote {

    public void createSaving(Integer C_ID, String ACCNUM) throws BusinessException, DataLayerException;
}
