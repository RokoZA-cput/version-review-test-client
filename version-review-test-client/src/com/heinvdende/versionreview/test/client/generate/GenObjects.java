/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.generate;

import com.heinvdende.versionreview.test.modules.repository.domain.Task;
import com.heinvdende.versionreview.test.modules.repository.domain.Project;
import com.heinvdende.versionreview.test.modules.repository.domain.User;
import com.heinvdende.versionreview.test.modules.repository.persistence.ProjectRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.UserRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.factory.RepositoryFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;

/**
 *
 * @author Heinrich
 */
public class GenObjects {
    
    private static GenObjects instance;
    
    private Project purchaseProject;
    private Task interfaceTask, calculateTask, vatTask, purchaseTask;
    private User johnUser, peterUser, steveUser;
    
    public User getUser(String user){ 
        RepositoryFactory factory = new RepositoryFactory();
        UserRepository userRep = factory.getUserRepository(); 
        
        List<User> userList = userRep.getEntities();
        
        userRep.fetchAll(userList);
        userRep.commit();
        
        for(User u : userList) {
            if(u.getUsername().equalsIgnoreCase(user))
                return u;
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
    
    public List<Task> getTasks() {
        return new ArrayList<>(Arrays.asList(interfaceTask, calculateTask, vatTask, purchaseTask));
    }
    
    public Task getTask(String task) {
        switch(task.toLowerCase()) {
            case "interface": return interfaceTask;
            case "calculate": return calculateTask;
            case "vat": return vatTask;
            case "purchase": return purchaseTask;
        }
        
        return null;
    }
    
    public void setTask(Task task, String name) {
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
        RepositoryFactory factory = new RepositoryFactory();
        UserRepository userRep = factory.getUserRepository();
        ProjectRepository projRep = factory.getProjectRepository();
        
        peterUser = new User();
        peterUser.setUsername("Peter");
        peterUser.setPassword("1234");
        
        johnUser = new User();
        johnUser.setUsername("John");
        johnUser.setPassword("1234");
        
        steveUser = new User();
        steveUser.setUsername("Steve");
        steveUser.setPassword("1234");
        
        steveUser = userRep.createEntity(steveUser);
        johnUser = userRep.createEntity(johnUser);
        peterUser = userRep.createEntity(peterUser);
        
        userRep.commit();
        
        interfaceTask = new Task();
        interfaceTask.setName("Interface");
        interfaceTask.setDate(new Date());
        interfaceTask.setDescription("Create the User Interface of the project and add calculations to user interface.");
        interfaceTask.setIsFinished(false);
        interfaceTask.addUsers(new ArrayList<>(Arrays.asList(peterUser, johnUser)));

        calculateTask = new Task();
        calculateTask.setName("Calculate");
        calculateTask.setDate(new Date());
        calculateTask.setDescription("Create calculations functionality.");
        calculateTask.setIsFinished(false);
        calculateTask.addUsers(new ArrayList<>(Arrays.asList(johnUser, steveUser)));
        
        vatTask = new Task();
        vatTask.setName("VAT");
        vatTask.setDate(new Date());
        vatTask.setDescription("Create VAT calculations functionality.");
        vatTask.setIsFinished(false);
        vatTask.addUsers(new ArrayList<>(Arrays.asList(steveUser)));
        
        purchaseTask = new Task();
        purchaseTask.setName("Purchase");
        purchaseTask.setDate(new Date());
        purchaseTask.setDescription("Complete Purchase Project");
        purchaseTask.setIsFinished(false);
        purchaseTask.addUsers(new ArrayList<>(Arrays.asList(peterUser, johnUser, steveUser)));
        
        purchaseProject = new Project();
        purchaseProject.setName("Purchase Project");
        purchaseProject.setDescription("Implementing purchasing functionality");
        purchaseProject.setTaskList(new ArrayList<>(Arrays.asList(interfaceTask, calculateTask, vatTask, purchaseTask)));
        
        purchaseProject = projRep.createEntity(purchaseProject);
        
        projRep.commit();
    }
    
    public static GenObjects getInstance() {
        if(instance == null)
            instance = new GenObjects();
        
        return instance;
    }
    
    
}
