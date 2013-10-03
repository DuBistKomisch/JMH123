/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author morga_000
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/BanckingSystemQueue")
})
public class SavingReceiver implements MessageListener {
    
    public SavingReceiver() {
    }
    
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("Received message:" +
                textMessage.getText());
        } catch (JMSException ex) {
            Logger.getLogger(
                SavingReceiver.class.getName()).log(
                Level.SEVERE, null, ex);
        }
    }
}
