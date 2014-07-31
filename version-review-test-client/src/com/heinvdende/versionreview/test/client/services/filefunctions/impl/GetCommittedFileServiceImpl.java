/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.filefunctions.impl;

import com.heinvdende.versionreview.test.client.domain.ChangedCodeFile;
import com.heinvdende.versionreview.test.client.domain.CodeFile;
import com.heinvdende.versionreview.test.client.services.filefunctions.GetCommittedFileService;
import java.io.File;

/**
 *
 * @author Heinrich
 */

// This class should implement a strategy pattern, where GetCommittedFileService is the strategy interface
// The strategy pattern should choose which version control to use

// E.g. a SubVersoinFileService will implement GetCommittedFileService
//      a GitHubFileService will implement GetCommittedFileService

// A mediater class should be create that checks the configuration for what type of version control was used 
// and then the appropriate strat is chosen
public class GetCommittedFileServiceImpl implements GetCommittedFileService{

    @Override
    public File getCommittedFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String getFinalFile(String className) {
        String classPath = "C:\\Users\\Heinrich\\workspace\\purchase-test-app\\src\\com\\heinvdende\\test\\app\\purchase\\" + className;
        
        return classPath;
    }
    
    // Should be removed when finished testing
    @Override
    public String getCommittedFile(String file) {
        
        String folderName = "First Files";
        
        try {
            // find src folder, to get the FirstFiles folder in the same folder that contains src
            int index = file.indexOf("\\src");
            String dirPath = file.substring(0, index);

            String className = file.substring(file.lastIndexOf("\\")+1);
            String classPath = dirPath + "\\" + folderName + "\\" + className;

            File f = new File(classPath);
            if(!f.exists()) 
                return null;

            return classPath;
        }
        catch(StringIndexOutOfBoundsException e) {
            System.out.println("Could not find 'src' in file: " + file);
        }
        
        throw new StringIndexOutOfBoundsException();
    }
}
