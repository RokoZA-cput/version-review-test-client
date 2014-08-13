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
@Table(name = "CODEFILES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Codefile.findAll", query = "SELECT c FROM Codefile c"),
    @NamedQuery(name = "Codefile.findById", query = "SELECT c FROM Codefile c WHERE c.id = :id"),
    @NamedQuery(name = "Codefile.findByFilepath", query = "SELECT c FROM Codefile c WHERE c.filepath = :filepath"),
    @NamedQuery(name = "Codefile.findByVersion", query = "SELECT c FROM Codefile c WHERE c.version = :version")})
public class Codefile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 500)
    @Column(name = "FILEPATH")
    private String filepath;
    @Column(name = "VERSION")
    private Integer version;
    @JoinColumn(name = "TASKCLASSID", referencedColumnName = "ID")
    @ManyToOne
    private TaskClass taskclassid;
    @JoinColumn(name = "USERID", referencedColumnName = "ID")
    @ManyToOne
    private User userid;
    @OneToMany(mappedBy = "codefileid")
    private Collection<Filechange> filechangeCollection;

    public Codefile() {
    }

    public Codefile(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public TaskClass getTaskclassid() {
        return taskclassid;
    }

    public void setTaskclassid(TaskClass taskclassid) {
        this.taskclassid = taskclassid;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    @XmlTransient
    public Collection<Filechange> getFilechangeCollection() {
        return filechangeCollection;
    }

    public void setFilechangeCollection(Collection<Filechange> filechangeCollection) {
        this.filechangeCollection = filechangeCollection;
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
        if (!(object instanceof Codefile)) {
            return false;
        }
        Codefile other = (Codefile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.heinvdende.versionreview.db.rest.Codefile[ id=" + id + " ]";
    }
    
}
