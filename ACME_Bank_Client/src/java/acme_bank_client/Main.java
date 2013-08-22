/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_bank_client;

import Beans.Create_CustomerRemote;
//import Beans.Delete_CustomerRemote;
//import Beans.Update_CustomerRemote;

import javax.ejb.EJB;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author morga_000
 */
public class Main {
    @EJB
    private static Create_CustomerRemote create_Customer;
//  private static Delete_CustomerRemote delete_Customer;
//  private static Update_CustomerRemote update_customer;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Calendar cal = new GregorianCalendar(2013, 00, 01);
        Date dob = new Date(cal.getTimeInMillis());
        create_Customer.addCustomer("John", "Holeroyui", dob, "StillHomeless");
//      update_customer.changeCustomerDetail("First Name", "Last Name", new Date(2013,01,01), "Address", 1);
//      delete_Customer.removeCustomer(111);
        
    }
}
