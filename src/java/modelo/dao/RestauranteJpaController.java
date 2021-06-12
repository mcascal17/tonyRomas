/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Restaurante;

/**
 *
 * @author macar
 */
public class RestauranteJpaController implements Serializable {

    public RestauranteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Restaurante restaurante) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(restaurante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Restaurante restaurante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            restaurante = em.merge(restaurante);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = restaurante.getId();
                if (findRestaurante(id) == null) {
                    throw new NonexistentEntityException("The restaurante with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Restaurante restaurante;
            try {
                restaurante = em.getReference(Restaurante.class, id);
                restaurante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The restaurante with id " + id + " no longer exists.", enfe);
            }
            em.remove(restaurante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Restaurante> findRestauranteEntities() {
        return findRestauranteEntities(true, -1, -1);
    }

    public List<Restaurante> findRestauranteEntities(int maxResults, int firstResult) {
        return findRestauranteEntities(false, maxResults, firstResult);
    }

    private List<Restaurante> findRestauranteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Restaurante.class));
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
    
    public Restaurante findRestauranteNombre(String nombre){
        List<Restaurante> restaurantes = findRestauranteEntities();
        Restaurante res = new Restaurante();
        for(Restaurante aux : restaurantes){
            if(nombre.equals(aux.getNombre())){
                res=aux;
            }
        }
        return res;
    }


    public Restaurante findRestaurante(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Restaurante.class, id);
        } finally {
            em.close();
        }
    }

    public int getRestauranteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Restaurante> rt = cq.from(Restaurante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
