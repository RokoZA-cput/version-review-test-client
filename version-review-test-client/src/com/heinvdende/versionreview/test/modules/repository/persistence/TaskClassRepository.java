/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.persistence;

import com.heinvdende.versionreview.test.modules.repository.domain.TaskClass;

/**
 *
 * @author Heinrich
 */
public class TaskClassRepository extends AbstractRepository<TaskClass, Long> {

    @Override
    public Class getEntityClass() {
        return TaskClass.class;
    }
    
}
