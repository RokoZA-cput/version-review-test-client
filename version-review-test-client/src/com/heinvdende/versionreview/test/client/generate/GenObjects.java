/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.generate;

import com.heinvdende.versionreview.test.modules.repository.domain.MainTask;
import com.heinvdende.versionreview.test.modules.repository.domain.Project;
import com.heinvdende.versionreview.test.modules.repository.domain.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class GenObjects {
    
    private static GenObjects instance;
    
    private Project purchaseProject;
    private MainTask interfaceTask, calculateTask, vatTask, purchaseTask;
    private User johnUser, peterUser, steveUser;
    
    public User getUser(String user) {
        switch(user.toLowerCase()) {
            case "peter": return peterUser;
            case "john": return johnUser;
            case "steve": return steveUser;
        }
        
        return null;
    }
    
    public void setUser(User user, String name) {
        switch(name.toLowerCase()) {
            case "peter": peterUser = user; break;
            case "john": johnUser = user; break;
            case "steve": steveUser = user; break;
        }
    }
    
    public List<User> getUsers() {
        return new ArrayList<>(Arrays.asList(johnUser, peterUser, steveUser));
    }
    
    public List<MainTask> getTasks() {
        return new ArrayList<>(Arrays.asList(interfaceTask, calculateTask, vatTask, purchaseTask));
    }
    
    public MainTask getTask(String task) {
        switch(task.toLowerCase()) {
            case "interface": return interfaceTask;
            case "calculate": return calculateTask;
            case "vat": return vatTask;
            case "purchase": return purchaseTask;
        }
        
        return null;
    }
    
    public void setTask(MainTask task, String name) {
        switch(name.toLowerCase()) {
            case "interface": interfaceTask = task; break;
            case "calculate": calculateTask = task; break;
            case "vat": vatTask = task; break;
            case "purchase": purchaseTask = task; break;
        }
    }

    public Project getPurchaseProject() {
        return purchaseProject;
    }

    public void setPurchaseProject(Project purchaseProject) {
        this.purchaseProject = purchaseProject;
    }
    
    private GenObjects() {
        johnUser = new User();
        
        peterUser = new User();
        peterUser.setId(1);
        peterUser.setUsername("Peter");
        peterUser.setPassword("1234");
        
        johnUser.setId(2);
        johnUser.setUsername("John");
        johnUser.setPassword("1234");
        
        steveUser = new User();
        steveUser.setId(3);
        steveUser.setUsername("Steve");
        steveUser.setPassword("1234");
        
        interfaceTask = new MainTask();
        interfaceTask.setId(0);
        interfaceTask.setName("Interface");
        interfaceTask.setDate(new Date());
        interfaceTask.setDescription("Create the User Interface of the project and add calculations to user interface.");
        interfaceTask.setIsFinished(false);
        interfaceTask.setUsers(new ArrayList<>(Arrays.asList(peterUser, johnUser)));

        calculateTask = new MainTask();
        calculateTask.setId(1);
        calculateTask.setName("Calculate");
        calculateTask.setDate(new Date());
        calculateTask.setDescription("Create calculations functionality.");
        calculateTask.setIsFinished(false);
        calculateTask.setUsers(new ArrayList<>(Arrays.asList(johnUser, steveUser)));
        
        vatTask = new MainTask();
        vatTask.setId(1);
        vatTask.setName("VAT");
        vatTask.setDate(new Date());
        vatTask.setDescription("Create VAT calculations functionality.");
        vatTask.setIsFinished(false);
        vatTask.setUsers(new ArrayList<>(Arrays.asList(steveUser)));
        
        purchaseTask = new MainTask();
        purchaseTask.setId(1);
        purchaseTask.setName("Purchase");
        purchaseTask.setDate(new Date());
        purchaseTask.setDescription("Complete Purchase Project");
        purchaseTask.setIsFinished(false);
        purchaseTask.setUsers(new ArrayList<>(Arrays.asList(peterUser, johnUser, steveUser)));
        
        johnUser.setTasks(new ArrayList<>(Arrays.asList(interfaceTask, calculateTask, purchaseTask)));
        peterUser.setTasks(new ArrayList<>(Arrays.asList(interfaceTask, purchaseTask)));
        steveUser.setTasks(new ArrayList<>(Arrays.asList(vatTask, purchaseTask)));
        
        purchaseProject = new Project();
        purchaseProject.setId(1);
        purchaseProject.setName("Purchase Project");
        purchaseProject.setDescription("Implementing purchasing functionality");
        purchaseProject.setTasks(new ArrayList<>(Arrays.asList(interfaceTask, calculateTask, vatTask, purchaseTask)));
    }
    
    public static GenObjects getInstance() {
        if(instance == null)
            instance = new GenObjects();
        
        return instance;
    }
    
    
}
