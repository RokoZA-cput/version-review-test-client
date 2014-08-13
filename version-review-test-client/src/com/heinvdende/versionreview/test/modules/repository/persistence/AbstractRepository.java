/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.persistence;

import com.heinvdende.versionreview.test.modules.repository.persistence.constants.PersistenceConstants;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Heinrich
 */
public abstract class AbstractRepository<T, S> {

    private Class entityClass;
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    public AbstractRepository() {
        entityClass = getEntityClass();
    }
    
    public abstract Class getEntityClass();
    
    public T getEntity(S id) {
        emf = Persistence.createEntityManagerFactory(PersistenceConstants.PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();
        
        T entity = null;

        try {
            em.getTransaction().begin();
            entity = (T) em.find(entityClass, id);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        finally {
            em.close();
            emf.close();
        }

        
        return entity;
    }

    public List<T> getEntities() {
        emf = Persistence.createEntityManagerFactory(PersistenceConstants.PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();
        
        List<T> entities = null;

        try {
            em.getTransaction().begin();
            entities = em.createQuery("SELECT u FROM Users u").getResultList();
            em.getTransaction().commit();
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
            emf.close();
        }

        
        return entities;
    }

    public T createEntity(T entity) {    
        emf = Persistence.createEntityManagerFactory(PersistenceConstants.PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.flush();
            em.getTransaction().commit();
        }
        catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        finally {
            em.close();
            emf.close();
        }

        
        return entity;
    }

    public T updateEntity(T entity) {
        emf = Persistence.createEntityManagerFactory(PersistenceConstants.PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.flush();
            em.getTransaction().commit();  
        }
        catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback(); 
        }
        finally {
            em.close();
            emf.close();
        }

        
        return entity;
    }

    public void removeEntity(S id) {
        emf = Persistence.createEntityManagerFactory(PersistenceConstants.PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.remove(id);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        finally {
            em.close();
            emf.close();
        }
    }

}
