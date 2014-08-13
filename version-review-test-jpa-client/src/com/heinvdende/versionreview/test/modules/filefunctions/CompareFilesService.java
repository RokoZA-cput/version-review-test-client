/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filefunctions;

import com.heinvdende.versionreview.test.modules.repository.domain.ChangedCodeFile;
import com.heinvdende.versionreview.test.modules.repository.domain.CodeFile;
import com.heinvdende.versionreview.test.modules.repository.domain.TaskClass;

/**
 *
 * @author Heinrich
 */
public interface CompareFilesService {
    public TaskClass highlightFileChanges(TaskClass task) throws Exception;
    public ChangedCodeFile getVersionChanged(CodeFile oldVersion, CodeFile newVersion);
    public ChangedCodeFile getVersionChanged(CodeFile newVersion); // Uses SVN as old version
    public ChangedCodeFile getVersionDeletions(CodeFile oldVersion, CodeFile newVersion);
}
