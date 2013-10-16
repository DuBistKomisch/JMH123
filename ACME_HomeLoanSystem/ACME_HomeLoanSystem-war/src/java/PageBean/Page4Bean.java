/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PageBean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Howard Tseng
 */
@Named(value = "page4Bean")
@RequestScoped
public class Page4Bean {

    /**
     * Creates a new instance of Page4Bean
     */
    public Page4Bean() {
    }
    
    private double loan;

    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }
    
}
