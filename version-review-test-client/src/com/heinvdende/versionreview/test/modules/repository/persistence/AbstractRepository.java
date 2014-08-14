/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.persistence;

import com.heinvdende.versionreview.test.modules.repository.persistence.hibernate.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author Heinrich
 */
public abstract class AbstractRepository<T, ID extends Serializable> {

    private Transaction transaction;
    private Session session;
    
    public AbstractRepository() {
    }
            
    // DAO Methods
    public T getEntity(ID id) {
        T entity = null;
        
        try {
            entity = (T) session.load(getEntityClass(), id);
        }
        catch(ObjectNotFoundException e) {
            System.out.println("Entity Not Found");
        }
        
        return entity;
    }

    public List<T> getEntities() {
        List<T> list = null;
        
        try {
            list = findByCriteria();
        }
        catch(ObjectNotFoundException e) {
            System.out.println("Entity Not Found");
        }
        
        return list;
    }

    public T createEntity(T entity) { 
        session.saveOrUpdate(entity);  
        return entity; 
    }

    public T updateEntity(T entity) {
        session.merge(entity);
        return entity;
    }

    public void removeEntity(T entity) {
        session.delete(entity);
    }
    
    protected List<T> findByCriteria(Criterion... criterion) {  
        Criteria crit = getSession().createCriteria(getEntityClass());  
        for (Criterion c : criterion) {  
            crit.add(c);  
        }  
        return crit.list();  
    }
    
    public Class getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]; 
    }
    
    // Commit Transaction & End Session
    public void commit() {
        try {
            getTransaction().commit();
        }
        catch(RuntimeException e) {
            System.out.println(e.getStackTrace());
            getTransaction().rollback();  
        }  
        finally {  
            getSession().close();  
        }
    }
    
    // Session & Transaction Methods
    public Session getSession() {
        if(session == null) 
            throw new IllegalStateException("Session has not been opened");
            
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    public void setTransaction(Transaction t) {
        this.transaction = t;
    }
    
    public Transaction getTransaction() {
        return this.transaction;
    }
    
    // Creates Hibernet Session
    public void openSession() {
        setSession(HibernateUtil.getSessionFactory().openSession());
    }
    
    public void flush() {
        getSession().flush();
    }
}
