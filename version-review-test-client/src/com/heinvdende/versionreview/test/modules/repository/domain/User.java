/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Heinrich
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Size(max = 20)
    @Column(name = "USERNAME")
    private String username;
    
    @Size(max = 20)
    @Column(name = "PASSWORD")
    private String password;
    
    @ManyToMany(mappedBy = "userList", cascade = CascadeType.MERGE)
    private List<Task> taskList = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    private List<CodeFile> codeFileList;

    @OneToMany(mappedBy = "user")
    private List<FileChange> fileChangeList;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @XmlTransient
    public List<CodeFile> getCodeFileList() {
        return codeFileList;
    }

    public void setCodeFileList(List<CodeFile> codeFileList) {
        this.codeFileList = codeFileList;
    }

    public void addTask(Task task) {
        this.getTaskList().add(task);
        task.getUserList().add(this);
    }
    
    public void removeTask(Task task) {
        this.getTaskList().remove(task);
        task.getUserList().remove(this);
    }
    
    @XmlTransient
    public List<FileChange> getFileChangeList() {
        return fileChangeList;
    }

    public void setFileChangeList(List<FileChange> fileChangeList) {
        this.fileChangeList = fileChangeList;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.heinvdende.versionreview.test.modules.repository.domain.entities.User[ id=" + id + " ]";
    }
    
}
