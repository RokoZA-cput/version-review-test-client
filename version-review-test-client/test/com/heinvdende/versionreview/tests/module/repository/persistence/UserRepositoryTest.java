/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.tests.module.repository.persistence;

import com.heinvdende.versionreview.test.modules.repository.domain.Task;
import com.heinvdende.versionreview.test.modules.repository.domain.User;
import com.heinvdende.versionreview.test.modules.repository.persistence.TaskRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.UserRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.factory.RepositoryFactory;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Heinrich
 */
public class UserRepositoryTest {
    
    private static RepositoryFactory repo;
    private static UserRepository userRepo;
    private static TaskRepository taskRepo;

    private static User newUser;
    private static Task newTask;
    
    public UserRepositoryTest() {
    }

    @Test
    public void testCreate() {
        Task task = new Task();
        task.setName("Task");
        task.setDate(new Date());
        task.setDescription("Description");
        task.setIsFinished(false);
        
        newUser = new User();
        newUser.setUsername("Username");
        newUser.setPassword("Password");
        newUser.addTask(task);
        
        newUser = userRepo.createEntity(newUser);
        
        Assert.assertNotNull(newUser.getId());
        
        newTask = new Task();
        newTask.setName("Task");
        newTask.setDate(new Date());
        newTask.setDescription("Description");
        newTask.setIsFinished(false);

        newTask = taskRepo.createEntity(newTask);
        
        Assert.assertNotNull(newTask.getId());
        
        taskRepo.commit();
        userRepo.flush();
    }

    @Test(dependsOnMethods = {"testCreate"})
    public void testRead() {
        User user = userRepo.getEntity(newUser.getId());
        
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId(), newUser.getId());
    }
    
    @Test(dependsOnMethods = {"testCreate"})
    public void testUpdate() {
        newUser.addTask(newTask);
        
        userRepo.updateEntity(newUser);
        
        User user = userRepo.getEntity(newUser.getId());
        
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getTaskList().size(), newUser.getTaskList().size());
    }
    
    @Test(dependsOnMethods = {"testCreate", "testRead"})
    public void testDelete() {
        userRepo.removeEntity(newUser);
        
        User user = userRepo.getEntity(newUser.getId());
        
        Assert.assertNull(user);
        
        // Check if Task still exists
        taskRepo = repo.getTaskRepository();
        Task task = taskRepo.getEntity(newTask.getId());
        
        Assert.assertNotNull(task);
        
        taskRepo.removeEntity(task);
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        repo = new RepositoryFactory();
        userRepo = repo.getUserRepository();
        taskRepo = repo.getTaskRepository();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        userRepo.commit();
        taskRepo.commit();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
