/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class ChangedCodeFile extends CodeFile {
    private List<FileChange> changes = new ArrayList<>();

    public List<FileChange> getChanges() {
        return changes;
    }

    public void setChanges(List<FileChange> changes) {
        this.changes = changes;
    }
}
