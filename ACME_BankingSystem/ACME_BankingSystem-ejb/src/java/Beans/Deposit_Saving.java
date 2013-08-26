/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import data_access.Customer;
import data_access.CustomerDAO;
import data_access.Employee;
import data_access.EmployeeDAO;
import data_access.RDBCustomerDAO;
import data_access.RDBEmployeeDAO;
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
public class Deposit_Saving implements Deposit_SavingRemote {

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
    public void InputBalance(Integer E_ID, String ACCNUM, Integer BALANCE, String desc) {
         try {
            //java.sql.Date sqlDob = new java.sql.Date(dob.getTime());
            SavingDAO dao = new RDBSavingDAO(connection);
            Saving saving = new Saving( ACCNUM);
            EmployeeDAO daoE = new RDBEmployeeDAO(connection);
            Employee employee = new Employee(E_ID);
            dao.deposit(employee,saving, BALANCE, desc);
        } catch (Exception e) {
            System.out.println("Could not Deposit Balance");
            e.printStackTrace();
        }
    }

}
