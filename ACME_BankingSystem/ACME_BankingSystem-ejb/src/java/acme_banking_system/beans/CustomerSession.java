package acme_banking_system.beans;

import acme_banking_system.data_access.Customer;
import acme_banking_system.data_access.CustomerDAO;
import acme_banking_system.data_access.RDBCustomerDAO;
import acme_banking_system.data_access.RDBSavingDAO;
import acme_banking_system.data_access.SavingDAO;
import acme_banking_system.exceptions.BusinessException;
import acme_banking_system.exceptions.DataLayerException;
import acme_banking_system.exceptions.LoggedInStateException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.sql.DataSource;

/**
 *
 * @author morga_000
 */
@Stateful(name = "CustomerSession")
@PermitAll
public class CustomerSession implements CustomerSessionRemote {

    private boolean logged = false;
    private int C_ID;
    @Resource(lookup = "jdbc/acmeDBDatasource")
    private DataSource dataSource;
    private Connection connection;

    @PostActivate
    @PostConstruct
    public void initialize() {
        try {
            this.logged = false;
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
            connection.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void login(String firstname, String lastName) throws LoggedInStateException, BusinessException, DataLayerException {
        if (this.logged) {
            throw new LoggedInStateException(this.logged);
        }
        CustomerDAO doa = new RDBCustomerDAO(connection);
        Customer cust = doa.loginCustomer(firstname, lastName); // throws
        this.logged = true;
        this.C_ID = cust.getId();
    }

    @Remove
    @Override
    public void logout() throws LoggedInStateException {
        if (!this.logged) {
            throw new LoggedInStateException(this.logged);
        }
        this.logged = false;
    }
    
    @Override
    public Customer getCustomer() throws BusinessException, DataLayerException {
        CustomerDAO dao = new RDBCustomerDAO(connection);
        return dao.readCustomer(C_ID); // throws
    }

    @Override
    public void makeRepayment(String accNum, double amount) throws BusinessException, DataLayerException {
        SavingDAO dao = new RDBSavingDAO(connection);
        dao.withdraw(1, accNum, amount, "Home Loan repayment");
    }
}
