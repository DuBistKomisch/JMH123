/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_homeloan.messenger;

import acme_banking_system.beans.EmployeeSessionRemote;
import acme_banking_system.data_access.Asking;
import acme_banking_system.data_access.Customer;
import java.io.Serializable;
import javax.ejb.Stateless;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSProducer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/**
 *
 * @author morga_000
 */
@Stateless
public class MessengerBean implements MessengerRemote {
    
    @Inject
    @JMSConnectionFactory("jms/bankingSystemConnectionFactory")
    JMSContext BankingContext;
    @Resource(lookup = "jms/BankingSystemQueue")
    private Queue BankingSystemQueue;
    @Resource(lookup = "jms/AnswerBankingSystemQueue")
    private Queue AnswerBankingSystemQueue;

    @Override
    public boolean canCreateHomeLoan(Integer c_id) {
        Asking ask = new Asking();
        JMSProducer msgSender = BankingContext.createProducer();
        try {
            msgSender.setJMSReplyTo(BankingSystemQueue);
            msgSender.setProperty("answer", false);
            ask.setMessage("canCreateHomeLoan");
            ask.setId(c_id);
            msgSender.send(BankingSystemQueue, ask);
            
            JMSConsumer receiver = BankingContext.createConsumer(BankingSystemQueue);
            Message message = receiver.receive(100000);
            if (message != null)
                return message.getBooleanProperty("canCreate");
        } catch (JMSException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public Integer isRepaymentPossible(double value, Integer c_id) {
        Asking ask = new Asking();
        JMSProducer msgSender = BankingContext.createProducer();
        try {
            msgSender.setJMSReplyTo(BankingSystemQueue);
            msgSender.setProperty("answer", false);
            ask.setMessage("canPerformRepayment");
            ask.setId(c_id);
            ask.setValue(value);
            msgSender.send(BankingSystemQueue, ask);
            
            JMSConsumer receiver = BankingContext.createConsumer(BankingSystemQueue);
            Message message = receiver.receive(100000);
            if (message != null && message instanceof TextMessage) {
                TextMessage msg = (TextMessage)message;
                Integer answer = Integer.parseInt(msg.getText());
                return answer;
            }
        } catch (JMSException e) {
            System.out.println(e);
        }
        return -1;
    }
    
    @Override
    public boolean performRepayment(Integer transaction, Integer c_id, boolean perform) {
        Asking ask = new Asking();
        JMSProducer msgSender = BankingContext.createProducer();
        try {
            msgSender.setJMSReplyTo(BankingSystemQueue);
            msgSender.setProperty("answer", false);
            ask.setMessage("performRepayment");
            ask.setId(c_id);
            ask.setTransaction(transaction);
            ask.setPerform(perform);
            msgSender.send(BankingSystemQueue, ask);
            
            JMSConsumer receiver = BankingContext.createConsumer(BankingSystemQueue);
            Message message = receiver.receive(100000);
            if (message != null)
                return message.getBooleanProperty("performed");
        } catch (JMSException e) {
            System.out.println(e);
        }
        return false;
    }
    
    @Override
    public double getBalance(Integer id) {
        try {
            Asking ask = new Asking();
            JMSProducer msgSender = BankingContext.createProducer();
            try {
                msgSender.setJMSReplyTo(BankingSystemQueue);
                msgSender.setProperty("answer", false);
                ask.setMessage("balance");
                ask.setId(id);
                msgSender.send(BankingSystemQueue, ask);

                JMSConsumer receiver = BankingContext.createConsumer(BankingSystemQueue);
                Message message = receiver.receive(100000);
                if (message instanceof TextMessage) {
                    TextMessage msg = (TextMessage)message;
                    System.out.println("Received: " + msg.getText());
                    return Double.parseDouble(msg.getText());
                } else {
                    System.out.println("Received something different");
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    @Override
    public Customer login(String firstname, String lastname) {
//                    try {
//            Asking ask = new Asking();
//            JMSProducer msgSender = BankingContext.createProducer();
//            try {
//                msgSender.setJMSReplyTo(AnswerBankingSystemQueue);
//                ask.setMessage("login");
//                ask.setFirstName(firstname);
//                ask.setLastName(lastname);
//                msgSender.setProperty("request", true);
//                msgSender.send(BankingSystemQueue, ask);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//
//        
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        return null;
    }

}