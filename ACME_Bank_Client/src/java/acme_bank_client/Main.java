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
    private static EmployeeSessionRemote employeeSessionRemote;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        // welcome
        System.out.println();
        System.out.println("Welcome to JMH123 ACME Banking System");

        while (true) {
            // get login
            System.out.println();
            System.out.println("Please log in with your name and password.");
            System.out.print("First Name: ");
            String firstName = br.readLine();
            System.out.print("Last Name: ");
            String lastName = br.readLine();
            System.out.print("Password: ");
            String password = br.readLine();
            if (!employeeSessionRemote.login(firstName, lastName, password))
            {
                System.out.println("Log in failed.");
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
                System.out.println("(operations performed: " + employeeSessionRemote.getCounter() + "/10)");

                // get selected option
                System.out.println();
                System.out.print("Select option: ");
                line = br.readLine();
                System.out.println();
                int option;
                try {
                    option = Integer.parseInt(line);
                } catch (Exception ex) {
                    option = 0; // invalid
                }

                // do selection
                switch (option) {
                    case 1: // Create Customer
                        try {
                            System.out.println("Create Customer");
                            System.out.print("First Name: ");
                            firstName = br.readLine();
                            System.out.print("Last Name: ");
                            lastName = br.readLine();
                            System.out.print("DOB (yyyy-mm-dd): ");
                            dateFormat.parse(br.readLine());
                            System.out.print("Address: ");
                            String address = br.readLine();
                            create_Customer.addCustomer(firstName, lastName,
                                    new Date(dateFormat.getCalendar().getTimeInMillis()),
                                    address);
                            // TODO output C_ID? bean call needs to return it
                            System.out.println("Customer created.");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.out.println("Invalid input.");
                        }
                        break;
                    case 2: // Open Savings Account
                        try {
                            System.out.println("Open Savings Account");
                            System.out.print("Customer ID: ");
                            int C_ID = Integer.parseInt(br.readLine());
                            System.out.print("Account No.: ");
                            String accnum = br.readLine();
                            create_Savings.createSaving(C_ID, accnum);
                            System.out.println("Savings account opened.");
                        } catch (Exception ex) {
                            System.out.println("Error: " + ex.getMessage());
                        }
                        break;
                    case 3: // Make Deposit
                        try {
                            System.out.println("Make Deposit");
                            System.out.print("Account No.: ");
                            String accnum = br.readLine();
                            System.out.print("Amount: ");
                            double amount = Double.parseDouble(br.readLine());
                            System.out.print("Description: ");
                            String desc = br.readLine();
                            deposit_Saving.InputBalance(E_ID, accnum, amount, desc);
                            System.out.println("Deposit made.");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.out.println("Invalid input.");
                        }
                        break;
                    case 4: // Make Withdrawal
                        try {
                            System.out.println("Make Withdrawal");
                            System.out.print("Account No.: ");
                            String accnum = br.readLine();
                            System.out.print("Amount: ");
                            double amount = Double.parseDouble(br.readLine());
                            System.out.print("Description: ");
                            String desc = br.readLine();
                            withdraw_Saving.takeBalance(E_ID, accnum, amount, desc);
                            System.out.println("Withdrawal made.");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.out.println("Invalid input.");
                        }
                        break;
                    case 5: // View Balance
                        try {
                            System.out.println("View Balance");
                            System.out.print("Customer ID: ");
                            int C_ID = Integer.parseInt(br.readLine());
                            ArrayList savings = view_Balance.ViewBalance(C_ID);
                            // TODO output
                            // print out all? or just get one account
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.out.println("Invalid input.");
                        }
                        break;
                    case 6: // View Transactions
                        try {
                            System.out.println("View Transactions");
                            // TODO input
                            // TODO bean call
                            // TODO output
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.out.println("Invalid input.");
                        }
                        break;
                    case 7: // Log Out
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
