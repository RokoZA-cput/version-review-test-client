/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Heinrich
 */
@Entity
@Table(name = "TASKCLASSES")
@XmlRootElement
public class TaskClass implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Size(max = 20)
    @Column(name = "CLASSNAME")
    private String className;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "taskClass") //
    private List<CodeFile> codeFileList;
    
    @JoinColumn(name = "TASKID", referencedColumnName = "ID")
    @ManyToOne
    private Task task;

    @OneToOne
    private ChangedCodeFile finalFile;
    
    @OneToMany
    private Map<User, ChangedCodeFile> userFinalFiles = new HashMap<>(); // cannot be retrieved from database, should be set in some functionality
    
    public TaskClass() {
    }

    public TaskClass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ChangedCodeFile getFinalFile() {
        return finalFile;
    }

    public void setFinalFile(ChangedCodeFile finalFile) {
        this.finalFile = finalFile;
    }

    public Map<User, ChangedCodeFile> getUserFinalFiles() {
        return userFinalFiles;
    }

    public void setUserFinalFiles(Map<User, ChangedCodeFile> userFinalFiles) {
        this.userFinalFiles = userFinalFiles;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @XmlTransient
    public List<CodeFile> getCodeFileList() {
        return codeFileList;
    }

    public void setCodeFileList(List<CodeFile> codeFileList) {
        this.codeFileList = codeFileList;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskClass)) {
            return false;
        }
        TaskClass other = (TaskClass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.heinvdende.versionreview.test.modules.repository.domain.entities.TaskClass[ id=" + id + " ]";
    }
    
}
