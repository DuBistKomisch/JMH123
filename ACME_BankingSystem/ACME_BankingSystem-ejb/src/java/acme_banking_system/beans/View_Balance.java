package acme_banking_system.beans;

import acme_banking_system.data_access.ISaving;
import acme_banking_system.data_access.RDBSavingDAO;
import acme_banking_system.data_access.Saving;
import acme_banking_system.data_access.SavingDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class View_Balance implements View_BalanceRemote {

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
    public ArrayList<ISaving> ViewBalance(Integer C_ID) {
        ArrayList<ISaving> text = new ArrayList<ISaving>();
        try {
            SavingDAO dao = new RDBSavingDAO(connection);
            Saving saving = new Saving(C_ID);
            text.addAll(dao.getUserAccount(saving));

        } catch (Exception e) {
            System.out.println("Could not create customer.");
            e.printStackTrace();
            text = null;
        }
        return text;
    }
}
