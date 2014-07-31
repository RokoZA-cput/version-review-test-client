/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class MainTask extends Task {
    private List<SubTask> subTasks = new ArrayList<>();
    private List<TaskClass> classes = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

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
