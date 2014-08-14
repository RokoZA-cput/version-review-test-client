/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.tests.module.repository.persistence;

import com.heinvdende.versionreview.test.modules.repository.domain.ClassMember;
import com.heinvdende.versionreview.test.modules.repository.domain.CodeFile;
import com.heinvdende.versionreview.test.modules.repository.persistence.CodeFileRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.MemberRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.factory.RepositoryFactory;
import java.util.ArrayList;
import java.util.Arrays;
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
public class CodeFileRepositoryTest {
    
    private static RepositoryFactory repoFactory;
    private static CodeFileRepository repo;
    private static MemberRepository memberRepo;
    private static CodeFile newCodeFile;
    
    public CodeFileRepositoryTest() {
    }

    @Test
    public void testCreate() {
        ClassMember member1 = new ClassMember();
        member1.setParentMember(null);
        member1.setStartLine(1);
        member1.setEndLine(2);
        member1.setMemberHeader("Header1");
        member1.setType("Type1");
        
        ClassMember member2 = new ClassMember();
        member2.setParentMember(null);
        member2.setStartLine(1);
        member2.setEndLine(2);
        member2.setMemberHeader("Header2");
        member2.setType("Type2");
        member2.setParentMember(member1);
        
        newCodeFile = new CodeFile();
        newCodeFile.setFilePath("File Path");
        newCodeFile.setVersion(1);
        newCodeFile.setClassMemberList(new ArrayList<>(Arrays.asList(member1, member2)));
        
        newCodeFile = repo.createEntity(newCodeFile);
        
        Assert.assertNotNull(newCodeFile.getId());
        
        repo.flush();
    }

    @Test(dependsOnMethods = {"testCreate"})
    public void testRead() {
        CodeFile codeFile = repo.getEntity(newCodeFile.getId());
        
        Assert.assertNotNull(codeFile);
        Assert.assertEquals(codeFile.getId(), newCodeFile.getId());
        Assert.assertEquals(codeFile.getClassMemberList().size(), newCodeFile.getClassMemberList().size());
        
        ClassMember member = memberRepo.getEntity(codeFile.getClassMemberList().get(0).getId());
        
        Assert.assertNotNull(member);
    }
    
    @Test(dependsOnMethods = {"testCreate"})
    public void testUpdate() {
        newCodeFile.setVersion(2);
        repo.updateEntity(newCodeFile);
        
        CodeFile codeFile = repo.getEntity(newCodeFile.getId());
        
        Assert.assertNotNull(codeFile);
        Assert.assertEquals(codeFile.getVersion(), newCodeFile.getVersion());
    }
    
    @Test(dependsOnMethods = {"testCreate", "testRead"})
    public void testDelete() {
        repo.removeEntity(newCodeFile);
        
        CodeFile codeFile = repo.getEntity(newCodeFile.getId());
        
        Assert.assertNull(codeFile);
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        repoFactory = new RepositoryFactory();
        repo = repoFactory.getCodeFileRepository();
        memberRepo = repoFactory.getMemberRepository();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        repo.commit();
        memberRepo.commit();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
