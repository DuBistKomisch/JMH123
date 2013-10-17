/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_homeloan.data_access;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Howard Tseng
 */
@Entity
@Table(name = "CUSTOMERDETAILS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customerdetails.findAll", query = "SELECT c FROM Customerdetails c"),
    @NamedQuery(name = "Customerdetails.findById", query = "SELECT c FROM Customerdetails c WHERE c.id = :id"),
    @NamedQuery(name = "Customerdetails.findByCId", query = "SELECT c FROM Customerdetails c WHERE c.cId = :cId"),
    @NamedQuery(name = "Customerdetails.findByCurrentjob", query = "SELECT c FROM Customerdetails c WHERE c.currentjob = :currentjob"),
    @NamedQuery(name = "Customerdetails.findBySalary", query = "SELECT c FROM Customerdetails c WHERE c.salary = :salary"),
    @NamedQuery(name = "Customerdetails.findByEmail", query = "SELECT c FROM Customerdetails c WHERE c.email = :email"),
    @NamedQuery(name = "Customerdetails.findByPhone", query = "SELECT c FROM Customerdetails c WHERE c.phone = :phone"),
    @NamedQuery(name = "Customerdetails.findByPostcode", query = "SELECT c FROM Customerdetails c WHERE c.postcode = :postcode"),
    @NamedQuery(name = "Customerdetails.findByMethod", query = "SELECT c FROM Customerdetails c WHERE c.method = :method")})
public class Customerdetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "C_ID")
    private int cId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CURRENTJOB")
    private String currentjob;
    @Min(value = 1)
    @Max(value = 999999)
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALARY")
    private BigDecimal salary;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Pattern(regexp="^\\d{10}$", message="Invalid phone format, should be xxxxxxxxxx.")
    @Basic(optional = false)
    @NotNull
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits.")
    @Column(name = "PHONE")
    private String phone;
    @Pattern(regexp="^\\d{4}$", message="Invalid post code format, should be xxxx.")
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 4, message = "Post code must be 4 digits.")
    @Column(name = "POSTCODE")
    private String postcode;
    @Min(value = 0)
    @Max(value = 2)
    @Basic(optional = false)
    @NotNull
    @Column(name = "METHOD")
    private int method;

    public Customerdetails() {
    }

    public Customerdetails(Integer id) {
        this.id = id;
    }

    public Customerdetails(Integer id, int cId, String currentjob, BigDecimal salary, String email, String phone, String postcode, int method) {
        this.id = id;
        this.cId = cId;
        this.currentjob = currentjob;
        this.salary = salary;
        this.email = email;
        this.phone = phone;
        this.postcode = postcode;
        this.method = method;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCId() {
        return cId;
    }

    public void setCId(int cId) {
        this.cId = cId;
    }

    public String getCurrentjob() {
        return currentjob;
    }

    public void setCurrentjob(String currentjob) {
        this.currentjob = currentjob;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customerdetails)) {
            return false;
        }
        Customerdetails other = (Customerdetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acme_homeloan.data_access.Customerdetails[ id=" + id + " ]";
    }
    
}
