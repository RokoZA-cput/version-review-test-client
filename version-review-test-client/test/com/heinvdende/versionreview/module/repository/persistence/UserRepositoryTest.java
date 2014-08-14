/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.module.repository.persistence;

import com.heinvdende.versionreview.test.modules.repository.domain.Task;
import com.heinvdende.versionreview.test.modules.repository.domain.User;
import com.heinvdende.versionreview.test.modules.repository.persistence.TaskRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.UserRepository;
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
    
    public UserRepositoryTest() {
    }

    @Test
    public void testCrud() {
        TaskRepository taskRepo = new TaskRepository();
        
        // Create Task
        Task task = new Task();
        task.setName("Task Name");
        task.setDate(new Date());
        task.setDescription("Task Description");
        task.setIsFinished(false);
        
        task = taskRepo.createEntity(task);
        
        // Create User
        UserRepository repo = new UserRepository();
        
        User user = new User();
        user.setUsername("Username");
        user.setPassword("Password");
        user.addTask(task);
        
        user = repo.createEntity(user);
        
        Assert.assertNotNull(user.getId());

        // Read
        User readUser = repo.getEntity(user.getId());
        
        Assert.assertNotNull(readUser);
        
        // Check if tasks was correctly persisted
        Assert.assertEquals(readUser.getTaskList().size(), 1);
        
        // Update
        user.setPassword("Test Updated");
        
        User updatedUser = repo.updateEntity(user);
        
        Assert.assertEquals(updatedUser.getPassword(), "Test Updated");
        
        // Remove task
        user.removeTask(task);
        
        repo.updateEntity(user);
        
        readUser = repo.getEntity(user.getId());
        Assert.assertEquals(readUser.getTaskList().size(), 0);
        
        // Delete User
        
        repo.removeEntity(user);
        
        User deleteUser = repo.getEntity(user.getId());
        Assert.assertNull(deleteUser);

        taskRepo.removeEntity(task);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
