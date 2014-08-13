/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.db.rest;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
    @NamedQuery(name = "Filechange.findAll", query = "SELECT f FROM Filechange f"),
    @NamedQuery(name = "Filechange.findById", query = "SELECT f FROM Filechange f WHERE f.id = :id"),
    @NamedQuery(name = "Filechange.findByStartline", query = "SELECT f FROM Filechange f WHERE f.startline = :startline"),
    @NamedQuery(name = "Filechange.findByEndline", query = "SELECT f FROM Filechange f WHERE f.endline = :endline"),
    @NamedQuery(name = "Filechange.findByType", query = "SELECT f FROM Filechange f WHERE f.type = :type"),
    @NamedQuery(name = "Filechange.findByMethodheader", query = "SELECT f FROM Filechange f WHERE f.methodheader = :methodheader"),
    @NamedQuery(name = "Filechange.findByDate", query = "SELECT f FROM Filechange f WHERE f.date = :date"),
    @NamedQuery(name = "Filechange.findByMarkertype", query = "SELECT f FROM Filechange f WHERE f.markertype = :markertype"),
    @NamedQuery(name = "Filechange.findByMembertype", query = "SELECT f FROM Filechange f WHERE f.membertype = :membertype")})
public class Filechange implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "STARTLINE")
    private Integer startline;
    @Column(name = "ENDLINE")
    private Integer endline;
    @Size(max = 15)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 100)
    @Column(name = "METHODHEADER")
    private String methodheader;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "MARKERTYPE")
    private Integer markertype;
    @Size(max = 15)
    @Column(name = "MEMBERTYPE")
    private String membertype;
    @JoinColumn(name = "CODEFILEID", referencedColumnName = "ID")
    @ManyToOne
    private Codefile codefileid;
    @JoinColumn(name = "USERID", referencedColumnName = "ID")
    @ManyToOne
    private User userid;

    public Filechange() {
    }

    public Filechange(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStartline() {
        return startline;
    }

    public void setStartline(Integer startline) {
        this.startline = startline;
    }

    public Integer getEndline() {
        return endline;
    }

    public void setEndline(Integer endline) {
        this.endline = endline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethodheader() {
        return methodheader;
    }

    public void setMethodheader(String methodheader) {
        this.methodheader = methodheader;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getMarkertype() {
        return markertype;
    }

    public void setMarkertype(Integer markertype) {
        this.markertype = markertype;
    }

    public String getMembertype() {
        return membertype;
    }

    public void setMembertype(String membertype) {
        this.membertype = membertype;
    }

    public Codefile getCodefileid() {
        return codefileid;
    }

    public void setCodefileid(Codefile codefileid) {
        this.codefileid = codefileid;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
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
        if (!(object instanceof Filechange)) {
            return false;
        }
        Filechange other = (Filechange) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.heinvdende.versionreview.db.rest.Filechange[ id=" + id + " ]";
    }
    
}
