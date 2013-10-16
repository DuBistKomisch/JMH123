/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PageBean;

import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;

/**
 *
 * @author Howard Tseng
 */
@Named(value = "page1Bean")
@ConversationScoped
public class Page1Bean {

    /**
     * Creates a new instance of Page1Bean
     */
    public Page1Bean() {
    }
    
    private String firstName;
    private String lastName;
    private Date DOB ;
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
