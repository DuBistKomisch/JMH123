package acme_banking_system.beans;

import acme_banking_system.data_access.Customer;
import acme_banking_system.data_access.CustomerDAO;
import acme_banking_system.data_access.Employee;
import acme_banking_system.data_access.EmployeeDAO;
import acme_banking_system.data_access.RDBCustomerDAO;
import acme_banking_system.data_access.RDBEmployeeDAO;
import acme_banking_system.data_access.RDBSavingDAO;
import acme_banking_system.data_access.Saving;
import acme_banking_system.data_access.SavingDAO;
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
    public void deposit(Integer E_ID, String ACCNUM, double BALANCE, String desc) {
        try {
            SavingDAO dao = new RDBSavingDAO(connection);
            Saving saving = new Saving(ACCNUM);
            EmployeeDAO daoE = new RDBEmployeeDAO(connection);
            Employee employee = new Employee(E_ID);
            dao.deposit(employee, saving, BALANCE, desc);
        } catch (Exception e) {
            System.out.println("Could not Deposit Balance");
            e.printStackTrace();
        }
    }
}
