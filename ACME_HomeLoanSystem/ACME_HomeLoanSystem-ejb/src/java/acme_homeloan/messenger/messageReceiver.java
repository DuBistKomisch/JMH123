/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_homeloan.messenger;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author morga_000
 */
//@MessageDriven(activationConfig = {
//    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
//    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/AnswerBankingSystemQueue")
//})
//public class messageReceiver implements MessageListener {
//    @Resource(lookup = "jms/bankingSystemConnectionFactory")
//    private ConnectionFactory BankingSystemConnectionFactory;
//
//    public messageReceiver() {
//    }
//    
//    @Override
//    public void onMessage(Message message) {
//        TextMessage textMessage = (TextMessage) message;
//        
//        try {
//            System.out.println("Received message Home loan:" +
//                textMessage.getText());
//        } catch (JMSException ex) {
//            Logger.getLogger(
//                messageReceiver.class.getName()).log(
//                Level.SEVERE, null, ex);
//        }
//    }
//}
