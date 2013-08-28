/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import data_access.Employee;
import data_access.EmployeeDAO;
import data_access.RDBEmployeeDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;
import javax.sql.DataSource;

/**
 *
 * @author morga_000
 */

@Stateful
public class EmployeeSession implements EmployeeSessionRemote {
    private Boolean logged = false;
    private Integer actions = 0;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
@Resource(lookup = "jdbc/acmeDBDatasource")
    private DataSource dataSource;
    
    private Connection connection;
    
    @PostActivate
    @PostConstruct
    public void initialize() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    @PrePassivate
    @PreDestroy
    public void close() {
        try {
            connection.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    @Override
    public void login(String firstname, String lastName, String password) {
        Employee emp = new Employee();
        EmployeeDAO doa = new RDBEmployeeDAO(connection);
        emp = doa.loginEmployee(firstname, lastName, password);
        if (emp != null)
            this.logged = true;
        else
            this.logged = false;
        this.actions = 0;
    }
    
    @Override
    public Boolean addAction() {
        if (this.actions < 10) {
            this.actions++;
            return true;
        } else {
            this.actions = 0;
            this.logged = false;
            return false;
        }
    }
    
    @Override
    public Integer  getCounter() {
        return this.actions;
    }
}
