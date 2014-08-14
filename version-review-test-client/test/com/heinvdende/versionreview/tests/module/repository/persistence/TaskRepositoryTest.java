/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.tests.module.repository.persistence;

import com.heinvdende.versionreview.test.modules.repository.domain.Task;
import com.heinvdende.versionreview.test.modules.repository.persistence.TaskRepository;
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
public class TaskRepositoryTest {
    
    private static RepositoryFactory repoFactory;
    private static TaskRepository repo;
    private static Task newTask;
    
    public TaskRepositoryTest() {
    }

    @Test
    public void testCreate() {
        newTask = new Task();
        newTask.setName("Task");
        newTask.setDate(new Date());
        newTask.setDescription("Description");
        newTask.setIsFinished(false);

        newTask = repo.createEntity(newTask);
        
        Assert.assertNotNull(newTask.getId());

        repo.flush();
    }

    @Test(dependsOnMethods = {"testCreate"})
    public void testRead() {
        Task task = repo.getEntity(newTask.getId());
        
        Assert.assertNotNull(task);
        Assert.assertEquals(task.getId(), newTask.getId());
    }
    
    @Test(dependsOnMethods = {"testCreate"})
    public void testUpdate() {
        newTask.setDescription("Updated Description");
        repo.updateEntity(newTask);
        
        Task task = repo.getEntity(newTask.getId());
        
        Assert.assertNotNull(task);
        Assert.assertEquals(task.getDescription(), newTask.getDescription());
    }
    
    @Test(dependsOnMethods = {"testCreate", "testRead"})
    public void testDelete() {
        repo.removeEntity(newTask);
        
        Task task = repo.getEntity(newTask.getId());
        
        Assert.assertNull(task);
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        repoFactory = new RepositoryFactory();
        repo = repoFactory.getTaskRepository();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        repo.commit();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
