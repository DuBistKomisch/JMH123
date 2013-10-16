/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PageBean;

import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Howard Tseng
 */
@Named(value = "page1Bean")
@RequestScoped
public class Page1Bean {

    /**
     * Creates a new instance of Page1Bean
     */
    public Page1Bean() {
    }
    
    private String firstName;
    private String lastName;
    private Date DOB;
    private String addrss;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getAddrss() {
        return addrss;
    }

    public void setAddrss(String addrss) {
        this.addrss = addrss;
    }
}
