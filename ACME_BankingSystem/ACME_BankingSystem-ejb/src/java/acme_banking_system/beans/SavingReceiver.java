/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.beans;

import acme_banking_system.data_access.Asking;
import acme_banking_system.data_access.Customer;
import acme_banking_system.data_access.CustomerDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author morga_000
 */
@MessageDriven(activationConfig = {
//    @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "request = true"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/BankingSystemQueue"),
    @ActivationConfigProperty(propertyName = "connectionfactoryLookup", propertyValue = "jms/bankingSystemConnectionFactory")
})
public class SavingReceiver implements MessageListener {

    @EJB
    private WebBeanRemote WebBean;
    
    @Inject
    @JMSConnectionFactory("jms/bankingSystemConnectionFactory")
    private JMSContext context;
    @Resource(lookup = "jms/AnswerBankingSystemQueue")
    private Queue AnswerBankingSystemQueue;
    
    @Override
    public void onMessage(Message message) {
//        ObjectMessage objMess = (ObjectMessage)message;
//        Object response = null;
        try {
            context.createProducer().send(AnswerBankingSystemQueue, "Test");
            //            sendReply(null, AnswerBankingSystemQueue);
            //            Asking ask = (Asking) objMess.getObject();
//            double res = 0;
//            Boolean output;
//            Integer id;
//            System.out.println("GOT MESSAGE: " + ask.getMessage());
//            if ("login".equals(ask.getMessage())) {
//                response = WebBean.login(ask.getFirstName(), ask.getLastName());
//                sendReply(response, (Queue) message.getJMSReplyTo());
//            } else if ("balance".equals(ask.getMessage())) {
//                res = WebBean.getBalance(ask.getId());
//                sendPropertyReply(res, (Queue)message.getJMSReplyTo());
//            } else if ("canPerformRepayment".equals(ask.getMessage())) {
//                id = WebBean.isRepaymentPossible(ask.getValue(), ask.getId());
//                sendIntegerReply(id, (Queue)message.getJMSReplyTo());
//            } else if ("performRepayment".equals(ask.getMessage())) {
//                output = WebBean.performRepayment(ask.getTransaction(), ask.getId(), ask.getPerform());
//                sendBooleanReply(output, (Queue)message.getJMSReplyTo());
//            } else if ("canCreateHomeLoan".equals(ask.getMessage())) {
//                output = WebBean.canCreateHomeLoan(ask.getId());
//                sendBooleanReply(output, (Queue)message.getJMSReplyTo());                
//            }
        } catch (Exception ex) {
            Logger.getLogger(
                SavingReceiver.class.getName()).log(
                Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    private void sendReply(Object response, Queue dest)
            throws JMSException
    {
        try {
            System.out.println("TextMDB.sendReply, this=" + (Customer)response + ", dest="+dest);
            JMSProducer sender = context.createProducer();
            if (response == null) {
                System.out.println("NULL");
                sender.setProperty("found", false);
                sender.send(dest, (Customer)null);
            } else {
                System.out.println("NOT NULL");
                sender.setProperty("found", true);
                sender.send(dest, (Customer)response);
            }
        } catch (Exception e) {
            System.err.println("Exeption while sending");
            System.out.println(e);
        }
    }

    private void sendPropertyReply(Double res, Queue dest)
            throws JMSException
    {
        try {
            System.out.println("Send balance");
            JMSProducer sender = context.createProducer();
            sender.setProperty("answer", true);
            sender.send(dest, res.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void sendBooleanReply(Boolean res, Queue dest)
            throws JMSException
    {
        try {
            System.out.println("Send boolean");
            JMSProducer sender = context.createProducer();
            sender.clearProperties();
            sender.setProperty("answer", true);
            System.out.println("Boolean res : " + res.toString());
            sender.send(dest, res.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

        private void sendIntegerReply(Integer res, Queue dest)
            throws JMSException
    {
        try {
            System.out.println("Send balance");
            JMSProducer sender = context.createProducer();
            sender.clearProperties();
            sender.setProperty("answer", true);
            System.out.println("Integer res : " + res.toString());
            sender.send(dest, res.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
