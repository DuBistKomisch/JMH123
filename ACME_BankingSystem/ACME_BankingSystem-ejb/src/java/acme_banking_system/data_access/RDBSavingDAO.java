package acme_banking_system.data_access;

import acme_banking_system.exceptions.BusinessException;
import acme_banking_system.exceptions.DataLayerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author morga_000
 */
public class RDBSavingDAO implements SavingDAO {

    private Connection dbConnection = null;

    public RDBSavingDAO(Connection connection) {
        this.dbConnection = connection;
    }

    
    // Creates a saving account checking if the are no more than 2 accounts.
    @Override
    public void createSaving(Saving save) throws BusinessException, DataLayerException {
        try {
            // TODO needs more validation
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.SAVINGS WHERE C_ID = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            sqlStatement.setInt(1, save.getId());
            ResultSet res = sqlStatement.executeQuery();
            // Got the accounts for the users, check if the user already has two accounts
            if (res.last() && res.getRow() >= 2) {
                throw new BusinessException("Impossible to add a new account to this customer.");
            }
            //Creates saving acocunt
            sqlStatement = dbConnection.prepareStatement(
                    "INSERT INTO JMH123.SAVINGS (C_ID, ACCNUM, BALANCE)"
                    + " VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            sqlStatement.setInt(1, save.getId());
            sqlStatement.setString(2, save.getAccNum());
            sqlStatement.setDouble(3, save.getBalance());
            sqlStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataLayerException();
        }
    }

    @Override
    public void deleteSaving(String accNum) throws DataLayerException {
        try {
            //Deleting saving account
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "DELETE FROM JMH123.SAVINGS WHERE ACCNUM = ?");
            sqlStatement.setString(1, accNum);
            sqlStatement.executeQuery();
        } catch (SQLException sqle) {
            throw new DataLayerException();
        }
    }

    @Override
    public void deposit(int employeeId, String accNum, double amount, String desc) throws BusinessException, DataLayerException {
        try {
            //Getting the account using the account number
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.SAVINGS WHERE ACCNUM = ?");
            sqlStatement.setString(1, accNum);
            ResultSet result = sqlStatement.executeQuery();
            if (result.next()) {
                // Insert the new transaction
                PreparedStatement transaction = dbConnection.prepareStatement(
                        "INSERT INTO JMH123.TRANSACTIONS(ACCNUM, AMOUNT, DESCRIPTION, ACCNUM2, E_ID)"
                        + " VALUES (?, ?, ?, ?, ?)");
                transaction.setString(1, accNum);
                transaction.setDouble(2, amount);
                transaction.setString(3, desc);
                transaction.setString(4, "");
                transaction.setInt(5, employeeId);
                transaction.executeUpdate();

                // Update the balance
                sqlStatement = dbConnection.prepareStatement(
                        "UPDATE JMH123.SAVINGS SET BALANCE = BALANCE + ? WHERE ACCNUM = ?");
                sqlStatement.setDouble(1, amount);
                sqlStatement.setString(2, accNum);
                sqlStatement.executeUpdate();
            } else {
                throw new BusinessException("Account not found.");
            }
        } catch (SQLException sqle) {
            throw new DataLayerException();
        }
    }

    @Override
    public void withdraw(int employeeId, String accNum, double amount, String desc) throws BusinessException, DataLayerException {
        try {
            //Getting the account from the user
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.SAVINGS WHERE ACCNUM = ?");
            sqlStatement.setString(1, accNum);
            ResultSet result = sqlStatement.executeQuery();
            if (result.next()) {
                // Insert the new transaction
                int balance = result.getInt("BALANCE");
                if (balance >= amount) {
                    PreparedStatement transaction = dbConnection.prepareStatement(
                            "INSERT INTO JMH123.TRANSACTIONS(ACCNUM, AMOUNT, DESCRIPTION, ACCNUM2, E_ID)"
                            + " VALUES (?, ?, ?, ?, ?)");
                    transaction.setString(1, accNum);
                    transaction.setDouble(2, -(amount));
                    transaction.setString(3, desc);
                    transaction.setString(4, "");
                    transaction.setInt(5, employeeId);
                    transaction.executeUpdate();
                    
                    // Update the balance
                    sqlStatement = dbConnection.prepareStatement(
                            "UPDATE JMH123.SAVINGS SET BALANCE = BALANCE - ? WHERE ACCNUM = ?");
                    sqlStatement.setDouble(1, amount);
                    sqlStatement.setString(2, accNum);
                    sqlStatement.executeUpdate();
                } else {
                    throw new BusinessException("Not enough balance.");
                }
            } else {
                throw new BusinessException("Account not found.");
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            throw new DataLayerException();
        }
    }

    @Override
    public ArrayList<Saving> getCustomerSavings(int customerId) throws DataLayerException {
        ArrayList<Saving> res = new ArrayList<>();
        try {
            // Getting all the saving accounts
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.SAVINGS WHERE C_ID = ?");
            sqlStatement.setInt(1, customerId);
            ResultSet result = sqlStatement.executeQuery();
            // Filling the array with accounts
            while (result.next()) {
                res.add(new Saving(result.getInt(1), result.getString(2), result.getDouble(3), result.getDate(4)));
            }
        } catch (SQLException sqle) {
            throw new DataLayerException();
        }

        return res;
    }

    @Override
    public ArrayList<ACME_Transaction> getTransactionsHistory(String accNum) throws DataLayerException {
        ArrayList<ACME_Transaction> res = new ArrayList<>();
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.TRANSACTIONS WHERE ACCNUM = ?");
            sqlStatement.setString(1, accNum);
            ResultSet result = sqlStatement.executeQuery();
            while (result.next()) {
                res.add(new ACME_Transaction(result.getInt(2), result.getInt(3), result.getInt(4), result.getString(5)));
            }
        } catch (SQLException sqle) {
            throw new DataLayerException();
        }
        return res;
    }

    @Override
    public double stats() throws DataLayerException {
        double res = 0.0;
        try {
            // Getting all the saving accounts
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT SUM(BALANCE) AS TOTAL FROM JMH123.SAVINGS");
            ResultSet result = sqlStatement.executeQuery();
            // Filling the array with accounts
            while (result.next()) {
                res = result.getDouble(1);
            }
        } catch (SQLException sqle) {
            throw new DataLayerException();
        }
        return res;
    }
}
