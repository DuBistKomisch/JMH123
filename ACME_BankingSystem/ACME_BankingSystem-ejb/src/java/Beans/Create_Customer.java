/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import data_access.Customer;
import data_access.CustomerDAO;
import data_access.RDBCustomerDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import javax.sql.DataSource;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;

/**
 *
 * @author Howard Tseng
 */
@Stateless
public class Create_Customer implements Create_CustomerRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
   //To change body of generated methods, choose Tools | Templates.
    
    
    @Resource(lookup = "jdbc/acmeDBDatasource")
    private DataSource dataSource;
    
    private Connection connection;

    @PostConstruct
    public void initialize() {
    try {
    connection = dataSource.getConnection();
    } catch (SQLException sqle) {
    sqle.printStackTrace();
        }
    }
    
    @PreDestroy
    public void close() {
    try {
    connection.close();
    } catch (SQLException sqle) {
    sqle.printStackTrace();
    }
}
    
    
    
    @Override
    public void addCustomer(String firstname, String lastname, Date dob, String address) {
        try {
        CustomerDAO dao = new RDBCustomerDAO(connection);
        Customer customer = new Customer(firstname, lastname, (java.sql.Date) dob, address);
        dao.createCustomer(customer);
        } catch (Exception e) {
        System.out.println("Could not create customer.");
        e.printStackTrace();
     }
    }
    
    
    
}
