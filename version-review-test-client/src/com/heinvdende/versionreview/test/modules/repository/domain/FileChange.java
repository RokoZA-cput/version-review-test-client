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
public class FileChange {
    public static final String TYPE_ADD = "added";
    public static final String TYPE_DEL = "deleted";
    public static final String TYPE_MOD = "modified";
    
    public static final int MARKER_NOT_USED = 0;
    public static final int MARKER_HIGHLIGHT = 1;
    public static final int MARKER_UNDERLINE = 2;
    public static final int MARKER_NEW_FILE = 3;
    
    private long id;
    private Member member;
    private Date date;
    private User user;
    private String type;
    private int markerType = 0;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getMarkerType() {
        return markerType;
    }

    public void setMarkerType(int markerType) {
        this.markerType = markerType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
