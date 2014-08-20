/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.persistence.fetch.FetchEntity;

import com.heinvdende.versionreview.test.modules.repository.domain.TaskClass;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class FetchTaskClass extends FetchEntity<TaskClass> {

    @Override
    protected List<Object> getEntityLists(TaskClass entity) {
        List list = new ArrayList();
        
        list.add(entity.getCodeFileList());
        list.add(entity.getUserFinalFiles());
        
        return list;
    }



}
