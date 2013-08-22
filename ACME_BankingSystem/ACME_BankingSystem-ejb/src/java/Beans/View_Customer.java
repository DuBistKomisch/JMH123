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
public class View_Customer implements View_CustomerRemote {

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
    public void seeCustomer(Integer C_ID) {
        Customer text;
        try {
        CustomerDAO dao = new RDBCustomerDAO(connection);
       // Customer customer = new Customer(C_ID);
        text = dao.readCustomer(C_ID);
        System.out.println(text.getC_ID().toString() + text.getFirstName().toString()
                + text.getLastName().toString() + text.getDateOfBirth().toString()
                + text.getAddress().toString());
        } catch (Exception e) {
        System.out.println("Could not show customer details.");
        e.printStackTrace();
     }
    }
    
    
}
