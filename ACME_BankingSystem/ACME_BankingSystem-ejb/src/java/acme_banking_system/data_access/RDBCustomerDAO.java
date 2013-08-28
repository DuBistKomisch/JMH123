package acme_banking_system.data_access;

/**
 *
 * @author morga_000
 */
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
            customer.setC_ID(result.getInt(1));
            System.out.print("Customer has been created with id: " + customer.getC_ID().toString());
        } catch (SQLException sqlException) {
            throw new DataLayerException();
        }
    }

    @Override
    public Customer readCustomer(Integer C_ID) throws DataLayerException {
        Customer result = new Customer(null, null, null, null);

        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.CUSTOMERS WHERE C_ID = ?", Statement.RETURN_GENERATED_KEYS);
            sqlStatement.setString(1, C_ID.toString());
            ResultSet res = sqlStatement.executeQuery();
            if (res.next()) {
                result.setFirstName(res.getString(2));
                result.setLastName(res.getString(3));
                result.setDateOfBirth(res.getDate(4));
                result.setAddress(res.getString(5));
            }
        } catch (SQLException sqlException) {
            throw new DataLayerException();
        }

        return result;
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
            sqlStatement.setString(5, customer.getC_ID().toString());
        } catch (SQLException sqlException) {
            throw new DataLayerException();
        }
    }

    @Override
    public void deleteCustomer(Customer customer) throws DataLayerException {
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "DELETE FROM JMH123.CUSTOMERS WHERE C_ID = ?");
            sqlStatement.setString(1, customer.getC_ID().toString());
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
                Customer cust = new Customer(null, null, null, null);

                cust.setFirstName(res.getString(2));
                cust.setLastName(res.getString(3));
                cust.setDateOfBirth(res.getDate(4));
                cust.setAddress(res.getString(5));
                result.add(cust);
            }
        } catch (SQLException sqlException) {
            throw new DataLayerException();
        }

        return result;
    }
}
