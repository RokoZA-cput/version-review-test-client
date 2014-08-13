/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class MainTask extends Task {
    private List<TaskClass> classes = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public List<TaskClass> getClasses() {
        return classes;
    }

    public void setClasses(List<TaskClass> classes) {
        this.classes = classes;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
