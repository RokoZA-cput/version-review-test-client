/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.persistence;

import com.heinvdende.versionreview.test.modules.repository.domain.CodeFile;

/**
 *
 * @author Heinrich
 */
public class CodeFileRepository extends AbstractRepository<CodeFile, Long> {

    @Override
    public Class getEntityClass() {
        return CodeFile.class;
    }
    
}
