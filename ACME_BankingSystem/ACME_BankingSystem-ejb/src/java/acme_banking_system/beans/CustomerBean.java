package acme_banking_system.beans;

import acme_banking_system.data_access.Customer;
import acme_banking_system.data_access.CustomerDAO;
import acme_banking_system.data_access.RDBCustomerDAO;
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
    public void createCustomer(String firstname, String lastname, Date dob, String address) throws DataLayerException {
        try {
            java.sql.Date sqlDob = new java.sql.Date(dob.getTime());
            CustomerDAO dao = new RDBCustomerDAO(connection);
            Customer customer = new Customer(firstname, lastname, sqlDob, address);
            dao.createCustomer(customer);
        } catch (Exception e) {
            System.out.println("Could not create customer.");
            throw e;
        }
    }

    @Override
    public void readCustomer(Integer C_ID) throws DataLayerException {
        Customer text;
        try {
            CustomerDAO dao = new RDBCustomerDAO(connection);
            text = dao.readCustomer(C_ID);
            System.out.println(text.getC_ID().toString() + text.getFirstName().toString()
                    + text.getLastName().toString() + text.getDateOfBirth().toString()
                    + text.getAddress().toString());
        } catch (Exception e) {
            System.out.println("Could not read customer.");
            throw e;
        }
    }

    @Override
    public void updateCustomer(String firstname, String lastname, Date dob, String address, Integer C_ID) throws DataLayerException {
        try {
            CustomerDAO dao = new RDBCustomerDAO(connection);
            Customer customer = new Customer(firstname, lastname, (java.sql.Date) dob, address, C_ID);
            dao.updateCustomer(customer);
        } catch (Exception e) {
            System.out.println("Could not update customer.");
            throw e;
        }
    }

    @Override
    public void deleteCustomer(Integer C_ID) throws DataLayerException {
        try {
            CustomerDAO dao = new RDBCustomerDAO(connection);
            Customer customer = new Customer(C_ID);
            dao.deleteCustomer(customer);
        } catch (Exception e) {
            System.out.println("Could not delete customer.");
            throw e;
        }
    }
}
