/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.persistence.fetch.FetchEntity;

import java.util.List;
import org.hibernate.Hibernate;

/**
 *
 * @author Heinrich
 */
public abstract class FetchEntity<T> {
    
    public void fetchEntity(T entity) {
        List<Object> entityLists = getEntityLists(entity);
        
        for(Object list : entityLists) {
            initializeEntities(list);
        }
    }
    
    protected abstract List<Object> getEntityLists(T entity);
    
    private void initializeEntities(Object entities) {
        Hibernate.initialize(entities);
    }
}
