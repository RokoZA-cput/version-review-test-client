/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.filefunctions.impl;

import com.heinvdende.versionreview.test.client.domain.ChangedCodeFile;
import com.heinvdende.versionreview.test.client.domain.FileChange;
import com.heinvdende.versionreview.test.client.domain.CodeFile;
import com.heinvdende.versionreview.test.client.domain.TaskClass;
import com.heinvdende.versionreview.test.client.services.membervisitors.ClassVisitor;
import com.heinvdende.versionreview.test.client.services.membervisitors.FinalMemberVisitor;
import com.heinvdende.versionreview.test.client.services.codechecker.GetFileChangesService;
import com.heinvdende.versionreview.test.client.services.codechecker.GetFileChangesServiceImpl;
import com.heinvdende.versionreview.test.client.services.filefunctions.CompareFilesService;
import com.heinvdende.versionreview.test.client.services.filefunctions.GetCommittedFileService;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Heinrich
 */
public class CompareFilesServiceImpl implements CompareFilesService {

    @Override
    public TaskClass highlightFileChanges(TaskClass task) {
        // Using the list of java files in the task object
        // Determine using some algorithm the orignial and final JavaFile objects
        // With the final highlighting all the changes and the users responsible for the changes, so it will be a ChangedJavaFile
        // And the original highlighting all the deletions and additions, so it will also be a ChangedJavaFile
        
        // Retrieve current file from SVN, if all is done correctly this should be the Final File
        GetCommittedFileService service = new GetCommittedFileServiceImpl();
        String finalFilePath = service.getFinalFile(task.getClassName());

        // Using the FinalMethodVisitor, retrieve the ChangedCodeFile
        CompilationUnit cu = getCompulationUnit(finalFilePath); 
        
        FinalMemberVisitor visitor = new FinalMemberVisitor();
        visitor.visit(cu, task);
        
        ChangedCodeFile changedFile = visitor.getFinalFile();
        changedFile.setFilePath(finalFilePath);
        task.setFinalFile(changedFile);
        
        return task;
    }

    @Override
    public ChangedCodeFile getVersionChanged(CodeFile oldVersion, CodeFile newVersion) {
        // Using the same alogrithm as above
        // Take two versions of a file and highlight all changes
        
        ChangedCodeFile file = new ChangedCodeFile();
        file.setFilePath(newVersion.getFilePath());
        file.setVersion(newVersion.getVersion());
        file.setChanges(getChanges(oldVersion, newVersion));
        
        return file;
    }
    
    // Uses SVN as old version
    @Override
    public ChangedCodeFile getVersionChanged(CodeFile newVersion) {
        // Using the same alogrithm as above
        // Take two versions of a file and highlight all changes
        
        // Use the committed file as the old version
        GetCommittedFileService service = new GetCommittedFileServiceImpl();
        String oldFilePath = service.getCommittedFile(newVersion.getFilePath());
        
        CodeFile oldVersion = null;
        if(oldFilePath == null) {
            oldVersion = createDummyFile();
        }
        else {
            oldVersion = new CodeFile();
            oldVersion.setFilePath(oldFilePath);
        }
        
        ChangedCodeFile file = new ChangedCodeFile();
        file.setFilePath(newVersion.getFilePath());
        file.setVersion(newVersion.getVersion());
        file.setChanges(getChanges(oldVersion, newVersion));
        
        return file;
    }

    @Override
    public ChangedCodeFile getVersionDeletions(CodeFile oldVersion, CodeFile newVersion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private List<FileChange> getChanges(CodeFile oldVersion, CodeFile newVersion){

        ClassOrInterfaceDeclaration oldClass = getClassDeclarations(oldVersion.getFilePath());
        ClassOrInterfaceDeclaration newClass = getClassDeclarations(newVersion.getFilePath());
        
        GetFileChangesService service = new GetFileChangesServiceImpl(oldClass, newClass);
        
        List<FileChange> fileChanges = service.getFileChanges();
        
        return fileChanges;
    }
    
    private ClassOrInterfaceDeclaration getClassDeclarations(String path) {
        CompilationUnit cu = getCompulationUnit(path); 
        
        ClassVisitor visitor = new ClassVisitor();
        visitor.visit(cu, null);
        
        return visitor.getFileClass();
    }
    
    private CompilationUnit getCompulationUnit(String file) {
        FileInputStream in = null;
        CompilationUnit cu = null;
        FileNotFoundException exception = new FileNotFoundException("Was not file exception.");
        try {
            in = new FileInputStream(file);
            
        
            if(in != null) {
                cu = JavaParser.parse(in);  
                in.close();
            
                return cu;
            }
        }
        catch(FileNotFoundException ex) {
            exception = ex;
        }
        catch (ParseException ex) {
            Logger.getLogger(CompareFilesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            Logger.getLogger(CompareFilesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        throw new RuntimeException("File Exception:\n" + exception.getMessage());
    }
    
    private CodeFile createDummyFile() {
        PrintWriter pw = null;
        CodeFile file = null;
        
        try {
            pw = new PrintWriter("\\dummy.java", "UTF-8");
            pw.println("");
            pw.close();
            
            file = new CodeFile();
            file.setFilePath("\\dummy.java");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return file;
    }
}
