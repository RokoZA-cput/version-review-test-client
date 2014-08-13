/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.domain;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "MEMBERS")
@XmlRootElement
public class ClassMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "STARTLINE")
    private Integer startLine;
    
    @Column(name = "ENDLINE")
    private Integer endLine;
    
    @Size(max = 20)
    @Column(name = "TYPE")
    private String type;
    
    @Size(max = 30)
    @Column(name = "MEMBERHEADER")
    private String memberHeader;
    
    @OneToMany(cascade=CascadeType.PERSIST, mappedBy = "classMember")
    private List<ClassMember> classMemberList;
    
    @JoinColumn(name = "PARENTMEMBERID", referencedColumnName = "ID")
    @ManyToOne
    private ClassMember classMember;
    
    @JoinColumn(name = "CODEFILEID", referencedColumnName = "ID")
    @ManyToOne
    private CodeFile codeFile;

    public ClassMember() {
    }

    public ClassMember(Integer id) {
        this.id = id;
    }

    public ClassMember getParentMember() {
        return classMember;
    }

    public void setParentMember(ClassMember parentMember) {
        this.classMember = parentMember;
    }

    public CodeFile getCodeFile() {
        return codeFile;
    }

    public void setCodeFile(CodeFile codeFile) {
        this.codeFile = codeFile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStartLine() {
        return startLine;
    }

    public void setStartLine(Integer startline) {
        this.startLine = startline;
    }

    public Integer getEndLine() {
        return endLine;
    }

    public void setEndLine(Integer endline) {
        this.endLine = endline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMemberHeader() {
        return memberHeader;
    }

    public void setMemberHeader(String memberheader) {
        this.memberHeader = memberheader;
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
        if (!(object instanceof ClassMember)) {
            return false;
        }
        ClassMember other = (ClassMember) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.heinvdende.versionreview.test.modules.repository.domain.ClassMember[ id=" + id + " ]";
    }
    
}
