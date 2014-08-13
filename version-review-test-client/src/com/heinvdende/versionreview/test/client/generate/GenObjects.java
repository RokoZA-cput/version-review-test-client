/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.generate;

import com.heinvdende.versionreview.test.modules.repository.domain.MainTask;
import com.heinvdende.versionreview.test.modules.repository.domain.Project;
import com.heinvdende.versionreview.test.modules.repository.domain.User;
import com.heinvdende.versionreview.test.modules.repository.persistence.ProjectRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.UserRepository;
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
        UserRepository userRep = new UserRepository();
        ProjectRepository projectRep = new ProjectRepository();
        
        peterUser = new User();
        peterUser.setUsername("Peter");
        peterUser.setPassword("1234");
        
        johnUser = new User();
        johnUser.setUsername("John");
        johnUser.setPassword("1234");
        
        steveUser = new User();
        steveUser.setUsername("Steve");
        steveUser.setPassword("1234");
        
        interfaceTask = new MainTask();
        interfaceTask.setName("Interface");
        interfaceTask.setDate(new Date());
        interfaceTask.setDescription("Create the User Interface of the project and add calculations to user interface.");
        interfaceTask.setIsFinished(false);
        interfaceTask.setUserList(new ArrayList<>(Arrays.asList(peterUser, johnUser)));

        calculateTask = new MainTask();
        calculateTask.setName("Calculate");
        calculateTask.setDate(new Date());
        calculateTask.setDescription("Create calculations functionality.");
        calculateTask.setIsFinished(false);
        calculateTask.setUserList(new ArrayList<>(Arrays.asList(johnUser, steveUser)));
        
        vatTask = new MainTask();
        vatTask.setName("VAT");
        vatTask.setDate(new Date());
        vatTask.setDescription("Create VAT calculations functionality.");
        vatTask.setIsFinished(false);
        vatTask.setUserList(new ArrayList<>(Arrays.asList(steveUser)));
        
        purchaseTask = new MainTask();
        purchaseTask.setName("Purchase");
        purchaseTask.setDate(new Date());
        purchaseTask.setDescription("Complete Purchase Project");
        purchaseTask.setIsFinished(false);
        purchaseTask.setUsers(new ArrayList<>(Arrays.asList(peterUser, johnUser, steveUser)));
        
        johnUser.setTaskList(new ArrayList<>(Arrays.asList(interfaceTask, calculateTask, purchaseTask)));
        peterUser.setTaskList(new ArrayList<>(Arrays.asList(interfaceTask, purchaseTask)));
        steveUser.setTaskList(new ArrayList<>(Arrays.asList(vatTask, purchaseTask)));
        
        steveUser = userRep.createEntity(steveUser);
        johnUser = userRep.createEntity(johnUser);
        peterUser = userRep.createEntity(peterUser);
        
        purchaseProject = new Project();
        purchaseProject.setName("Purchase Project");
        purchaseProject.setDescription("Implementing purchasing functionality");
        purchaseProject.setTaskList(new ArrayList<>(Arrays.asList(interfaceTask, calculateTask, vatTask, purchaseTask)));
        purchaseProject = projectRep.createEntity(purchaseProject);
    }
    
    public static GenObjects getInstance() {
        if(instance == null)
            instance = new GenObjects();
        
        return instance;
    }
    
    
}
