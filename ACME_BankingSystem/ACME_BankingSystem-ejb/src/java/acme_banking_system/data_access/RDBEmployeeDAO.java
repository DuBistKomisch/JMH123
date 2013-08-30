package acme_banking_system.data_access;

import acme_banking_system.exceptions.BusinessException;
import acme_banking_system.exceptions.DataLayerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author morga_000
 */
public class RDBEmployeeDAO implements EmployeeDAO {

    private Connection dbConnection = null;

    public RDBEmployeeDAO(Connection connection) {
        this.dbConnection = connection;
    }

    @Override
    public void createEmployee(Employee employee) throws DataLayerException {
        try {
            // Inserts the employee
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "INSERT INTO JMH123.EMPLOYEES(E_FIRSTNAME, E_LASTNAME, E_PASSWORD)"
                    + " VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            sqlStatement.setString(1, employee.getFirstName());
            sqlStatement.setString(2, employee.getLastName());
            sqlStatement.setString(3, employee.getPassword());
            sqlStatement.executeUpdate();
            ResultSet result = sqlStatement.getGeneratedKeys();
            result.next();
            employee.setId(result.getInt(1));
            System.out.println("Employee has been created with id: " + employee.getId().toString());
        } catch (SQLException sqlException) {
            throw new DataLayerException();
        }
    }

    @Override
    public Employee readEmployee(int E_ID) throws DataLayerException {
        try {
            // Get the employee with an ID
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.EMPLOYEES WHERE E_ID = ?");
            sqlStatement.setInt(1, E_ID);
            ResultSet res = sqlStatement.executeQuery();
            res.next();
            return new Employee(E_ID, res.getString(2), res.getString(3), res.getString(4));
        } catch (SQLException sqlException) {
            throw new DataLayerException();
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws DataLayerException {
        try {
            // Update the employee
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "UPDATE JMH123.EMPLOYEES SET E_FIRSTNAME = ?, E_LASTNAME = ?, E_PASSWORD = ? WHERE E_ID = ?");
            sqlStatement.setString(1, employee.getFirstName());
            sqlStatement.setString(2, employee.getLastName());
            sqlStatement.setString(3, employee.getPassword());
            sqlStatement.setString(4, employee.getId().toString());
            sqlStatement.executeQuery();
        } catch (SQLException sqlException) {
            throw new DataLayerException();
        }
    }

    @Override
    public void deleteEmployee(int employeeId) throws DataLayerException {
        try {
            // Delete the employee
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "DELETE FROM JMH123.EMPLOYEES WHERE C_ID = ?");
            sqlStatement.setInt(1, employeeId);
            sqlStatement.executeQuery();
        } catch (SQLException sqlException) {
            throw new DataLayerException();
        }
    }

    @Override
    public Employee loginEmployee(String E_FIRSTNAME, String E_LASTNAME, String PASSWORD) throws BusinessException, DataLayerException {
        try {
            // Getting the employee using his full name and apssword
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.EMPLOYEES WHERE E_FIRSTNAME = ? AND E_LASTNAME = ? AND E_PASSWORD = ?");
            sqlStatement.setString(1, E_FIRSTNAME);
            sqlStatement.setString(2, E_LASTNAME);
            sqlStatement.setString(3, PASSWORD);
            ResultSet res = sqlStatement.executeQuery();
            if (res.next()) {
                return new Employee(res.getInt(1));
            } else {
                throw new BusinessException("Wrong employee name and password.");
            }
        } catch (SQLException sqlException) {
            throw new DataLayerException();
        }
    }
}