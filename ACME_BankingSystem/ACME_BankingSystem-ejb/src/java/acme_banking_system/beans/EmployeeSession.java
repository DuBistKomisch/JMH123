package acme_banking_system.beans;

import acme_banking_system.data_access.Employee;
import acme_banking_system.data_access.EmployeeDAO;
import acme_banking_system.data_access.Saving;
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
import javax.annotation.security.PermitAll;
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
@Stateful(name = "EmployeeSession")
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 1)
@PermitAll
public class EmployeeSession implements EmployeeSessionRemote {

    @EJB
    private CustomerBeanRemote customerBean;
    @EJB
    private SavingBeanRemote savingBean;
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
    public void login(String firstname, String lastName, String password) throws LoggedInStateException, BusinessException, DataLayerException {
        if (this.logged) {
            throw new LoggedInStateException(this.logged);
        }
        EmployeeDAO doa = new RDBEmployeeDAO(connection);
        Employee emp = doa.loginEmployee(firstname, lastName, password); // throws
        this.logged = true;
        this.E_ID = emp.getId();
        this.actions = 0;
    }

    @Remove
    @Override
    public void logout() throws LoggedInStateException {
        if (!this.logged) {
            throw new LoggedInStateException(this.logged);
        }
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
    public void createCustomer(String firstName, String lastName, Date dateOfBirth, String address) throws LoggedInStateException, DataLayerException {
        if (!this.logged) {
            throw new LoggedInStateException(this.logged);
        }
        customerBean.createCustomer(firstName, lastName, dateOfBirth, address);
        this.addAction();
    }

    @Override
    public void createSaving(int customerId, String accNum) throws LoggedInStateException, BusinessException, DataLayerException {
        if (!this.logged) {
            throw new LoggedInStateException(this.logged);
        }
        savingBean.createSaving(customerId, accNum);
        this.addAction();
    }

    @Override
    public void deposit(String accNum, double amount, String description) throws LoggedInStateException, BusinessException, DataLayerException {
        if (!this.logged) {
            throw new LoggedInStateException(this.logged);
        }
        savingBean.deposit(this.E_ID, accNum, amount, description);
        this.addAction();
    }

    @Override
    public void withdraw(String accNum, double amount, String description) throws LoggedInStateException, BusinessException, DataLayerException {
        if (!this.logged) {
            throw new LoggedInStateException(this.logged);
        }
        savingBean.withdraw(this.E_ID, accNum, amount, description);
        this.addAction();
    }

    @Override
    public ArrayList<Saving> viewBalance(int customerId) throws LoggedInStateException, DataLayerException {
        if (!this.logged) {
            throw new LoggedInStateException(this.logged);
        }
        ArrayList<Saving> list = savingBean.viewBalance(customerId);
        this.addAction();
        return list;
    }
}
