/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Heinrich
 */
public class TaskClass {
    private long id;
    private String className;
    private List<CodeFile> allFiles = new ArrayList<>();    //all version including original and excluding final
    private ChangedCodeFile finalFile;
    private Map<User, CodeFile> userFinalFiles = new HashMap<>(); // cannot be retrieved from database, should be set in some functionality

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<CodeFile> getAllFiles() {
        return allFiles;
    }

    public void setAllFiles(List<CodeFile> allFiles) {
        this.allFiles = allFiles;
    }

    public ChangedCodeFile getFinalFile() {
        return finalFile;
    }

    public void setFinalFile(ChangedCodeFile committedFile) {
        this.finalFile = committedFile;
    }

    public Map<User, CodeFile> getUserFinalFiles() {
        return userFinalFiles;
    }

    public void setUserFinalFiles(Map<User, CodeFile> finalFiles) {
        this.userFinalFiles = finalFiles;
    }

}
