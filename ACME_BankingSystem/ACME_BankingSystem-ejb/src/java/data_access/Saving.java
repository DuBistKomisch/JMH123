/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_access;

/**
 *
 * @author morga_000
 */
import java.sql.Date;

public class Saving {
    private Integer C_ID;
    private String  ACCNUM;
    private Integer BALANCE;
    private Date    S_DATETIMECREATION;
    //
    private String Description;
    private Integer E_ID;
    
    public Saving(){
        
    }
    
    public Saving(/*Integer E_ID,*/String ACCNUM/*, Integer BALANCE, String Desc*/)
    {
        //this.E_ID = E_ID;
        this.ACCNUM = ACCNUM;
        //this.BALANCE= BALANCE;
        //this.Description = Desc;
    }
    public Saving(Integer C_ID){
        this.C_ID = C_ID;
    }
   /* public Saving(Employee E_ID, String ACCNUM, Integer BALANCE)
    {
        this.C_ID = C_ID;
        this.ACCNUM = ACCNUM;
        this.BALANCE = BALANCE;
        this.S_DATETIMECREATION = S_DATETIMECREATION;
        emp = E_ID(); 
    }*/
    
    public Saving(String ACCNUM, Integer BALANCE)
    {
        this.C_ID = C_ID;
        this.ACCNUM = ACCNUM;
        this.BALANCE = BALANCE;
        this.S_DATETIMECREATION = S_DATETIMECREATION;
    }
    
    public void setC_ID(Integer id) {
        this.C_ID = id;
    }
    public Integer  getC_ID() {
        return this.C_ID;
    }

    public void setE_ID(Integer id) {
        this.E_ID = id;
    }
    public Integer  getE_ID() {
        return this.E_ID;
    }
   
     public void setDesc(String Description) {
        this.Description = Description;
    }
    public String   getDesc() {
        return this.Description;
    }
    
    public void setACCNUM(String number) {
        this.ACCNUM = number;
    }
    public String   getACCNUM() {
        return this.ACCNUM;
    }

    public void setBALANCE(Integer bal) {
        this.BALANCE = bal;
    }
    public Integer  getBALANCE() {
        return this.BALANCE;
    }
    
    public void setCREATIONTIME(Date crea) {
        this.S_DATETIMECREATION = crea;
    }
    public Date getCREATIONTIME() {
        return this.S_DATETIMECREATION;
    }
}
