package Beans;

import data_access.Customer;
import data_access.CustomerDAO;
import data_access.RDBCustomerDAO;
import java.sql.Connection;
import java.sql.SQLException;
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
public class Delete_Customer implements Delete_CustomerRemote {

    @Resource(lookup = "jdbc/acmeDBDatasource")
    private DataSource dataSource;
    private Connection connection;
    private Customer customer;

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
    public void removeCustomer(Integer C_ID) {
        try {
            CustomerDAO dao = new RDBCustomerDAO(connection);
            Customer customer = new Customer(C_ID);
            dao.deleteCustomer(customer);
        } catch (Exception e) {
            System.out.println("Could not delete customer.");
            e.printStackTrace();
        }
    }
}
