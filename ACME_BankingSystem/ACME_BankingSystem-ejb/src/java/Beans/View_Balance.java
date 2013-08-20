/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.sql.Connection;
import java.sql.SQLException;
import javax.activation.DataSource;
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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

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
    
    
}
