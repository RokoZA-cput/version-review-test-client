/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.persistence.fetch.FetchEntity;

import com.heinvdende.versionreview.test.modules.repository.domain.Task;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class FetchTask extends FetchEntity<Task> {

    @Override
    protected List<Object> getEntityLists(Task entity) {
        List list = new ArrayList();

        list.add(entity.getSubTasks());
        list.add(entity.getTaskClassList());
        list.add(entity.getUserList());
        
        return list;
    }



}
