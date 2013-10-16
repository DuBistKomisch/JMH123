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
    private String phone;
    private String postcode;
    private int method;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    public void setmethod(int method){
        this.method = method;
    }
    
    public int getMethod(){
        return method;
    }
}
