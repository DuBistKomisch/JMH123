/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import data_access.Customer;
import data_access.CustomerDAO;
import data_access.RDBCustomerDAO;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author Howard Tseng
 */
@Stateless
public class Update_Customer implements Update_CustomerRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

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
    public void changeCustomerDetail(String firstname, String lastname, Date dob, String address, Integer C_ID) {
    try {
        CustomerDAO dao = new RDBCustomerDAO(connection);
        Customer customer = new Customer(firstname, lastname, (java.sql.Date) dob, address, C_ID);
        dao.updateCustomer(customer);
        } catch (Exception e) {
        System.out.println("Could not re-update customer.");
        e.printStackTrace();
     }
        
    }
    
}
