package acme_banking_system.data_access;

import java.io.Serializable;

/**
 *
 * @author morga_000
 */
public class Employee implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private String password;

    protected Employee() {
    }
    
    public Employee(int id) {
        this.id = id;
    }

    public Employee(int id, String firstName, String lastName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getPassword() {
        return this.password;
    }
}
