/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.persistence;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author Heinrich
 */
public abstract class AbstractRepository<T, ID extends Serializable> {

    private Session session;
    
    public AbstractRepository() {
    }
    
    public abstract Class getEntityClass();
    
    public void setSession(Session session) {
        this.session = session;
    }
    
    public Session getSession() {
        return session;
    }
    
    public void flush() {
        getSession().flush();
    }
    
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
}
