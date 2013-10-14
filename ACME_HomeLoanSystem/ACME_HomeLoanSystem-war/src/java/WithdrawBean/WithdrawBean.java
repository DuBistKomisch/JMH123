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
    private double withdraw;

    public double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(double withdraw) {
        this.withdraw = withdraw;
    }
    
    
}
