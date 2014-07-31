/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.filefunctions.impl;

import com.heinvdende.versionreview.test.client.domain.ChangedCodeFile;
import com.heinvdende.versionreview.test.client.domain.CodeFile;
import com.heinvdende.versionreview.test.client.domain.FileChange;
import com.heinvdende.versionreview.test.client.domain.MainTask;
import com.heinvdende.versionreview.test.client.domain.TaskClass;
import com.heinvdende.versionreview.test.client.domain.User;
import com.heinvdende.versionreview.test.client.services.filefunctions.CompareFilesService;
import com.heinvdende.versionreview.test.client.services.filefunctions.UpdateClasses;
import java.io.BufferedReader;
import java.io.FileReader;
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
public class UpdateClassesImpl implements UpdateClasses {

    // The path to the servers data folder (Could be in WebService's folder)
    private static final String DATA_PATH = "C:\\Users\\Heinrich\\workspace\\VersionReview\\Data\\";
    
    @Override
    public MainTask addClass(CodeFile file, MainTask task) {
        List<TaskClass> taskClasses = task.getClasses();
        
        User user = file.getUser();
        int index = file.getFilePath().lastIndexOf('\\');
        String name = file.getFilePath().substring(index+1);
        
        boolean isClassFound = false;
        boolean isUserFound = false;
        
        // Iterate through all existing classes to determine if the class exists
        for(TaskClass taskClass : taskClasses) {
            isClassFound = false;
            
            // Check if added class already exists
            if(name.equals(taskClass.getClassName())) {
                
                // Check if this user has used this class for this task before
                if(taskClass.getUserFinalFiles().containsKey(user)) {
                    
                    // Get all the files in this task that the specific user has worked on
                    List<CodeFile> allFiles = getAllFilesFromUser(taskClass.getAllFiles(), user.getUsername());
                    file.setVersion(allFiles.size() + 1);
                    String newPath = createNewFile(file);
                    //file.setFilePath(newPath);
                    
                    // Add new created version of file to list of files for TaskClass
                    taskClass.getAllFiles().add(file);

                    try {
                        
                        // Compare files and retrieve a ChangedJavaFile
                        ChangedCodeFile finalFile = getFinalMergedFile(file, taskClass, user);
                        
                        // Set user and Update the final file for specific user in the TaskClass
                        finalFile.setUser(user);
                        taskClass.getUserFinalFiles().replace(user, finalFile);
                    }
                    catch(Exception e) {
                        System.out.println(e.getMessage());
                    }

                    isUserFound = true;
                }
                isClassFound = true;
                // Experimental
                break;
            }
        }
        
        if(!isUserFound) {
            file.setVersion(1);
            //String newPath = createNewFile(file);
            //file.setFilePath(newPath);
            
            if(!isClassFound) {
                // If class was not found, create new TaskClass for the MainTask
                TaskClass newTaskClass = new TaskClass();
                newTaskClass.setClassName(name);
                
                // Compare files and retrieve a ChangedJavaFile
                ChangedCodeFile finalFile = getFinalMergedFile(file, newTaskClass, user);
                
                newTaskClass.getUserFinalFiles().put(user, finalFile);

                List<CodeFile> newFileList = new ArrayList<>();
                newFileList.add(file);

                newTaskClass.setAllFiles(newFileList);

                task.getClasses().add(newTaskClass);
            }
            else {
                // If class was found, just add update the final map with the user and file
                for(TaskClass taskClass : taskClasses) {
                    if(name.equals(taskClass.getClassName())) {
                        ChangedCodeFile finalFile = getFinalMergedFile(file, taskClass, user);
                        taskClass.getUserFinalFiles().put(user, finalFile);
                        taskClass.getAllFiles().add(file);
                    }
                }
            }
        }
        
        return task;
    }
    
    private ChangedCodeFile getFinalMergedFile(CodeFile file, TaskClass taskClass, User user) {
        try {
            // Compare files and retrieve a ChangedJavaFile
            CompareFilesService service = new CompareFilesServiceImpl();
            ChangedCodeFile newFile = service.getVersionChanged(file);
            
            // Before setting final file, the ChangedFile retrieved from getVersionChanged() will require all the old changes aswell,
            // because it will compare the new file to the committed version, therefore changes that has already been checked will not be included.
            // Don't use allFile[0] as the first parameter in getVersionChanged, because it will then pickup any other code that was committed by another user aswell
            ChangedCodeFile oldFile = (ChangedCodeFile) taskClass.getUserFinalFiles().get(user);
            return mergeChangedFiles(oldFile, newFile);
        } catch (Exception ex) {
            Logger.getLogger(UpdateClassesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private String createNewFile(CodeFile file) {
        // Data folder will be required
        
        PrintWriter pw = null;
        BufferedReader br = null;
        
        String timmedString = file.getFilePath().substring(file.getFilePath().lastIndexOf("\\"), file.getFilePath().indexOf(".java"));
        String newPath = DATA_PATH + timmedString + "_v" + file.getVersion() + ".java";
        
        try {
            br = new BufferedReader(new FileReader(file.getFilePath()));
            pw = new PrintWriter(newPath, "UTF-8");

            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                    pw.println(currentLine);
            }

            pw.close();
        } 
        catch (IOException e) {
                e.printStackTrace();
        } 
        finally {
            try 
            {
                if (br != null)
                    br.close();
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        return newPath;
    }
    
    private List<CodeFile> getAllFilesFromUser(List<CodeFile> files, String username) {
        List<CodeFile> newList = new ArrayList<>();
        
        for(CodeFile file : files) {
            if(file.getUser().getUsername().equals(username)) {
                newList.add(file);
            }
        }
        
        return newList;
    }
    
    // Add all the changes that was made in the oldFile to the newFile
    private ChangedCodeFile mergeChangedFiles(ChangedCodeFile oldFile, ChangedCodeFile newFile) {
        
        if(oldFile == null)
            return newFile;
        
        List<FileChange> newChanges = newFile.getChanges();
        newChanges.addAll(oldFile.getChanges());
        
        newFile.setChanges(newChanges);
        
        return newFile;
    }
}
