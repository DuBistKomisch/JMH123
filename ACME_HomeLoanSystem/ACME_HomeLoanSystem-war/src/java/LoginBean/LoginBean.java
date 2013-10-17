/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginBean;

import HomeLoanBean.HomeLoanBean;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 *
 * @author Howard Tseng
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }
    
    private HomeLoanBean homeloan;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HomeLoanBean getHomeloan() {
        return homeloan;
    }
    
    public String submit() {
        boolean authenticated = true;
        if (authenticated)
            return "HomePage";
        else
            return "Login";
    }
    
    public String createHomeLoan()
    {
        homeloan = new HomeLoanBean();
        return "Page1";
    }
    
    public String cancelHomeLoan()
    {
        homeloan = null;
        return "HomePage";
    }
    
    public String confirmHomeLoan()
    {
        // TODO
        homeloan = null;
        return "HomePage";
    }
}
