/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.db.rest;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "TASKCLASSES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaskClass.findAll", query = "SELECT t FROM TaskClass t"),
    @NamedQuery(name = "TaskClass.findById", query = "SELECT t FROM TaskClass t WHERE t.id = :id"),
    @NamedQuery(name = "TaskClass.findByClassname", query = "SELECT t FROM TaskClass t WHERE t.classname = :classname")})
public class TaskClass implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "CLASSNAME")
    private String classname;
    @JoinTable(name = "USERTASKCLASSES", joinColumns = {
        @JoinColumn(name = "TASKCLASSID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "USERID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<User> userCollection;
    @OneToMany(mappedBy = "taskclassid")
    private Collection<Codefile> codefileCollection;
    @JoinColumn(name = "TASKID", referencedColumnName = "ID")
    @ManyToOne
    private Task taskid;

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

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @XmlTransient
    public Collection<Codefile> getCodefileCollection() {
        return codefileCollection;
    }

    public void setCodefileCollection(Collection<Codefile> codefileCollection) {
        this.codefileCollection = codefileCollection;
    }

    public Task getTaskid() {
        return taskid;
    }

    public void setTaskid(Task taskid) {
        this.taskid = taskid;
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
        return "com.heinvdende.versionreview.db.rest.TaskClass[ id=" + id + " ]";
    }
    
}
