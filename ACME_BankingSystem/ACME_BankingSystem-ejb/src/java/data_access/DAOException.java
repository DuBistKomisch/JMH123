/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_access;

/**
 *
 * @author morga_000
 */
public class DAOException extends Exception {
    
    public DAOException() {
    }
    
    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
    
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}