/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.impl;

import com.heinvdende.versionreview.test.modules.filemembers.codecompare.GetFileChangesService;
import com.heinvdende.versionreview.test.modules.repository.domain.FileChange;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes.ClassChanges;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class GetFileChangesServiceImpl implements GetFileChangesService{
    
    // Old file's nodes
    private ClassOrInterfaceDeclaration oldClass;
    
    // New file's nodes
    private ClassOrInterfaceDeclaration newClass;

    
    public GetFileChangesServiceImpl(ClassOrInterfaceDeclaration oldClass, ClassOrInterfaceDeclaration newClass) {
        this.oldClass = oldClass;
        this.newClass = newClass;
    }

    @Override
    public List<FileChange> getFileChanges() {
        // Create the top level strat, which is class
        ClassChanges strat = new ClassChanges();
        List<FileChange> list = strat.doCheck(oldClass, newClass);
        
        return list;
    }
}
































