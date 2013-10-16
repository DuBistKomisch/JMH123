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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Howard Tseng
 */
@Entity
@Table(name = "PAGE4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Page4.findAll", query = "SELECT p FROM Page4 p"),
    @NamedQuery(name = "Page4.findById", query = "SELECT p FROM Page4 p WHERE p.id = :id"),
    @NamedQuery(name = "Page4.findByCId", query = "SELECT p FROM Page4 p WHERE p.cId = :cId"),
    @NamedQuery(name = "Page4.findByLoan", query = "SELECT p FROM Page4 p WHERE p.loan = :loan")})
public class Page4 implements Serializable {
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LOAN")
    private Double loan;

    public Page4() {
    }

    public Page4(Integer id) {
        this.id = id;
    }

    public Page4(Integer id, int cId) {
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

    public Double getLoan() {
        return loan;
    }

    public void setLoan(Double loan) {
        this.loan = loan;
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
        if (!(object instanceof Page4)) {
            return false;
        }
        Page4 other = (Page4) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acme_homeloan.data_access.Page4[ id=" + id + " ]";
    }
    
}
