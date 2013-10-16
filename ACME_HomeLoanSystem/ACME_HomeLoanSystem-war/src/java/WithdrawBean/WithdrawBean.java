/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WithdrawBean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Howard Tseng
 */
@Named(value = "withdrawBean")
@RequestScoped
public class WithdrawBean {

    /**
     * Creates a new instance of WithdrawBean
     */
    public WithdrawBean() {
    }
    
    private double amountRepayed;
    private double amountBorrowed;

    public double getAmountRepayed() {
        return amountRepayed;
    }

    public void setAmountRepayed(double amountRepayed) {
        this.amountRepayed = amountRepayed;
    }

    public double getAmountBorrowed() {
        return amountBorrowed;
    }

    public void setAmountBorrowed(double amountBorrowed) {
        this.amountBorrowed = amountBorrowed;
    }
    
 
    
    
}
