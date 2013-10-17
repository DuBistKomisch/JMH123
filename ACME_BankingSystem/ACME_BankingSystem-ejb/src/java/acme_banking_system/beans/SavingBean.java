package acme_banking_system.beans;

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
    public void createSaving(int customerId, String accNum) throws BusinessException, DataLayerException {
        try {
            SavingDAO daoS = new RDBSavingDAO(connection);
            Saving saving = new Saving(customerId, accNum);
            daoS.createSaving(saving);
        } catch (Exception e) {
            System.out.println("Could not create savings.");
            throw e;
        }
    }

    @Override
    public void deposit(int employeeId, String accNum, double balance, String desc) throws BusinessException, DataLayerException {
        try {
            if (balance <= 0)
                throw new BusinessException("Deposit has to be positive");
            SavingDAO dao = new RDBSavingDAO(connection);
            dao.deposit(employeeId, accNum, balance, desc);
        } catch (Exception e) {
            System.out.println("Could not deposit.");
            throw e;
        }
    }

    @Override
    public void withdraw(int employeeId, String accNum, double balance, String desc) throws BusinessException, DataLayerException {
        try {
            if (balance <= 0)
                throw new BusinessException("Withdrawal has to be positive");
            SavingDAO dao = new RDBSavingDAO(connection);
            dao.withdraw(employeeId, accNum, balance, desc);
        } catch (Exception e) {
            System.out.println("Could not withdraw.");
            throw e;
        }
    }

    @Override
    public ArrayList<Saving> viewBalance(int customerId) throws DataLayerException {
        ArrayList<Saving> text = new ArrayList<>();
        try {
            SavingDAO dao = new RDBSavingDAO(connection);
            text.addAll(dao.getCustomerSavings(customerId));
        } catch (Exception e) {
            System.out.println("Could not view balance.");
            throw e;
        }
        return text;
    }

    @Override
    public void viewTransactionsHistory() throws DataLayerException {
        // TODO implement
        throw new DataLayerException();
    }

    @Override
    public double stats() throws DataLayerException {
        SavingDAO dao = new RDBSavingDAO(connection);
        return dao.stats();
    }
}
