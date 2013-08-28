package acme_banking_system.beans;

import acme_banking_system.data_access.Employee;
import acme_banking_system.data_access.EmployeeDAO;
import acme_banking_system.data_access.ISaving;
import acme_banking_system.data_access.RDBEmployeeDAO;
import acme_banking_system.exceptions.BusinessException;
import acme_banking_system.exceptions.DataLayerException;
import acme_banking_system.exceptions.LoggedInStateException;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.sql.DataSource;

/**
 *
 * @author morga_000
 */
@Stateful(name="EmployeeSession")
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 1)
public class EmployeeSession implements EmployeeSessionRemote {

    @EJB
    private Create_CustomerRemote create_Customer;
    @EJB
    private Create_SavingRemote create_Savings;
    @EJB
    private Deposit_SavingRemote deposit_Saving;
    @EJB
    private Withdraw_SavingRemote withdraw_Saving;
    @EJB
    private View_BalanceRemote view_Balance;
    private boolean logged = false;
    private int E_ID;
    private int actions;
    @Resource(lookup = "jdbc/acmeDBDatasource")
    private DataSource dataSource;
    private Connection connection;

    @PostActivate
    @PostConstruct
    public void initialize() {
        try {
            this.logged = false;
            this.actions = 0;
            connection = dataSource.getConnection();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @PrePassivate
    @PreDestroy
    public void close() {
        try {
            this.logged = false;
            this.actions = 0;
            connection.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void login(String firstname, String lastName, String password) throws LoggedInStateException, DataLayerException {
        if (this.logged)
            throw new LoggedInStateException("You are already logged in.");
        EmployeeDAO doa = new RDBEmployeeDAO(connection);
        Employee emp = doa.loginEmployee(firstname, lastName, password);
        if (emp == null)
            throw new LoggedInStateException("Wrong employee name and password.");
        this.logged = true;
        this.E_ID = emp.getE_ID();
        this.actions = 0;
    }

    @Remove
    @Override
    public void logout() throws LoggedInStateException {
        if (!this.logged)
            throw new LoggedInStateException("You are not logged in.");
        this.logged = false;
        this.actions = 0;
    }
    
    private boolean addAction() {
        if (this.actions < 10) {
            this.actions++;
            return true;
        } else {
            this.actions = 0;
            this.logged = false;
            return false;
        }
    }

    @Override
    public int getCounter() {
        return this.actions;
    }

    @Override
    public void createCustomer(String firstName, String lastName, Date dob, String address) throws LoggedInStateException, DataLayerException {
        if (!this.logged)
            throw new LoggedInStateException("You are not logged in.");
        create_Customer.createCustomer(firstName, lastName, dob, address);
        this.addAction();
    }

    @Override
    public void createSaving(Integer C_ID, String accnum) throws LoggedInStateException, BusinessException, DataLayerException {
        if (!this.logged)
            throw new LoggedInStateException("You are not logged in.");
        create_Savings.createSaving(C_ID, accnum);
        this.addAction();
    }

    @Override
    public void deposit(String accnum, Double amount, String desc) throws LoggedInStateException, DataLayerException {
        if (!this.logged)
            throw new LoggedInStateException("You are not logged in.");
        deposit_Saving.deposit(this.E_ID, desc, amount, desc);
        this.addAction();
    }
    
    @Override
    public void withdraw(String accnum, Double amount, String desc) throws LoggedInStateException, DataLayerException {
        if (!this.logged)
            throw new LoggedInStateException("You are not logged in.");
        withdraw_Saving.withdraw(this.E_ID, desc, amount, desc);
        this.addAction();
    }

    @Override
    public ArrayList<ISaving> viewBalance(Integer C_ID) throws LoggedInStateException, DataLayerException {
        if (!this.logged)
            throw new LoggedInStateException("You are not logged in.");
        ArrayList list = view_Balance.viewBalance(C_ID);
        this.addAction();
        return list;
    }
}
