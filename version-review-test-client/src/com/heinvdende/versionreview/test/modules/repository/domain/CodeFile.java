/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "CODEFILES")
@XmlRootElement
public class CodeFile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Size(max = 500)
    @Column(name = "FILEPATH")
    private String filePath;
    
    @Column(name = "VERSION")
    private Integer version;
    
    @JoinColumn(name = "TASKCLASSID", referencedColumnName = "ID")
    @ManyToOne
    private TaskClass taskClass;
    
    @JoinColumn(name = "USERID", referencedColumnName = "ID")
    @ManyToOne
    private User user;
    
    @JoinColumn(name = "CODEFILEID", referencedColumnName = "ID")
    @OneToMany(cascade = CascadeType.ALL)   //
    private List<ClassMember> classMemberList = new ArrayList<>();

    public CodeFile() {
    }

    public CodeFile(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filepath) {
        this.filePath = filepath;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public TaskClass getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(TaskClass taskClass) {
        this.taskClass = taskClass;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @XmlTransient
    public List<ClassMember> getClassMemberList() {
        return classMemberList;
    }

    public void setClassMemberList(List<ClassMember> classMemberList) {
        this.classMemberList = classMemberList;
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
        if (!(object instanceof CodeFile)) {
            return false;
        }
        CodeFile other = (CodeFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.heinvdende.versionreview.test.modules.repository.domain.entities.CodeFile[ id=" + id + " ]";
    }
    
}
