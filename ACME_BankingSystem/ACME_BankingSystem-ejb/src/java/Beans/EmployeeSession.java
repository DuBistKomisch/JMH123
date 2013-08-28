package Beans;

import data_access.Employee;
import data_access.EmployeeDAO;
import data_access.RDBEmployeeDAO;
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
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.sql.DataSource;

/**
 *
 * @author morga_000
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 1)
public class EmployeeSession implements EmployeeSessionRemote {

    @EJB
    private static Create_CustomerRemote create_Customer;
    @EJB
    private static Create_SavingsRemote create_Savings;
    @EJB
    private static Deposit_SavingRemote deposit_Saving;
    @EJB
    private static Withdraw_SavingRemote withdraw_Saving;
    @EJB
    private static View_BalanceRemote view_Balance;
    private boolean logged = false;
    private int actions = 0;
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
    public boolean login(String firstname, String lastName, String password) {
        EmployeeDAO doa = new RDBEmployeeDAO(connection);
        Employee emp = doa.loginEmployee(firstname, lastName, password);
        if (emp != null) {
            this.logged = true;
        } else {
            this.logged = false;
        }
        this.actions = 0;
        return this.logged;
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
    public void addCustomer(String firstName, String lastName, Date dob, String address) throws Exception {
        if (!this.logged)
            throw new Exception("You are not logged in.");
        create_Customer.addCustomer(firstName, lastName, dob, address);
        this.addAction();
    }

    @Override
    public void createSaving(Integer C_ID, String accnum) throws Exception {
        if (!this.logged)
            throw new Exception("You are not logged in.");
        create_Savings.createSaving(C_ID, accnum);
        this.addAction();
    }

    @Override
    public void InputBalance(Integer E_ID, String accnum, Double amount, String desc) throws Exception {
        if (!this.logged)
            throw new Exception("You are not logged in.");
        deposit_Saving.InputBalance(E_ID, desc, amount, desc);
        this.addAction();
    }
    
    @Override
    public void takeBalance(Integer E_ID, String accnum, Double amount, String desc) throws Exception {
        if (!this.logged)
            throw new Exception("You are not logged in.");
        withdraw_Saving.takeBalance(E_ID, desc, amount, desc);
        this.addAction();
    }

    @Override
    public ArrayList ViewBalance(Integer C_ID) throws Exception {
        if (!this.logged)
            throw new Exception("You are not logged in.");
        ArrayList list = view_Balance.ViewBalance(C_ID);
        this.addAction();
        return list;
    }
}
