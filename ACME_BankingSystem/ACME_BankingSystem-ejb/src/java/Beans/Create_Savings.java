package Beans;

import data_access.RDBSavingDAO;
import data_access.Saving;
import data_access.SavingDAO;
import exceptions.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author Howard Tseng
 */
@Stateless
public class Create_Savings implements Create_SavingsRemote {

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
    public void createSaving(Integer C_ID, String ACCNUM) throws BusinessException, DataLayerException {
        try {
            SavingDAO daoS = new RDBSavingDAO(connection);
            Saving saving = new Saving(C_ID, ACCNUM);
            daoS.createSaving(saving);
        } catch (Exception e) {
            System.out.println("Could not create savings.");
            throw e;
        }
    }
}
