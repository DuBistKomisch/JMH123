package acme_banking_system.data_access;

/**
 *
 * @author morga_000
 */
import java.io.Serializable;
import java.sql.Date;

public class Saving implements Serializable {

    private int id;
    private String accNum;
    private double balance;
    private Date dateTimeCreation;

    public Saving() {
    }

    public Saving(String accNum) {
        this.accNum = accNum;
    }

    public Saving(int id) {
        this.id = id;
    }

    public Saving(int id, String accNum) {
        this.id = id;
        this.accNum = accNum;
    }

    public Saving(int id, String accNum, double balance, Date dateTimeCreation) {
        this.id = id;
        this.accNum = accNum;
        this.balance = balance;
        this.dateTimeCreation = dateTimeCreation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public String getAccNum() {
        return this.accNum;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setDateTimeCreated(Date dateTimeCreation) {
        this.dateTimeCreation = dateTimeCreation;
    }

    public Date getDateTimeCreated() {
        return this.dateTimeCreation;
    }
}
