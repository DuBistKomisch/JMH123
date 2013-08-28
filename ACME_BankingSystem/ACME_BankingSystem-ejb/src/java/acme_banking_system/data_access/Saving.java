package acme_banking_system.data_access;

/**
 *
 * @author morga_000
 */
import java.io.Serializable;
import java.sql.Date;

public class Saving implements ISaving, Serializable {

    private int id;
    private String accNum;
    private double balance;
    private Date dateTimeCreation;

    protected Saving() {
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

    @Override
    public int getId() {
        return this.id;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    @Override
    public String getAccNum() {
        return this.accNum;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    public void setDateTimeCreated(Date dateTimeCreation) {
        this.dateTimeCreation = dateTimeCreation;
    }

    @Override
    public Date getDateTimeCreated() {
        return this.dateTimeCreation;
    }
}
