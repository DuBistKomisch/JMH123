package data_access;

/**
 *
 * @author morga_000
 */
import java.sql.Date;

public class Saving {

    private Integer C_ID;
    private String ACCNUM;
    private double BALANCE;
    private Date S_DATETIMECREATION;
    private String Description;
    private Integer E_ID;

    public Saving() {
    }

    public Saving(String ACCNUM) {
        this.ACCNUM = ACCNUM;
    }

    public Saving(Integer C_ID) {
        this.C_ID = C_ID;
    }

    public Saving(int C_ID, String ACCNUM) {
        this.C_ID = C_ID;
        this.ACCNUM = ACCNUM;
        this.BALANCE = BALANCE;
    }

    public void setC_ID(Integer id) {
        this.C_ID = id;
    }

    public Integer getC_ID() {
        return this.C_ID;
    }

    public void setE_ID(Integer id) {
        this.E_ID = id;
    }

    public Integer getE_ID() {
        return this.E_ID;
    }

    public void setDesc(String Description) {
        this.Description = Description;
    }

    public String getDesc() {
        return this.Description;
    }

    public void setACCNUM(String number) {
        this.ACCNUM = number;
    }

    public String getACCNUM() {
        return this.ACCNUM;
    }

    public void setBALANCE(Integer bal) {
        this.BALANCE = bal;
    }

    public double getBALANCE() {
        return this.BALANCE;
    }

    public void setCREATIONTIME(Date crea) {
        this.S_DATETIMECREATION = crea;
    }

    public Date getCREATIONTIME() {
        return this.S_DATETIMECREATION;
    }
}
