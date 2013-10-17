/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class RDBPendingDAO implements PendingDAO {
    private Connection dbConnection = null;

    public RDBPendingDAO(Connection connection) {
        this.dbConnection = connection;
    }
    
    @Override
    public void createPending(Pending pending) throws DataLayerException, BusinessException {
        try {
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.PENDINGTRANSACTIONS WHERE P_ID = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            sqlStatement.setInt(1, pending.getPID());
            ResultSet res = sqlStatement.executeQuery();
            // Check for unicity
            if (res.last()) {
                throw new BusinessException("This pending transaction already exists");
            }
            //Creating prepare statement
            sqlStatement = dbConnection.prepareStatement(
                    "INSERT INTO JMH123.PENDINGTRANSACTIONS (C_ID, Amount)"
                    + " VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            sqlStatement.setInt(1, pending.getCID());
            sqlStatement.setDouble(2, pending.getAmount());
            sqlStatement.executeUpdate();
            ResultSet result = sqlStatement.getGeneratedKeys();
            result.next();
            pending.setPID(result.getInt(1));
        } catch (SQLException ex) {
            throw new DataLayerException();
        }
    }
    
    @Override
    public void deletePending(int p_id) throws DataLayerException {
        try {
            //Deleting pending transaction
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "DELETE FROM JMH123.PENDINGTRANSACTIONS WHERE P_ID = ?");
            sqlStatement.setInt(1, p_id);
            sqlStatement.executeQuery();
        } catch (SQLException sqle) {
            throw new DataLayerException();
        }
    }

    @Override
    public Pending getPending(int p_id) throws DataLayerException {
        Pending pending;
        try {
            // Getting the pending transaction
            PreparedStatement sqlStatement = dbConnection.prepareStatement(
                    "SELECT * FROM JMH123.PENDINGTRANSACTIONS WHERE P_ID = ?");
            sqlStatement.setInt(1, p_id);
            ResultSet result = sqlStatement.executeQuery();
            // Creating the pending object
            pending = new Pending(result.getInt(1), result.getInt(2), result.getDouble(3));
            return pending;
        } catch (SQLException sqle) {
            throw new DataLayerException();
        }
    }
}
