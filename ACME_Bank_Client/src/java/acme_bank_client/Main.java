package acme_bank_client;

import Beans.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;

/**
 * @author morga_000, jake
 */
public class Main {

    @EJB
    private static EmployeeSessionRemote employeeSession;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstName, lastName, password, address, accnum, desc;
        int C_ID;
        double amount;

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
                employeeSession.login(firstName, lastName, password);
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
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
                System.out.println("(operations performed: " + employeeSession.getCounter() + "/10)");

                // get selected option
                int option;
                try {
                    System.out.println();
                    System.out.print("Select option: ");
                    System.out.println();
                    option = Integer.parseInt(br.readLine());
                } catch (Exception ex) {
                    option = 0; // invalid
                }

                // do selection
                try {
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
                            employeeSession.addCustomer(firstName, lastName,
                                    new Date(dateFormat.getCalendar().getTimeInMillis()),
                                    address);
                            // TODO output C_ID? bean call needs to return it
                            System.out.println("Customer created.");
                            break;
                        case 2: // Open Savings Account
                            System.out.println("Open Savings Account");
                            System.out.print("Customer ID: ");
                            C_ID = Integer.parseInt(br.readLine());
                            System.out.print("Account No.: ");
                            accnum = br.readLine();
                            employeeSession.createSaving(C_ID, accnum);
                            System.out.println("Savings account opened.");
                            break;
                        case 3: // Make Deposit
                            System.out.println("Make Deposit");
                            System.out.print("Account No.: ");
                            accnum = br.readLine();
                            System.out.print("Amount: ");
                            amount = Double.parseDouble(br.readLine());
                            System.out.print("Description: ");
                            desc = br.readLine();
                            employeeSession.InputBalance(accnum, amount, desc);
                            System.out.println("Deposit made.");
                            break;
                        case 4: // Make Withdrawal
                            System.out.println("Make Withdrawal");
                            System.out.print("Account No.: ");
                            accnum = br.readLine();
                            System.out.print("Amount: ");
                            amount = Double.parseDouble(br.readLine());
                            System.out.print("Description: ");
                            desc = br.readLine();
                            employeeSession.takeBalance(accnum, amount, desc);
                            System.out.println("Withdrawal made.");
                            break;
                        case 5: // View Balance
                            System.out.println("View Balance");
                            System.out.print("Customer ID: ");
                            C_ID = Integer.parseInt(br.readLine());
                            ArrayList savings = employeeSession.ViewBalance(C_ID);
                            // TODO output
                            // print out all? or just get one account
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
                } catch (IllegalStateException ex) {
                    // Session timed out
                    System.out.println("Session timed out.");
                    logout = true;
                } catch (Exception ex) {
                    // Input or data layer error
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        }
    }
}
