package acme_banking_system.beans;

import acme_banking_system.data_access.Employee;
import acme_banking_system.data_access.EmployeeDAO;
import acme_banking_system.data_access.ISaving;
import acme_banking_system.data_access.RDBEmployeeDAO;
import acme_banking_system.exceptions.BusinessException;
import acme_banking_system.exceptions.DataLayerException;
import acme_banking_system.data_access.RDBSavingDAO;
import acme_banking_system.data_access.Saving;
import acme_banking_system.data_access.SavingDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class SavingBean implements SavingBeanRemote {

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
    public void createSaving(Integer C_ID, String ACCNUM) throws BusinessException, DataLayerException {
        try {
            SavingDAO daoS = new RDBSavingDAO(connection);
            Saving saving = new Saving(C_ID, ACCNUM);
            daoS.createSaving(saving);
        } catch (Exception e) {
            System.out.println("Could not create savings.");
            throw e;
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
    
    @Override
    public void withdraw(Integer E_ID, String ACCNUM, double BALANCE, String desc) {
        try {
            SavingDAO dao = new RDBSavingDAO(connection);
            Saving saving = new Saving(ACCNUM);
            EmployeeDAO daoE = new RDBEmployeeDAO(connection);
            Employee employee = new Employee(E_ID);
            dao.withdraw(employee, saving, BALANCE, desc);
        } catch (Exception e) {
            System.out.println("Could not Withdraw Balance");
            e.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<ISaving> viewBalance(Integer C_ID) {
        ArrayList<ISaving> text = new ArrayList<ISaving>();
        try {
            SavingDAO dao = new RDBSavingDAO(connection);
            Saving saving = new Saving(C_ID);
            text.addAll(dao.getCustomerSavings(saving));

        } catch (Exception e) {
            System.out.println("Could not create customer.");
            e.printStackTrace();
            text = null;
        }
        return text;
    }
    
    @Override
    public void viewTransactionsHistory(String ACCNUM) {
        ArrayList<Saving> text;
        try {
           SavingDAO dao = new RDBSavingDAO(connection);
            Saving saving = new Saving(ACCNUM);
            text = dao.getTransactionsHistory(saving);

        } catch (Exception e) {
            System.out.println("Could not create customer.");
            e.printStackTrace();
            text = null;
        }
        System.out.println(text.toString());
    }
}
