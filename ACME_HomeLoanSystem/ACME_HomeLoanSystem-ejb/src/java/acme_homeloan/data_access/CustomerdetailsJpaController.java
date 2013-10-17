/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acme_homeloan.data_access;

import acme_homeloan.data_access.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Howard Tseng
 */
@TransactionManagement(TransactionManagementType.BEAN)
public class CustomerdetailsJpaController implements Serializable {

    public CustomerdetailsJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ACME_HomeLoanSystem-ejbPU");;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Customerdetails customerdetails) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(customerdetails);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customerdetails customerdetails) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            customerdetails = em.merge(customerdetails);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = customerdetails.getId();
                if (findCustomerdetails(id) == null) {
                    throw new NonexistentEntityException("The customerdetails with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customerdetails customerdetails;
            try {
                customerdetails = em.getReference(Customerdetails.class, id);
                customerdetails.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customerdetails with id " + id + " no longer exists.", enfe);
            }
            em.remove(customerdetails);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Customerdetails> findCustomerdetailsEntities() {
        return findCustomerdetailsEntities(true, -1, -1);
    }

    public List<Customerdetails> findCustomerdetailsEntities(int maxResults, int firstResult) {
        return findCustomerdetailsEntities(false, maxResults, firstResult);
    }

    private List<Customerdetails> findCustomerdetailsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customerdetails.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Customerdetails findCustomerdetails(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customerdetails.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerdetailsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customerdetails> rt = cq.from(Customerdetails.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
