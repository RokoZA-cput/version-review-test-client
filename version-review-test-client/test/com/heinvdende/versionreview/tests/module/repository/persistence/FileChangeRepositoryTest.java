/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.tests.module.repository.persistence;

import com.heinvdende.versionreview.test.modules.repository.domain.FileChange;
import com.heinvdende.versionreview.test.modules.repository.domain.Project;
import com.heinvdende.versionreview.test.modules.repository.persistence.FileChangeRepository;
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
public class FileChangeRepositoryTest {
    
    private static RepositoryFactory repoFactory;
    private static FileChangeRepository repo;
    private static FileChange newChange;
    
    public FileChangeRepositoryTest() {
    }

    @Test
    public void testCreate() {
        // Test FileChange
        
        newChange = new FileChange();
        newChange.setDate(new Date());
        newChange.setMarkerType(FileChange.MARKER_HIGHLIGHT);
        newChange.setType(FileChange.TYPE_ADD);
        
        newChange = repo.createEntity(newChange);
        
        Assert.assertNotNull(newChange.getId());

        repo.flush();
    }

    @Test(dependsOnMethods = {"testCreate"})
    public void testRead() {
        FileChange change = repo.getEntity(newChange.getId());
        
        Assert.assertNotNull(change);
        Assert.assertEquals(change.getId(), newChange.getId());
    }
    
    @Test(dependsOnMethods = {"testCreate"})
    public void testUpdate() {
        newChange.setMarkerType(FileChange.MARKER_NEW_FILE);
        repo.updateEntity(newChange);
        
        FileChange change = repo.getEntity(newChange.getId());
        
        Assert.assertNotNull(change);
        Assert.assertEquals(change.getMarkerType(), newChange.getMarkerType());
    }
    
    @Test(dependsOnMethods = {"testCreate", "testRead"})
    public void testDelete() {
        repo.removeEntity(newChange);
        
        FileChange change = repo.getEntity(newChange.getId());
        
        Assert.assertNull(change);
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        repoFactory = new RepositoryFactory();
        repo = repoFactory.getFileChangeRepository();
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
