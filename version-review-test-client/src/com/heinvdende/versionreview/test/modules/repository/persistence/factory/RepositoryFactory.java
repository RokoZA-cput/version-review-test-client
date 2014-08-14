/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.repository.persistence.factory;

import com.heinvdende.versionreview.test.modules.repository.persistence.AbstractRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.CodeFileRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.FileChangeRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.hibernate.HibernateUtil;
import com.heinvdende.versionreview.test.modules.repository.persistence.MemberRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.ProjectRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.TaskClassRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.TaskRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.UserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Heinrich
 */
public class RepositoryFactory {
    
    private Transaction transaction;
    private Session session;
    
    // Commit Transaction & End Session
    public void commit() {
        try {
            getTransaction().commit();
        }
        catch(RuntimeException e) {
            System.out.println(e.getStackTrace());
            getTransaction().rollback();  
        }  
        finally {  
            getSession().close();  
        }
    }
    
    // Repositories
    public CodeFileRepository getCodeFileRepository() {
        return (CodeFileRepository) instantiateDAO(CodeFileRepository.class); 
    }
    
    public FileChangeRepository getFileChangeRepository() {
        return (FileChangeRepository) instantiateDAO(FileChangeRepository.class);
    }
    
    public MemberRepository getMemberRepository() {
        return (MemberRepository) instantiateDAO(MemberRepository.class);
    }
    
    public ProjectRepository getProjectRepository() {
        return (ProjectRepository) instantiateDAO(ProjectRepository.class);
    }
    
    public TaskClassRepository getTaskClassRepository() {
        return (TaskClassRepository) instantiateDAO(TaskClassRepository.class);
    }
    
    public TaskRepository getTaskRepository() {
        return (TaskRepository) instantiateDAO(TaskRepository.class);
    }
    
    public UserRepository getUserRepository() {
        return (UserRepository) instantiateDAO(UserRepository.class);
    }
    
    private AbstractRepository instantiateDAO(Class daoClass) {  
        try {  
            AbstractRepository dao = (AbstractRepository) daoClass.newInstance();  
            // Open Session
            openSession();
            // Add session to DAO
            dao.setSession(getSession());
            // Start and set Transaction after session is opened
            setTransaction(getSession().beginTransaction());
            return dao;  
        } catch (Exception ex) {  
            throw new RuntimeException("Can not instantiate DAO: " + daoClass, ex);  
        }  
    }  
  
    private void setTransaction(Transaction t) {
        this.transaction = t;
    }
    
    private Transaction getTransaction() {
        return this.transaction;
    }
    
    // Creates Hibernet Session
    private void openSession() {
        setSession(HibernateUtil.getSessionFactory().openSession());
    } 

    public Session getSession() {
        if(session == null) 
            throw new IllegalStateException("Session has not been opened");
            
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
}
