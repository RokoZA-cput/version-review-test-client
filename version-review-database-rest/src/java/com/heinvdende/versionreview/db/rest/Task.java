/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.db.rest;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Heinrich
 */
@Entity
@Table(name = "TASKS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.id = :id"),
    @NamedQuery(name = "Task.findByName", query = "SELECT t FROM Task t WHERE t.name = :name"),
    @NamedQuery(name = "Task.findByDescription", query = "SELECT t FROM Task t WHERE t.description = :description"),
    @NamedQuery(name = "Task.findByDate", query = "SELECT t FROM Task t WHERE t.date = :date"),
    @NamedQuery(name = "Task.findByIsfinished", query = "SELECT t FROM Task t WHERE t.isfinished = :isfinished"),
    @NamedQuery(name = "Task.findByIsfix", query = "SELECT t FROM Task t WHERE t.isfix = :isfix")})
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Size(max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "ISFINISHED")
    private Boolean isfinished;
    @Column(name = "ISFIX")
    private Boolean isfix;
    @JoinColumn(name = "PROJECTID", referencedColumnName = "ID")
    @ManyToOne
    private Project projectid;
    @OneToMany(mappedBy = "parenttaskid")
    private Collection<Task> taskCollection;
    @JoinColumn(name = "PARENTTASKID", referencedColumnName = "ID")
    @ManyToOne
    private Task parenttaskid;
    @OneToMany(mappedBy = "taskid")
    private Collection<TaskClass> taskClassCollection;

    public Task() {
    }

    public Task(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getIsfinished() {
        return isfinished;
    }

    public void setIsfinished(Boolean isfinished) {
        this.isfinished = isfinished;
    }

    public Boolean getIsfix() {
        return isfix;
    }

    public void setIsfix(Boolean isfix) {
        this.isfix = isfix;
    }

    public Project getProjectid() {
        return projectid;
    }

    public void setProjectid(Project projectid) {
        this.projectid = projectid;
    }

    @XmlTransient
    public Collection<Task> getTaskCollection() {
        return taskCollection;
    }

    public void setTaskCollection(Collection<Task> taskCollection) {
        this.taskCollection = taskCollection;
    }

    public Task getParenttaskid() {
        return parenttaskid;
    }

    public void setParenttaskid(Task parenttaskid) {
        this.parenttaskid = parenttaskid;
    }

    @XmlTransient
    public Collection<TaskClass> getTaskClassCollection() {
        return taskClassCollection;
    }

    public void setTaskClassCollection(Collection<TaskClass> taskClassCollection) {
        this.taskClassCollection = taskClassCollection;
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
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.heinvdende.versionreview.db.rest.Task[ id=" + id + " ]";
    }
    
}
