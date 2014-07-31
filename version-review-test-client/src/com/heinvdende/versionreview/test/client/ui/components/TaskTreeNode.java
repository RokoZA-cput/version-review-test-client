/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.ui.components;

import com.heinvdende.versionreview.test.client.domain.Task;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Heinrich
 */
public class TaskTreeNode extends DefaultMutableTreeNode {
    
    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    
    public TaskTreeNode() {
        super();
    }
    
    public TaskTreeNode(Object userObject) {
        super(userObject);
    }
    
    public TaskTreeNode(Task task) {
        super(task.getName());
        this.task = task;
    }
}
