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

    public void setC_ID(Integer id) {
        this.C_ID = id;
    }
    public Integer  getC_ID() {
        return this.C_ID;
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
