/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ListAccountBean;

import acme_homeloan.data_access.Homeloans;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Howard Tseng
 */
@Named(value = "listAccountBean")
@RequestScoped
public class ListAccountBean {

    /**
     * Creates a new instance of ListAccountBean
     */
    public ListAccountBean() {
    }
    
    private ArrayList<Object> savings = new ArrayList<Object>();
    private ArrayList<Homeloans> loan = new ArrayList<Homeloans>();
    

    
    public void setSavingList(Object object){
        savings.add(object);
    }
    
    Object save;
    
    public Object SavingList(){
        for (int i=0; i < savings.size(); i++) {
         save = savings.get(i);
         return save;
        }
        return null;
    }
    
    public void setLoanlist(Homeloans object){
     loan.add(object);
    }
    
    Homeloans sloan;
    
    public Homeloans LoanList(){
        for (int i=0; i < loan.size(); i++) {
         sloan = loan.get(i);
         return sloan;
        }
        return null;
    }
    
}
