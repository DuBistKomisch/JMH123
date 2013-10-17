/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.beans;

import acme_banking_system.data_access.ACME_Transaction;
import acme_banking_system.data_access.Customer;
import acme_banking_system.data_access.CustomerDAO;
import acme_banking_system.data_access.Pending;
import acme_banking_system.data_access.PendingDAO;
import acme_banking_system.data_access.RDBCustomerDAO;
import acme_banking_system.data_access.RDBPendingDAO;
import acme_banking_system.data_access.RDBSavingDAO;
import acme_banking_system.data_access.Saving;
import acme_banking_system.data_access.SavingDAO;
import acme_banking_system.exceptions.DataLayerException;
import acme_homeloan.messenger.MessengerRemote;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author morga_000
 */
@Stateless
public class WebBean implements WebBeanRemote {

    @Resource(lookup = "jdbc/acmeDBDatasource")
    private DataSource dataSource;
    private Connection connection;

    @PostConstruct
    public void initialize() {
        try {
            connection = dataSource.getConnection("jmh123", "jmh123");
        } catch (SQLException sqle) {
            System.out.println("Error on creating resource");
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
    public double getBalance(Integer Id) {
        try {
            ArrayList<Saving> savings;
            double res = 0;
            SavingDAO dao = new RDBSavingDAO(connection);
            savings = dao.getCustomerSavings(Id);
            for (int i = 0; i < savings.size(); i++) {
                res += savings.get(i).getBalance();
            }
            return res;
        } catch (Exception e) {
            System.out.println("Could not read customer. --- " + e);
            return 0;
        }
    }
    
    @Override
    public Customer login(String firstName, String lastName) {
        try {
            Customer cust = null;
            CustomerDAO dao = new RDBCustomerDAO(connection);
            System.out.println("LOGIN ASKING DAO: " + dao);
            System.out.println(firstName);
            System.out.println(lastName);
            cust = dao.getCustomer(firstName, lastName);
            System.out.println(cust);
            return cust;
        } catch (Exception e) {
            System.out.println("Could not read customer. --- " + e);
            return null;
        }
    }
    
    @Override
    public boolean canCreateHomeLoan(Integer id) {
        ArrayList<Saving> saves;
        SavingDAO dao = new RDBSavingDAO(connection);
        try {
            saves = dao.getCustomerSavings(id);
            ArrayList<ACME_Transaction> tr = new ArrayList<ACME_Transaction>();
            int transactions = 0;
            for(Saving save: saves){
                tr.addAll(dao.getTransactionsHistory(save.getAccNum()));
            }
            System.out.println(tr);
            for (ACME_Transaction transaction : tr) {
                System.out.println(transaction);
                if (transaction.getAmount() > 0)
                    transactions++;
            }
            if (transactions >= 3)
                return true;
            return false;
        } catch (DataLayerException e) {
            return false;
        }
    }
    
    @Override
    public Integer isRepaymentPossible(double value, Integer c_id) {
        try {
            ArrayList<Saving> savings;
            double balance = 0;
            SavingDAO dao = new RDBSavingDAO(connection);
            PendingDAO pdao = new RDBPendingDAO(connection);
            savings = dao.getCustomerSavings(c_id);
            for (int i = 0; i < savings.size(); i++) {
               balance += savings.get(i).getBalance();
            }
            if (balance >= value) {
                Pending p = new Pending(c_id, value);
                pdao.createPending(p);
                return p.getCID();
            }
        } catch (Exception e) {
            System.out.println("Could not read customer. --- " + e);
            return -1;
        }
        return -1;
    }
    
    @Override
    public boolean performRepayment(Integer transaction, Integer c_id, boolean perform) {
        SavingDAO dao = new RDBSavingDAO(connection);
        PendingDAO pdao = new RDBPendingDAO(connection);
        ArrayList<Saving> savings;
        
        try {
            Pending pending = pdao.getPending(transaction);
            double toWithdraw = pending.getAmount();
            if (perform) {
                savings = dao.getCustomerSavings(c_id);
                for (Saving save : savings) {
                    if (save.getBalance() >= toWithdraw) {
                        dao.withdraw(0, save.getAccNum(), toWithdraw, "Web repayment");
                        pdao.deletePending(c_id);
                        return true;
                    } else {
                        toWithdraw -= save.getBalance();
                        dao.withdraw(0, save.getAccNum(), save.getBalance(), "Web repayment");
                    }
                }
                return true;
            } else {
                pdao.deletePending(transaction);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    
}
