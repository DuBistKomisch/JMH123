/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DepositBean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Howard Tseng
 */
@Named(value = "depositBean")
@RequestScoped
public class DepositBean {

    /**
     * Creates a new instance of DepositBean
     */
    public DepositBean() {
    }
    
    private double dAmount;

    public double getdAmount() {
        return dAmount;
    }

    public void setdAmount(double dAmount) {
        this.dAmount = dAmount;
    }
    
}
