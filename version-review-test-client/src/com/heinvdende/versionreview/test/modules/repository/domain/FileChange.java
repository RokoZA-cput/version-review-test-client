/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Heinrich
 */
@Entity
@Table(name = "FILECHANGES")
@XmlRootElement
public class FileChange implements Serializable {
    public static final String TYPE_ADD = "added";
    public static final String TYPE_DEL = "deleted";
    public static final String TYPE_MOD = "modified";
    
    public static final int MARKER_NOT_USED = 0;
    public static final int MARKER_HIGHLIGHT = 1;
    public static final int MARKER_UNDERLINE = 2;
    public static final int MARKER_NEW_FILE = 3;
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Size(max = 15)
    @Column(name = "TYPE")
    private String type;
    
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @Column(name = "MARKERTYPE")
    private Integer markerType;
    
    @JoinColumn(name = "MEMBERID", referencedColumnName = "ID")
    @OneToOne
    private ClassMember classMember;
    
    @JoinColumn(name = "USERID", referencedColumnName = "ID")
    @ManyToOne
    private User user;
    
    @JoinColumn(name = "CODEFILEID", referencedColumnName = "ID")
    @ManyToOne
    private ChangedCodeFile codeFile;
    
    public FileChange() {
    }

    public FileChange(Integer id) {
        this.id = id;
    }

    public ChangedCodeFile getCodeFile() {
        return codeFile;
    }

    public void setCodeFile(ChangedCodeFile codeFile) {
        this.codeFile = codeFile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getMarkerType() {
        return markerType;
    }

    public void setMarkerType(Integer markertype) {
        this.markerType = markertype;
    }

    public ClassMember getClassMember() {
        return classMember;
    }

    public void setClassMember(ClassMember classMember) {
        this.classMember = classMember;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof FileChange)) {
            return false;
        }
        FileChange other = (FileChange) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.heinvdende.versionreview.test.modules.repository.domain.entities.FileChange[ id=" + id + " ]";
    }
    
}
