package acme_homeloan_client;

import acme_banking_system.beans.WebBeanRemote;
import acme_banking_system.data_access.Customer;
import acme_homeloan.messenger.MessengerRemote;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;

/**
 *
 * @author Howard Tseng
 */
public class Main {
    
    @EJB
    private static MessengerRemote messenger;

    public static void main(String[] args) {
        Customer cust = messenger.login("Jean", "Claude");
        Double bal;
        Boolean res;
        if (cust != null) {
            System.out.println(cust.getFirstName());
//            bal = messenger.getBalance(cust.getId());
//            System.out.println("Received: " + bal);
//            res = messenger.canCreateHomeLoan(cust.getId());
//            System.out.println("Can create homeloan: " + res);
        } else {
            System.out.println("No, mr this one no here");
        }
    }
}
