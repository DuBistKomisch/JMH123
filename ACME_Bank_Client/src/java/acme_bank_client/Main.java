package acme_bank_client;

import acme_banking_system.exceptions.LoggedInStateException;
import acme_banking_system.beans.EmployeeSessionRemote;
import acme_banking_system.data_access.Saving;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.ejb.NoSuchEJBException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import org.jboss.logging.Logger;

/**
 * @author morga_000, jake
 */
public class Main {
    
    @Resource(mappedName = "jms/BanckingSystemQueue")
    private static Queue BankingSystemQueue;
    @Resource(mappedName = "jms/bankingSystemConnectionFactory")
    private static ConnectionFactory BankingSystemConnectionFactory;
    
    private static EmployeeSessionRemote employeeSession;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        new Main().sendJMSMessageToBankingSystemQueue("NetBeans is fucking buggy");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstName, lastName, password, address, accNum, desc;
        int id;
        double amount;
        getEmployeeSession();

        
        
        // welcome
        System.out.println();
        System.out.println("Welcome to JMH123 ACME Banking System");

        while (true) {
            // get login
            System.out.println();
            System.out.println("Please log in with your name and password.");
            System.out.print("First Name: ");
            firstName = br.readLine();
            System.out.print("Last Name: ");
            lastName = br.readLine();
            System.out.print("Password: ");
            password = br.readLine();
            try {
                try {
                    employeeSession.login(firstName, lastName, password);
                } catch (NoSuchEJBException ex) {
                    // expired, get new stateful bean, try again
                    getEmployeeSession();
                    employeeSession.login(firstName, lastName, password);
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getClass().getName() + ": " + ex.getMessage());
                continue;
            }

            boolean logout = false;
            while (!logout) {
                // print menu
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println(" 1. Create Customer");
                System.out.println(" 2. Open Savings Account");
                System.out.println(" 3. Make Deposit");
                System.out.println(" 4. Make Withdrawal");
                System.out.println(" 5. View Balance");
                System.out.println(" 6. View Transactions");
                System.out.println(" 7. Log Out");
                System.out.println("(operations performed: " + employeeSession.getActionCount() + "/" + employeeSession.getActionLimit() + ")");

                // get selected option
                int option;
                try {
                    System.out.println();
                    System.out.print("Select option: ");
                    option = Integer.parseInt(br.readLine());
                } catch (Exception ex) {
                    option = 0; // invalid
                }

                // do selection
                try {
                    System.out.println();
                    switch (option) {
                        case 1: // Create Customer
                            System.out.println("Create Customer");
                            System.out.print("First Name: ");
                            firstName = br.readLine();
                            System.out.print("Last Name: ");
                            lastName = br.readLine();
                            System.out.print("DOB (yyyy-mm-dd): ");
                            dateFormat.parse(br.readLine());
                            System.out.print("Address: ");
                            address = br.readLine();
                            employeeSession.createCustomer(firstName, lastName,
                                    new Date(dateFormat.getCalendar().getTimeInMillis()),
                                    address);
                            // TODO output C_ID? bean call needs to return it
                            System.out.println("Customer created.");
                            break;
                        case 2: // Open Savings Account
                            System.out.println("Open Savings Account");
                            System.out.print("Customer ID: ");
                            id = Integer.parseInt(br.readLine());
                            System.out.print("Account No.: ");
                            accNum = br.readLine();
                            employeeSession.createSaving(id, accNum);
                            System.out.println("Savings account opened.");
                            break;
                        case 3: // Make Deposit
                            System.out.println("Make Deposit");
                            System.out.print("Account No.: ");
                            accNum = br.readLine();
                            System.out.print("Amount: ");
                            amount = Double.parseDouble(br.readLine());
                            System.out.print("Description: ");
                            desc = br.readLine();
                            employeeSession.deposit(accNum, amount, desc);
                            System.out.println("Deposit made.");
                            break;
                        case 4: // Make Withdrawal
                            System.out.println("Make Withdrawal");
                            System.out.print("Account No.: ");
                            accNum = br.readLine();
                            System.out.print("Amount: ");
                            amount = Double.parseDouble(br.readLine());
                            System.out.print("Description: ");
                            desc = br.readLine();
                            employeeSession.withdraw(accNum, amount, desc);
                            System.out.println("Withdrawal made.");
                            break;
                        case 5: // View Balance
                            System.out.println("View Balance");
                            System.out.print("Customer ID: ");
                            id = Integer.parseInt(br.readLine());
                            ArrayList<Saving> savings = employeeSession.viewBalance(id);
                            for (Saving saving : savings) {
                                System.out.printf("AccNum %s: $%.2f" + System.lineSeparator(), saving.getAccNum(), saving.getBalance());
                            }
                            break;
                        case 6: // View Transactions
                            System.out.println("View Transactions");
                            // TODO input
                            // TODO bean call
                            // TODO output
                            break;
                        case 7: // Log Out
                            System.out.println("Bye.");
                            employeeSession.logout();
                            logout = true;
                            break;
                        default:
                            System.out.println("Invalid option.");
                            break;
                    }
                    if (employeeSession.getActionCount() == employeeSession.getActionLimit()) {
                        throw new LoggedInStateException(false);
                    }
                } catch (LoggedInStateException ex) {
                    // Session timed out, return to login
                    System.out.println("Exceeded operations limit.");
                    logout = true;
                } catch (NoSuchEJBException ex) {
                    // Session timed out, return to login
                    System.out.println("Session timed out.");
                    logout = true;
                } catch (Exception ex) {
                    // Input or data layer error, return to menu
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        }
    }
    
    private Message createJMSMessageForjmsBankingSystemQueue(Session session,
                        Object messageData) throws JMSException {
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }
    
    private void sendJMSMessageToBankingSystemQueue(Object messageData) throws JMSException {
        Connection connection;
        Session session = null;
        try {
            connection = BankingSystemConnectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(BankingSystemQueue);
            messageProducer.send(createJMSMessageForjmsBankingSystemQueue(session, messageData));
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

    private static void getEmployeeSession() throws NamingException {
        employeeSession = (EmployeeSessionRemote) PortableRemoteObject.narrow(new InitialContext().lookup("java:global/ACME_BankingSystem/ACME_BankingSystem-ejb/EmployeeSession"), EmployeeSessionRemote.class);
    }
}
