/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_banking_system.beans;

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
@Table(name = "DEBT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Debt.findAll", query = "SELECT d FROM Debt d"),
    @NamedQuery(name = "Debt.findById", query = "SELECT d FROM Debt d WHERE d.id = :id"),
    @NamedQuery(name = "Debt.findByCId", query = "SELECT d FROM Debt d WHERE d.cId = :cId"),
    @NamedQuery(name = "Debt.findByAccnum", query = "SELECT d FROM Debt d WHERE d.accnum = :accnum"),
    @NamedQuery(name = "Debt.findByAmountborrowed", query = "SELECT d FROM Debt d WHERE d.amountborrowed = :amountborrowed"),
    @NamedQuery(name = "Debt.findByAmountrepayed", query = "SELECT d FROM Debt d WHERE d.amountrepayed = :amountrepayed")})
public class Debt implements Serializable {
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
    @Column(name = "ACCNUM")
    private String accnum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNTBORROWED")
    private double amountborrowed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNTREPAYED")
    private double amountrepayed;

    public Debt() {
    }

    public Debt(Integer id) {
        this.id = id;
    }

    public Debt(Integer id, int cId, String accnum, double amountborrowed, double amountrepayed) {
        this.id = id;
        this.cId = cId;
        this.accnum = accnum;
        this.amountborrowed = amountborrowed;
        this.amountrepayed = amountrepayed;
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

    public String getAccnum() {
        return accnum;
    }

    public void setAccnum(String accnum) {
        this.accnum = accnum;
    }

    public double getAmountborrowed() {
        return amountborrowed;
    }

    public void setAmountborrowed(double amountborrowed) {
        this.amountborrowed = amountborrowed;
    }

    public double getAmountrepayed() {
        return amountrepayed;
    }

    public void setAmountrepayed(double amountrepayed) {
        this.amountrepayed = amountrepayed;
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
        if (!(object instanceof Debt)) {
            return false;
        }
        Debt other = (Debt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acme_banking_system.beans.Debt[ id=" + id + " ]";
    }
    
}
