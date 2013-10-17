/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_homeloan.messenger;

import java.util.logging.Level;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueReceiver;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.jboss.logging.Logger;

/**
 *
 * @author morga_000
 */
public class messageSender {
    
    private Message createJMSMessageForjmsBankingSystemQueue(Session session, 
            Object messageData, Queue dest) throws JMSException {
        TextMessage tm = session.createTextMessage();
        tm.setJMSReplyTo(dest);
        tm.setText(messageData.toString());
        return tm;
    }
    
    public void sendJMSMessage(Object messageData, Queue dest, Session session) throws JMSException {
        try {
            MessageProducer messageProducer = session.createProducer(dest);
            messageProducer.send(createJMSMessageForjmsBankingSystemQueue(session, messageData, dest));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(org.jboss.logging.Logger.Level.WARN, "Cannot close session", e);
                }
            }
        }
    }
}
