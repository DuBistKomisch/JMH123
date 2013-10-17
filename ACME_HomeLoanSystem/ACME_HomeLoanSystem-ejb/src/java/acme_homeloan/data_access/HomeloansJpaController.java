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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Howard Tseng
 */
@TransactionManagement(TransactionManagementType.BEAN)
public class HomeloansJpaController implements Serializable {

    public HomeloansJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ACME_HomeLoanSystem-ejbPU");;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Homeloans homeloans) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(homeloans);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Homeloans homeloans) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            homeloans = em.merge(homeloans);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = homeloans.getAccnum();
                if (findHomeloans(id) == null) {
                    throw new NonexistentEntityException("The homeloans with id " + id + " no longer exists.");
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
            Homeloans homeloans;
            try {
                homeloans = em.getReference(Homeloans.class, id);
                homeloans.getAccnum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The homeloans with id " + id + " no longer exists.", enfe);
            }
            em.remove(homeloans);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Homeloans> findHomeloansEntities() {
        return findHomeloansEntities(true, -1, -1);
    }

    public List<Homeloans> findHomeloansEntities(int maxResults, int firstResult) {
        return findHomeloansEntities(false, maxResults, firstResult);
    }

    private List<Homeloans> findHomeloansEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Homeloans.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            List<Homeloans> hll = q.getResultList();
            em.getTransaction().commit();
            return hll;
        } finally {
            em.close();
        }
    }

    public Homeloans findHomeloans(Integer id) {
        EntityManager em = getEntityManager();
        Homeloans homeloans = null;
        try {
            em.getTransaction().begin();
            homeloans = em.find(Homeloans.class, id);
            em.getTransaction().commit();
            return homeloans;
        } finally {
            em.close();
        }
    }

    public int getHomeloansCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Homeloans> rt = cq.from(Homeloans.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Homeloans> findHomeloansByCid(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Homeloans> query = em.createNamedQuery("Homeloans.findByCId", Homeloans.class);
            query.setParameter("cId", id);
            List<Homeloans> hll = query.getResultList();
            em.getTransaction().commit();
            return hll;
        } finally {
            em.close();
        }
    }
}
