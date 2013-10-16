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
@Table(name = "PAGE2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Page2.findAll", query = "SELECT p FROM Page2 p"),
    @NamedQuery(name = "Page2.findById", query = "SELECT p FROM Page2 p WHERE p.id = :id"),
    @NamedQuery(name = "Page2.findByCId", query = "SELECT p FROM Page2 p WHERE p.cId = :cId"),
    @NamedQuery(name = "Page2.findByCurrentjob", query = "SELECT p FROM Page2 p WHERE p.currentjob = :currentjob"),
    @NamedQuery(name = "Page2.findBySalary", query = "SELECT p FROM Page2 p WHERE p.salary = :salary")})
public class Page2 implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALARY")
    private double salary;

    public Page2() {
    }

    public Page2(Integer id) {
        this.id = id;
    }

    public Page2(Integer id, int cId, String currentjob, double salary) {
        this.id = id;
        this.cId = cId;
        this.currentjob = currentjob;
        this.salary = salary;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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
        if (!(object instanceof Page2)) {
            return false;
        }
        Page2 other = (Page2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acme_homeloan.data_access.Page2[ id=" + id + " ]";
    }
    
}
