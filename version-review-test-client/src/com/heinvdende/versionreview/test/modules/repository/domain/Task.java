/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private Boolean isFinished;
    
    @JoinTable(name = "USERTASKS", joinColumns = {
        @JoinColumn(name = "USERID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "TASKID", referencedColumnName = "ID")})
    @ManyToMany //(fetch = FetchType.EAGER)
    private List<User> userList = new ArrayList<>();
    
    @ManyToOne
    private Project project;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "parentTask") //
    private List<Task> subTasks;
    
    @JoinColumn(name = "PARENTTASKID", referencedColumnName = "ID")
    @ManyToOne
    private Task parentTask;
    
    @JoinColumn(name = "TASKID", referencedColumnName = "ID")
    @OneToMany(cascade=CascadeType.ALL) //
    private List<TaskClass> taskClassList = new ArrayList<>();

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

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isfinished) {
        this.isFinished = isfinished;
    }

    public void addUser(User user) {
        this.getUserList().add(user);
        user.getTaskList().add(this);
    }
    
    public void addUsers(List<User> users) {
        for(User u : users) {
            addUser(u);
        }
    }
    
    public void removeUser(User user) {
        this.getUserList().remove(user);
        user.getTaskList().remove(this);
    }
    
    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<Task> subTasks) {
        this.subTasks = subTasks;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task task) {
        this.parentTask = task;
    }

    @XmlTransient
    public List<TaskClass> getTaskClassList() {
        return taskClassList;
    }

    public void addTaskClass(TaskClass taskClass) {
        this.getTaskClassList().add(taskClass);
        taskClass.setTask(this);
    }
    
    public void setTaskClassList(List<TaskClass> taskClassList) {
        this.taskClassList = taskClassList;
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
        return "com.heinvdende.versionreview.test.modules.repository.domain.entities.Task[ id=" + id + " ]";
    }
    
}
