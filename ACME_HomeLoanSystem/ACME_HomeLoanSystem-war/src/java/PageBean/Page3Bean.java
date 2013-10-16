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
@Named(value = "page3Bean")
@RequestScoped
public class Page3Bean {

    /**
     * Creates a new instance of Page3Bean
     */
    public Page3Bean() {
    }
    
    private String email;
    private int phone;
    private int postcode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
    
    
}
