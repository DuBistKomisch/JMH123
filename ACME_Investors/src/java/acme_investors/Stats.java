/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_investors;

import acme_banking_system.beans.SavingBeanRemote;
import acme_banking_system.exceptions.DataLayerException;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author jake
 */
@Path("/stats")
public class Stats {

    @EJB
    SavingBeanRemote bean;
    
    @GET
    @Produces("text/plain")
    public String getMessage() throws DataLayerException {
        return "" + bean.stats();
    }
}
