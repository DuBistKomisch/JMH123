/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ListAccountBean;

import HomeLoanBean.HomeLoanBean;

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
    private ArrayList<HomeLoanBean> loan = new ArrayList<HomeLoanBean>();
    

    
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
    
    public void setLoanlist(HomeLoanBean object){
     loan.add(object);
    }
    
    HomeLoanBean sloan;
    
    public HomeLoanBean LoanList(){
        for (int i=0; i < loan.size(); i++) {
         sloan = loan.get(i);
         return sloan;
        }
        return null;
    }
    
}
