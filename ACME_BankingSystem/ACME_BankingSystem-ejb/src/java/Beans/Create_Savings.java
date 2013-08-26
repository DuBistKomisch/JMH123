/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import data_access.Customer;
import data_access.CustomerDAO;
import data_access.RDBCustomerDAO;
import data_access.RDBSavingDAO;
import data_access.Saving;
import data_access.SavingDAO;
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
public class Create_Savings implements Create_SavingsRemote {

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
    public void createSaving(Integer C_ID, String ACCNUM, Integer BALANCE) {
     try {
           // java.sql.Date sqlDob = new java.sql.Date(dob.getTime());
            CustomerDAO dao = new RDBCustomerDAO(connection);
            Customer customer = new Customer(C_ID/* sqlDob,*/);
          SavingDAO daoS = new RDBSavingDAO(connection);
          Saving saving = new Saving(ACCNUM, BALANCE);
          daoS.createSaving(customer,saving);
    } catch (Exception e) {
          System.out.println("Could not create customer.");
          e.printStackTrace();
    }
}
    
}
