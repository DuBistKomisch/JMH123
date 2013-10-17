/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.data_access;

import java.io.Serializable;

/**
 *
 * @author morga_000
 */
public class Asking implements Serializable {
    private String method;
    private String firstName;
    private String lastName;
    private Integer id;
    private Integer transaction;
    private double value;
    private boolean perform;
    
    public void setPerform(boolean perform) {
        this.perform = perform;
    }
    
    public boolean getPerform() {
        return this.perform;
    }
    
    public void setMessage(String method) {
        this.method = method;
    }
    
    public String getMessage() {
        return this.method;
    }

    public void setValue(double val) {
        this.value = val;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public void setFirstName(String fn) {
        this.firstName = fn;
    }
    
    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String ln) {
        this.lastName = ln;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return this.id;
    }

    public void setTransaction(Integer id) {
        this.transaction = id;
    }
    
    public Integer getTransaction() {
        return this.transaction;
    }
}
