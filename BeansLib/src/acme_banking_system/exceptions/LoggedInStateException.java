/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.exceptions;

/**
 *
 * @author Jake
 */
public class LoggedInStateException extends Exception {

    public LoggedInStateException(boolean loggedIn) {
        super(loggedIn ? "You are already logged in." : "You are not logged in.");
    }
}
