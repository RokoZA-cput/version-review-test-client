/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.domain;

import java.util.Date;

/**
 *
 * @author Heinrich
 */
public class Member {
    private long id;
    private int startLine;
    private int endLine;
    private String type;
    private String memberHeader;
    private Member parentMember;
    private CodeFile file;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
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

    public void setMemberHeader(String memberHeader) {
        this.memberHeader = memberHeader;
    }

    public Member getParentMember() {
        return parentMember;
    }

    public void setParentMember(Member parentMember) {
        this.parentMember = parentMember;
    }

    public CodeFile getFile() {
        return file;
    }

    public void setFile(CodeFile file) {
        this.file = file;
    }
}
