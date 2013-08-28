/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Howard Tseng
 */
public class DataLayerException extends Exception {
    public DataLayerException ()
    {
        super("Database error (see server log).");
    }
}
