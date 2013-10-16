/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginBean;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;


/**
 *
 * @author Howard Tseng
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean {

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
