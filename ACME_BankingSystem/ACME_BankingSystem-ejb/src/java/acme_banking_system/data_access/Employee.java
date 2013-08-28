package acme_banking_system.data_access;

/**
 *
 * @author morga_000
 */
public class Employee {

    private Integer E_ID;
    private String E_FIRSTNAME;
    private String E_LASTNAME;
    private String E_PASSWORD;

    public Employee() {
    }

    public Employee(Integer E_ID) {
        this.E_ID = E_ID;
    }

    public void setE_ID(Integer E_ID) {
        this.E_ID = E_ID;
    }

    public Integer getE_ID() {
        return this.E_ID;
    }

    public void setE_FIRSTNAME(String firstName) {
        this.E_FIRSTNAME = firstName;
    }

    public String getE_FIRSTNAME() {
        return this.E_FIRSTNAME;
    }

    public void setE_LASTNAME(String lastName) {
        this.E_FIRSTNAME = lastName;
    }

    public String getE_LASTNAME() {
        return this.E_FIRSTNAME;
    }

    public void setE_PASSWORD(String pass) {
        this.E_PASSWORD = pass;
    }

    public String getE_PASSWORD() {
        return this.E_PASSWORD;
    }
}
