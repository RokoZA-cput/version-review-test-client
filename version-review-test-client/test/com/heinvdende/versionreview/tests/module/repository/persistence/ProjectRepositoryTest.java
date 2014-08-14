/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.tests.module.repository.persistence;

import com.heinvdende.versionreview.test.modules.repository.domain.Project;
import com.heinvdende.versionreview.test.modules.repository.persistence.ProjectRepository;
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
public class ProjectRepositoryTest {
    
    private static RepositoryFactory repoFactory;
    private static ProjectRepository repo;
    private static Project newProject;
    
    public ProjectRepositoryTest() {
    }

    @Test
    public void testCreate() {
        newProject = new Project();
        newProject.setName("Test Project");
        newProject.setDescription("Testing CRUD of Project");
        
        newProject = repo.createEntity(newProject);
        
        Assert.assertNotNull(newProject.getId());
        
        repo.flush();
    }

    @Test(dependsOnMethods = {"testCreate"})
    public void testRead() {
        Project project = repo.getEntity(newProject.getId());
        
        Assert.assertNotNull(project);
        Assert.assertEquals(project.getId(), newProject.getId());
    }
    
    @Test(dependsOnMethods = {"testCreate"})
    public void testUpdate() {
        newProject.setDescription("Updated Description");
        repo.updateEntity(newProject);
        
        Project project = repo.getEntity(newProject.getId());
        
        Assert.assertNotNull(project);
        Assert.assertEquals(project.getDescription(), newProject.getDescription());
    }
    
    @Test(dependsOnMethods = {"testCreate", "testRead"})
    public void testDelete() {
        repo.removeEntity(newProject);
        
        Project project = repo.getEntity(newProject.getId());
        
        Assert.assertNull(project);
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        repoFactory = new RepositoryFactory();
        repo = repoFactory.getProjectRepository();
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
