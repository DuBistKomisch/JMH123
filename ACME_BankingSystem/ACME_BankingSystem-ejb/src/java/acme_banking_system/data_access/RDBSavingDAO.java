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

    @Override
    public void createSaving(Saving save) throws BusinessException, DataLayerException {
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.SAVINGS WHERE C_ID = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            sqlStatement.setInt(1, save.getC_ID());
            ResultSet res = sqlStatement.executeQuery();
            if (res.last() && res.getRow() >= 2) {
                throw new BusinessException("Impossible to add a new account to this customer.");
            }
            sqlStatement = dbConnection.prepareStatement(
                    "INSERT INTO JMH123.SAVINGS (C_ID, ACCNUM, BALANCE)"
                    + " VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            sqlStatement.setInt(1, save.getC_ID());
            //sqlStatement.setString(2, customer.getLastName().substring(0, 3).toUpperCase() + customer.getC_ID().toString());
            sqlStatement.setString(2, save.getACCNUM());
            sqlStatement.setDouble(3, save.getBALANCE());
            sqlStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataLayerException();
        }
    }

    @Override
    public void deleteSaving(Saving saving) {
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "DELETE FROM JMH123.SAVINGS WHERE ACCNUM = ?");
            sqlStatement.executeQuery();
        } catch (SQLException sqle) {
            System.out.print("Impossible to delete this account");
            sqle.printStackTrace();
        }
    }

    @Override
    public ArrayList<Saving> getCustomerSavings(Saving saving) {
        ArrayList<Saving> res = new ArrayList<>();
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.SAVINGS WHERE C_ID = ?");
            sqlStatement.setInt(1, saving.getC_ID());
            ResultSet result = sqlStatement.executeQuery();
            while (result.next()) {
                Saving acc = new Saving();
                acc.setC_ID(saving.getC_ID());
                acc.setACCNUM(result.getString("ACCNUM"));
                acc.setBALANCE(result.getInt("BALANCE"));
                acc.setCREATIONTIME(result.getDate("S_DATETIMECREATED"));
                res.add(acc);
            }
        } catch (SQLException sqle) {
            System.out.println("Impossible to retrieve any account for this customer");
            sqle.printStackTrace();
            res = null;
        }

        return res;
    }

    @Override
    public void withdraw(Employee emp, Saving acc, double amount, String desc)
            throws Exception {
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.SAVINGS WHERE ACCNUM = ?");
            sqlStatement.setString(1, acc.getACCNUM());
            ResultSet result = sqlStatement.executeQuery();
            if (result.next()) {
                Integer balance = result.getInt("BALANCE");
                if (balance >= amount) {
                    PreparedStatement transaction = dbConnection.prepareStatement(
                            "INSERT INTO JMH123.TRANSACTIONS(ACCNUM, AMOUNT, DESCRIPTION, ACCNUM2, E_ID)"
                            + " VALUES (?, ?, ?, ?, ?)");
                    transaction.setString(1, acc.getACCNUM());
                    transaction.setDouble(2, -(amount));
                    transaction.setString(3, desc);
                    transaction.setString(4, "");
                    transaction.setInt(5, emp.getE_ID());
                    result = transaction.executeQuery();
                    if (result.next()) {
                        sqlStatement = dbConnection.prepareStatement(
                                "UPDATE JMH123.SAVINGS SET BALANCE = BALANCE - ? WHERE ACCNUM = ?");
                        sqlStatement.setDouble(1, amount);
                        sqlStatement.setString(2, acc.getACCNUM());
                        sqlStatement.executeQuery();
                    }
                } else {
                    throw new Exception("Not enough balance");
                }
            } else {
                throw new Exception("Impossible to make this withdraw");
            }
        } catch (SQLException sqle) {
            System.out.println("Impossible to withdraw from this account");
            sqle.printStackTrace();
            throw new Exception("Sql error");
        }
    }

    @Override
    public void deposit(Employee emp, Saving acc, double amount, String desc)
            throws Exception {
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.SAVINGS WHERE ACCNUM = ?");
            sqlStatement.setString(1, acc.getACCNUM());
            ResultSet result = sqlStatement.executeQuery();
            if (result.next()) {
                PreparedStatement transaction = dbConnection.prepareStatement(
                        "INSERT INTO JMH123.TRANSACTIONS(ACCNUM, AMOUNT, DESCRIPTION, ACCNUM2, E_ID)"
                        + " VALUES (?, ?, ?, ?, ?)");
                transaction.setString(1, acc.getACCNUM());
                transaction.setDouble(2, amount);
                transaction.setString(3, desc);
                transaction.setString(4, "");
                transaction.setInt(5, emp.getE_ID());
                result = transaction.executeQuery();
                if (result.next()) {
                    sqlStatement = dbConnection.prepareStatement(
                            "UPDATE JMH123.SAVINGS SET BALANCE = BALANCE + ? WHERE ACCNUM = ?");
                    sqlStatement.setDouble(1, amount);
                    sqlStatement.setString(2, acc.getACCNUM());
                    sqlStatement.executeQuery();
                }
            } else {
                throw new Exception("Impossible to make this deposit");
            }
        } catch (SQLException sqle) {
            System.out.println("Impossible to withdraw from this account");
            sqle.printStackTrace();
            throw new Exception("Sql error");
        }
    }
    
    @Override
    public ArrayList<Saving>    getTransactionsHistory(Saving saving) {
        ArrayList<Saving>   res = new ArrayList<>();
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                "SELECT * FROM JMH123.Transactions WHERE ACCNUM = ?");
            sqlStatement.setString(1, saving.getACCNUM());
            ResultSet result = sqlStatement.executeQuery();
            while (result.next()) {
                Saving acc = new Saving();
                //acc.setC_ID(saving.getC_ID());
                acc.setACCNUM(result.getString("ACCNUM"));
                acc.setBALANCE(result.getInt("BALANCE"));
                acc.setCREATIONTIME(result.getDate("S_DATETIMECREATED"));
                acc.setDesc("Description");
                res.add(acc);
            }
        } catch (SQLException sqle) {
            System.out.println("Impossible to retrieve any Transaction History for this customer");
            sqle.printStackTrace();
            res = null;
        }
        
        return res;
    }
}
