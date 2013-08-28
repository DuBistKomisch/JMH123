package acme_banking_system.beans;

import acme_banking_system.data_access.Customer;
import acme_banking_system.data_access.CustomerDAO;
import acme_banking_system.data_access.RDBCustomerDAO;
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
public class Create_Customer implements Create_CustomerRemote {

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
    public void addCustomer(String firstname, String lastname, Date dob, String address) {
        try {
            java.sql.Date sqlDob = new java.sql.Date(dob.getTime());
            CustomerDAO dao = new RDBCustomerDAO(connection);
            Customer customer = new Customer(firstname, lastname, sqlDob, address);
            dao.createCustomer(customer);
        } catch (Exception e) {
            System.out.println("Could not create customer.");
            e.printStackTrace();
        }
    }
}
