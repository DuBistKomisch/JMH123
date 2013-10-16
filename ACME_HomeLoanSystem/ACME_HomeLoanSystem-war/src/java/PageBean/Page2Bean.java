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
@Named(value = "page2Bean")
@RequestScoped
public class Page2Bean {

    /**
     * Creates a new instance of Page2Bean
     */
    public Page2Bean() {
    }
    
    private String currentJob;
    private double salary;

    public String getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    
}
