/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.data_access;

import java.io.Serializable;

/**
 *
 * @author morga_000
 */
public class ACME_Transaction implements Serializable {
    private int id;
    private int accNum;
    private int accNum2;
    private int amount;
    private String desc;
    
    public ACME_Transaction() {
    }
    
    public ACME_Transaction(int id) {
        this.id = id;
    }
    
    public ACME_Transaction(int accNum, int accNum2, int amount, String desc) {
        this.accNum = accNum;
        this.accNum2 = accNum2;
        this.amount = amount;
        this.desc = desc;
    }

    public int getId() {
        return this.id;
    }
    
    public int getAccNum() {
        return this.accNum;
    }
    
    public int getAccNum2() {
        return this.accNum2;
    }
    
    public int getAmount() {
        return this.amount;
    }
    
    public String getDesc() {
        return this.desc;
    }
}
