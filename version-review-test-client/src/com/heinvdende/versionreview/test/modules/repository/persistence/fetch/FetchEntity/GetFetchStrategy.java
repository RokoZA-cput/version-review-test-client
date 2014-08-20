/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.persistence.fetch.FetchEntity;

import com.heinvdende.versionreview.test.modules.repository.domain.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Heinrich
 */
public class GetFetchStrategy {
    
    public static FetchEntity getFetchStrategy(Class classType) {
        populateMap();
        
        return fetchClasses.get(classType);
    }
    
    private static Map<Class, FetchEntity> fetchClasses;
    
    private static void populateMap() {
        fetchClasses = new HashMap<>();
        fetchClasses.put(ChangedCodeFile.class, new FetchChangedCodeFile());
        fetchClasses.put(CodeFile.class, new FetchCodeFile());
        fetchClasses.put(ClassMember.class, new FetchClassMember());
        fetchClasses.put(FileChange.class, new FetchFileChange());
        fetchClasses.put(Project.class, new FetchProject());
        fetchClasses.put(Task.class, new FetchTask());
        fetchClasses.put(TaskClass.class, new FetchTaskClass());
        fetchClasses.put(User.class, new FetchUser());
    }
}
