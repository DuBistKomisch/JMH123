/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.data_access;

/**
 *
 * @author morga_000
 */
public class Pending {
    private int p_id;
    private int c_id;
    private double amount;

    public Pending(int p_id, int c_id, double amount) {
        this.p_id = p_id;
        this.c_id = c_id;
        this.amount = amount;
    }

    public Pending(int c_id, double amount) {
        this.c_id = c_id;
        this.amount = amount;
    }
    
    public double getAmount() {
        return this.amount;
    }
    
    public int getCID() {
        return this.c_id;
    }
    
    public int getPID() {
        return this.p_id;
    }
    
    public void setPID(int pid) {
        this.p_id = pid;
    }
}
