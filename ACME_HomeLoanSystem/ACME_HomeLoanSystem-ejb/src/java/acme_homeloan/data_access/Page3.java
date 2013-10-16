/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_homeloan.data_access;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Howard Tseng
 */
@Entity
@Table(name = "PAGE3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Page3.findAll", query = "SELECT p FROM Page3 p"),
    @NamedQuery(name = "Page3.findById", query = "SELECT p FROM Page3 p WHERE p.id = :id"),
    @NamedQuery(name = "Page3.findByCId", query = "SELECT p FROM Page3 p WHERE p.cId = :cId"),
    @NamedQuery(name = "Page3.findByEmail", query = "SELECT p FROM Page3 p WHERE p.email = :email"),
    @NamedQuery(name = "Page3.findByPhone", query = "SELECT p FROM Page3 p WHERE p.phone = :phone"),
    @NamedQuery(name = "Page3.findByPostcode", query = "SELECT p FROM Page3 p WHERE p.postcode = :postcode")})
public class Page3 implements Serializable {
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
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private Integer phone;
    @Column(name = "POSTCODE")
    private Integer postcode;

    public Page3() {
    }

    public Page3(Integer id) {
        this.id = id;
    }

    public Page3(Integer id, int cId) {
        this.id = id;
        this.cId = cId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
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
        if (!(object instanceof Page3)) {
            return false;
        }
        Page3 other = (Page3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acme_homeloan.data_access.Page3[ id=" + id + " ]";
    }
    
}
