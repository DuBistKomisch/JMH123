package acme_bank_client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.Date;
//import java.util.Calendar;
//import java.util.GregorianCalendar;

//import javax.ejb.EJB;
//import Beans.Create_CustomerRemote;
//import Beans.Delete_CustomerRemote;
//import Beans.Update_CustomerRemote;

/**
 * @author morga_000, jake
 */
public class Main {
    //@EJB
    //private static Create_CustomerRemote create_Customer;
    //private static Delete_CustomerRemote delete_Customer;
    //private static Update_CustomerRemote update_customer;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        //Calendar cal = new GregorianCalendar(2013, 00, 01);
        //Date dob = new Date(cal.getTimeInMillis());
        //create_Customer.addCustomer("John", "Holeroyui", dob, "StillHomeless");
        //update_customer.changeCustomerDetail("First Name", "Last Name", new Date(2013,01,01), "Address", 1);
        //delete_Customer.removeCustomer(111);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        
        // welcome
        System.out.println();
        System.out.println("Welcome to JMH123 ACME Banking System");
        
        while (true)
        {
            // get login
            System.out.println();
            System.out.println("Please log in with your employee credentials");
            System.out.println();
            System.out.print("Employee ID: ");
            line = br.readLine();
            System.out.print("Password: ");
            line = br.readLine();
            // TODO validate and login
            
            boolean logout = false;
            while (!logout)
            {
                int ops = 0; // TODO get operations
                
                // print menu
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println(" 1. Create Customer");
                System.out.println(" 2. Open Savings Account");
                System.out.println(" 3. Make Deposit");
                System.out.println(" 4. Make Withdrawal");
                System.out.println(" 5. View Balance");
                System.out.println(" 6. Log Out");
                System.out.println("(operations performed: " + ops + "/10)");
                
                // get selected option
                System.out.println();
                System.out.print("Select option: ");
                line = br.readLine();
                System.out.println();
                int option;
                try {
                    option = Integer.parseInt(line);
                } catch (Exception ex) {
                    System.out.println("Invalid option.");
                    continue;
                }
                
                // do selection
                switch (option)
                {
                    case 1: // Create Customer
                        break;
                    case 2: // Open Savings Account
                        break;
                    case 3: // Make Deposit
                        break;
                    case 4: // Make Withdrawal
                        break;
                    case 5: // View Balance
                        break;
                    case 6: // Log Out
                        System.out.println("Bye.");
                        // TODO logout
                        logout = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }
        }
    }
}
