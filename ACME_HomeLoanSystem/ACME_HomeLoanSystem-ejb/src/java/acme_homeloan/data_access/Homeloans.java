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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Howard Tseng
 */
@Entity
@Table(name = "HOMELOANS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Homeloans.findAll", query = "SELECT h FROM Homeloans h"),
    @NamedQuery(name = "Homeloans.findByAccnum", query = "SELECT h FROM Homeloans h WHERE h.accnum = :accnum"),
    @NamedQuery(name = "Homeloans.findByCId", query = "SELECT h FROM Homeloans h WHERE h.cId = :cId"),
    @NamedQuery(name = "Homeloans.findByAmountborrowed", query = "SELECT h FROM Homeloans h WHERE h.amountborrowed = :amountborrowed"),
    @NamedQuery(name = "Homeloans.findByAmountrepayed", query = "SELECT h FROM Homeloans h WHERE h.amountrepayed = :amountrepayed")})
public class Homeloans implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ACCNUM")
    private Integer accnum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "C_ID")
    private int cId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNTBORROWED")
    private BigDecimal amountborrowed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNTREPAYED")
    private BigDecimal amountrepayed;

    public Homeloans() {
    }

    public Homeloans(Integer accnum) {
        this.accnum = accnum;
    }

    public Homeloans(Integer accnum, int cId, BigDecimal amountborrowed, BigDecimal amountrepayed) {
        this.accnum = accnum;
        this.cId = cId;
        this.amountborrowed = amountborrowed;
        this.amountrepayed = amountrepayed;
    }

    public Integer getAccnum() {
        return accnum;
    }

    public void setAccnum(Integer accnum) {
        this.accnum = accnum;
    }

    public int getCId() {
        return cId;
    }

    public void setCId(int cId) {
        this.cId = cId;
    }

    public BigDecimal getAmountborrowed() {
        return amountborrowed;
    }

    public void setAmountborrowed(BigDecimal amountborrowed) {
        this.amountborrowed = amountborrowed;
    }

    public BigDecimal getAmountrepayed() {
        return amountrepayed;
    }

    public void setAmountrepayed(BigDecimal amountrepayed) {
        this.amountrepayed = amountrepayed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accnum != null ? accnum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Homeloans)) {
            return false;
        }
        Homeloans other = (Homeloans) object;
        if ((this.accnum == null && other.accnum != null) || (this.accnum != null && !this.accnum.equals(other.accnum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acme_homeloan.data_access.Homeloans[ accnum=" + accnum + " ]";
    }
    
}
