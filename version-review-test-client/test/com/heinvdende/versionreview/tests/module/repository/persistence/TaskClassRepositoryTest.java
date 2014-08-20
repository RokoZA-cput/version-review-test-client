/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.tests.module.repository.persistence;

import com.heinvdende.versionreview.test.modules.repository.domain.TaskClass;
import com.heinvdende.versionreview.test.modules.repository.persistence.TaskClassRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.factory.RepositoryFactory;
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
public class TaskClassRepositoryTest {
    
    private static RepositoryFactory repoFactory;
    private static TaskClassRepository repo;
    private static TaskClass newTaskClass;
    
    public TaskClassRepositoryTest() {
    }

    @Test
    public void testCreate() {
        newTaskClass = new TaskClass();
        newTaskClass.setClassName("Class Name");
        
        newTaskClass = repo.createEntity(newTaskClass);
        
        Assert.assertNotNull(newTaskClass.getId());
        
        repo.flush();
    }

    @Test(dependsOnMethods = {"testCreate"})
    public void testRead() {
        TaskClass taskClass = repo.getEntity(newTaskClass.getId());
        
        Assert.assertNotNull(taskClass);
        Assert.assertEquals(taskClass.getId(), newTaskClass.getId());
    }
    
    @Test(dependsOnMethods = {"testCreate"})
    public void testUpdate() {
        newTaskClass.setClassName("Updated Class Name");
        repo.updateEntity(newTaskClass);
        
        TaskClass taskClass = repo.getEntity(newTaskClass.getId());
        
        Assert.assertNotNull(taskClass);
        Assert.assertEquals(taskClass.getClassName(), newTaskClass.getClassName());
    }
    
    @Test(dependsOnMethods = {"testCreate", "testRead"})
    public void testDelete() {
        repo.removeEntity(newTaskClass);
        
        TaskClass taskClass = repo.getEntity(newTaskClass.getId());
        
        Assert.assertNull(taskClass);
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        repoFactory = new RepositoryFactory();
        repo = repoFactory.getTaskClassRepository();
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
