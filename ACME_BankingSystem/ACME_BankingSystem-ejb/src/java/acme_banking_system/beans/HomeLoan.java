
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.beans;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import acme_banking_system.*;
import acme_banking_system.data_access.Customer;
import acme_banking_system.data_access.Saving;

/**
 *
 * @author Howard Tseng
 */
@Entity
public class HomeLoan implements Serializable {
    private static final long serialVersionUID = 1L;
    
    Customer cust = new Customer();
    Saving sav = new Saving();
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int c_ID;
    private String accnum;
    private double amountBorrowed;
    private double amountRepayed;
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerIDFK (){
    c_ID = cust.getId();
    }
    
    public int getCustomerIDFK(){
        return c_ID;
    }
    
    public void setAccNumFK(){
        accnum = sav.getAccNum();
    }
    
    public String getAccNumFK(){
        return accnum;
    }
    
    public void setAmountBorrowed(double amountBorrowed){
        this.amountBorrowed = amountBorrowed;
    }
    
    public double getAmountBorrowed(){
        return amountBorrowed;
    }
    
    public void setAmountRepayed(double amountRepayed){
        this.amountRepayed = amountRepayed;
    }
    
    public double getAmountRepayed(){
        return amountRepayed;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HomeLoan)) {
            return false;
        }
        HomeLoan other = (HomeLoan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acme_banking_system.beans.HomeLoan[ id=" + id + " ]";
    }
}
