/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.persistence.fetch.FetchEntity;

import com.heinvdende.versionreview.test.modules.repository.domain.ChangedCodeFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class FetchChangedCodeFile extends FetchEntity<ChangedCodeFile> {

    @Override
    protected List<Object> getEntityLists(ChangedCodeFile entity) {
        List list = new ArrayList();
        
        list.add(entity.getChanges());
        list.add(entity.getClassMemberList());
        
        return list;
    }



}
