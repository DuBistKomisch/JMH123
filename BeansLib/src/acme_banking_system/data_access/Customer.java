package acme_banking_system.data_access;

/**
 *
 * @author morga_000
 */
import java.io.Serializable;
import java.sql.Date;

public class Customer implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;

    public Customer() {
    }

    public Customer(int id) {
        this.id = id;
    }

    public Customer(String firstName, String lastName, Date dateOfBirth, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Customer(int id, String firstName, String lastName, Date dateOfBirth, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}