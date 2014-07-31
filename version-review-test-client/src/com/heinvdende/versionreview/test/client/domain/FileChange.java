/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.domain;

import java.util.Date;

/**
 *
 * @author Heinrich
 */
public class FileChange {
    public static final String TYPE_ADD = "added";
    public static final String TYPE_DEL = "deleted";
    public static final String TYPE_MOD = "modified";
    
    public static final int MARKER_HIGHLIGHT = 1;
    public static final int MARKER_UNDERLINE = 2;
    public static final int MARKER_NEW_FILE = 3;
    
    private long id;
    private int startLine;
    private int endLine;
    private String type;
    private String memberHeader;
    private Date date;
    private User user;
    private int markerType = 0;
    private String memberType;

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public int getMarkerType() {
        return markerType;
    }

    public void setMarkerType(int markerType) {
        this.markerType = markerType;
    }

    public String getMemberHeader() {
        return memberHeader;
    }

    public void setMemberHeader(String methodHeader) {
        this.memberHeader = methodHeader;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startPosition) {
        this.startLine = startPosition;
    }

    public int getEndLine() {
        return endLine;
    }

    public void setEndLine(int endPosition) {
        this.endLine = endPosition;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
