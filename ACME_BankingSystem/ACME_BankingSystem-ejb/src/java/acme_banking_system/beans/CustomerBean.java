package acme_banking_system.beans;

import acme_banking_system.data_access.Customer;
import acme_banking_system.data_access.CustomerDAO;
import acme_banking_system.data_access.Customer;
import acme_banking_system.data_access.RDBCustomerDAO;
import acme_banking_system.exceptions.BusinessException;
import acme_banking_system.exceptions.DataLayerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import javax.sql.DataSource;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;

/**
 *
 * @author Howard Tseng
 */
@Stateless
public class CustomerBean implements CustomerBeanRemote {

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
    public void createCustomer(String firstName, String lastName, Date dateOfBirth, String address) throws DataLayerException {
        try {
            java.sql.Date sqlDob = new java.sql.Date(dateOfBirth.getTime());
            CustomerDAO dao = new RDBCustomerDAO(connection);
            Customer customer = new Customer(firstName, lastName, sqlDob, address);
            dao.createCustomer(customer);
        } catch (Exception e) {
            System.out.println("Could not create customer.");
            throw e;
        }
    }

    @Override
    public Customer readCustomer(int customerId) throws BusinessException, DataLayerException {
        Customer text;
        try {
            CustomerDAO dao = new RDBCustomerDAO(connection);
            return dao.readCustomer(customerId);
        } catch (Exception e) {
            System.out.println("Could not read customer.");
            throw e;
        }
    }

    @Override
    public void updateCustomer(int customerId, String firstName, String lastName, Date dateOfBirth, String address) throws DataLayerException {
        try {
            CustomerDAO dao = new RDBCustomerDAO(connection);
            Customer customer = new Customer(customerId, firstName, lastName, (java.sql.Date) dateOfBirth, address);
            dao.updateCustomer(customer);
        } catch (Exception e) {
            System.out.println("Could not update customer.");
            throw e;
        }
    }

    @Override
    public void deleteCustomer(int customerId) throws DataLayerException {
        try {
            CustomerDAO dao = new RDBCustomerDAO(connection);
            dao.deleteCustomer(customerId);
        } catch (Exception e) {
            System.out.println("Could not delete customer.");
            throw e;
        }
    }
}
