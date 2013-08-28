package acme_banking_system.data_access;

/**
 *
 * @author morga_000
 */
import acme_banking_system.exceptions.BusinessException;
import acme_banking_system.exceptions.DataLayerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RDBCustomerDAO implements CustomerDAO {

    private Connection dbConnection = null;

    public RDBCustomerDAO(Connection connection) {
        this.dbConnection = connection;
    }

    @Override
    public void createCustomer(Customer customer) throws DataLayerException {
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "INSERT INTO JMH123.CUSTOMERS(C_FIRSTNAME, C_LASTNAME, DOB, ADDRESS)"
                    + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            sqlStatement.setString(1, customer.getFirstName());
            sqlStatement.setString(2, customer.getLastName());
            sqlStatement.setDate(3, customer.getDateOfBirth());
            sqlStatement.setString(4, customer.getAddress());
            sqlStatement.executeUpdate();
            ResultSet result = sqlStatement.getGeneratedKeys();
            result.next();
            customer.setId(result.getInt(1));
        } catch (SQLException sqlException) {
            throw new DataLayerException();
        }
    }

    @Override
    public Customer readCustomer(int customerId) throws BusinessException, DataLayerException {
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.CUSTOMERS WHERE C_ID = ?", Statement.RETURN_GENERATED_KEYS);
            sqlStatement.setInt(1, customerId);
            ResultSet res = sqlStatement.executeQuery();
            if (res.next()) {
                return new Customer(res.getInt(1), res.getString(2), res.getString(3), res.getDate(4), res.getString(5));
            } else {
                throw new BusinessException("Customer doesn't exist.");
            }
        } catch (SQLException sqlException) {
            throw new DataLayerException();
        }
    }

    @Override
    public void updateCustomer(Customer customer) throws DataLayerException {
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "UPDATE JMH123.CUSTOMERS SET C_FIRSTNAME = ?, C_LASTNAME = ?, DOB = ?, ADDRESS = ? WHERE C_ID = ?");
            sqlStatement.setString(1, customer.getFirstName());
            sqlStatement.setString(2, customer.getLastName());
            sqlStatement.setDate(3, customer.getDateOfBirth());
            sqlStatement.setString(4, customer.getAddress());
            sqlStatement.setString(5, customer.getId().toString());
        } catch (SQLException sqlException) {
            throw new DataLayerException();
        }
    }

    @Override
    public void deleteCustomer(int customerId) throws DataLayerException {
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "DELETE FROM JMH123.CUSTOMERS WHERE C_ID = ?");
            sqlStatement.setInt(1, customerId);
        } catch (SQLException sqlException) {
            throw new DataLayerException();
        }
    }

    @Override
    public ArrayList<Customer> getAllCustomer() throws DataLayerException {
        ArrayList<Customer> result = new ArrayList<>();

        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.CUSTOMERS", Statement.RETURN_GENERATED_KEYS);
            ResultSet res = sqlStatement.executeQuery();
            while (res.next()) {
                result.add(new Customer(res.getInt(1), res.getString(2), res.getString(3), res.getDate(4), res.getString(5)));
            }
        } catch (SQLException sqlException) {
            throw new DataLayerException();
        }

        return result;
    }
}
