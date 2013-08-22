/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_access;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author morga_000
 */
public class RDBEmployeeDAO implements EmployeeDAO{
        private Connection dbConnection = null;
    
    public RDBEmployeeDAO(Connection connection) {
        this.dbConnection = connection;
    }    

    @Override
    public void createEmployee(Employee employee) {
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "INSERT INTO JMH123.EMPLOYEES(E_FIRSTNAME, E_LASTNAME, E_PASSWORD)"
                    + " VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            sqlStatement.setString(1, employee.getE_FIRSTNAME());
            sqlStatement.setString(2, employee.getE_LASTNAME());
            sqlStatement.setString(3, employee.getE_PASSWORD());
            sqlStatement.executeUpdate();
            ResultSet result = sqlStatement.getGeneratedKeys();
            result.next();
            employee.setE_ID(result.getInt(1));
            System.out.print("Employee has been created with id: " + employee.getE_ID().toString());
        } catch (SQLException sqlException) {
            System.out.println("Could not add new employee.");
            sqlException.printStackTrace();
        }
    }
    
    @Override
    public Employee readEmployee(Integer E_ID){
        Employee    result = new Employee();

        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.EMPLOYEES WHERE C_ID = ?");
            sqlStatement.setString(1, E_ID.toString());
            ResultSet res = sqlStatement.executeQuery();
            res.next();
            result.setE_FIRSTNAME(res.getString(2));
            result.setE_LASTNAME(res.getString(3));
            result.setE_PASSWORD(res.getString(4));
        } catch (SQLException sqlException) {
            System.out.println("Could not retrieve emplyee.");
            sqlException.printStackTrace();
            result = null;
        }
        
        return result;
    }

    @Override
    public void updateEmployee(Employee employee) {
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "UPDATE JMH123.EMPLOYEES SET E_FIRSTNAME = ?, E_LASTNAME = ?, E_PASSWORD = ? WHERE E_ID = ?");
            sqlStatement.setString(1, employee.getE_FIRSTNAME());
            sqlStatement.setString(2, employee.getE_LASTNAME());
            sqlStatement.setString(3, employee.getE_PASSWORD());
            sqlStatement.setString(4, employee.getE_ID().toString());
            sqlStatement.executeQuery();
        } catch (SQLException sqlException) {
            System.out.println("Could not update employee.");
            sqlException.printStackTrace();
        }
    }
    
    @Override
    public void deleteEmployee(Employee employee) {
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "DELETE FROM JMH123.EMPLOYEES WHERE C_ID = ?");
            sqlStatement.setString(1, employee.getE_ID().toString());
            sqlStatement.executeQuery();
        } catch (SQLException sqlException) {
            System.out.println("Could not delete employee.");
            sqlException.printStackTrace();
        }
    }
    
    @Override
    public Employee loginEmployee(String E_FIRSTNAME, String E_LASTNAME, String PASSWORD) {
        Employee    result = new Employee();
        
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.EMPLOYEES WHERE E_FIRSTNAME = ? AND E_LASTNAME = ? AND E_PASSWORD = ?");
            sqlStatement.setString(1, E_FIRSTNAME);
            sqlStatement.setString(2, E_LASTNAME);
            sqlStatement.setString(3, PASSWORD);
            ResultSet res = sqlStatement.executeQuery();
            if (res.next()) {
                result.setE_FIRSTNAME(E_FIRSTNAME);
                result.setE_LASTNAME(E_LASTNAME);
                result.setE_PASSWORD(PASSWORD);
                result.setE_ID(res.getInt("E_ID"));
            } else {
                result = null;
            }
        } catch (SQLException sqlException) {
           System.out.println("Could not log employee.");
           sqlException.printStackTrace();
        }
        return result;
    }
}