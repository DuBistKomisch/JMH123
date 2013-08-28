/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Jake
 */
public class LoggedInStateException extends Exception {
    public LoggedInStateException(String message) {
        super(message);
    }
}
