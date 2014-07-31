/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.codechecker.strategy.changes;

import com.heinvdende.versionreview.test.client.domain.FileChange;
import com.heinvdende.versionreview.test.client.services.codechecker.StrategyChooser;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.check.CheckClass;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.check.CodeChecker;
import com.heinvdende.versionreview.test.factories.FileChangeFactory;
import japa.parser.ast.Node;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Heinrich
 */
public class ClassChanges implements ChangesStrategy {
    
    @Override
    public List<FileChange> doCheck(Node oldNode, Node newNode) {
        // Check for correct types
        if(!isInstanceOf(oldNode) || !isInstanceOf(newNode) || newNode == null)
            return null;
        
        CodeChecker checker;
        
        List<FileChange> list = new ArrayList<>();
        
        // Get Class Changes
        // Set strategy
        checker = new CodeChecker(new CheckClass());
        
        ClassOrInterfaceDeclaration oldClass = (ClassOrInterfaceDeclaration) oldNode;
        ClassOrInterfaceDeclaration newClass = (ClassOrInterfaceDeclaration) newNode;

        ChangesStrategy strat;
        boolean memberFound;
        List<FileChange> tmpList = new ArrayList<>();
        if(checker.doCheck(oldClass, newClass)) {
            List<BodyDeclaration> oldMembers = oldClass.getMembers();
            List<BodyDeclaration> newMembers = newClass.getMembers();

            for(BodyDeclaration newMember : newMembers) {
                memberFound = false;

                // Get changes for each member
                strat = StrategyChooser.getChangeStrategy(newMember);

                if(strat != null) {
                    for(BodyDeclaration oldMember : oldMembers) {
                        tmpList = strat.doCheck(oldMember, newMember);
                        if(tmpList != null) {
                            memberFound = true;
                            list.addAll(tmpList);
                            break;
                        }
                    }

                    if(!memberFound) {
                        try {
                            list.add(FileChangeFactory.getFileChange(newMember, strat.getMemberHeader(newMember), FileChange.TYPE_ADD));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
        else
            list.add(FileChangeFactory.getFileChange(newClass, getMemberHeader(newClass), FileChange.TYPE_ADD));
        
        return list;
        
    }

    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof ClassOrInterfaceDeclaration || node == null)
            return true;
        
        return false;   
    }

    @Override
    public String getMemberHeader(Node node) {
        
        if(isInstanceOf(node)) {
            ClassOrInterfaceDeclaration member = (ClassOrInterfaceDeclaration) node;
            
            // Use the '{' character to determine the end of the method header
            String methodString = member.toString();
            int index = methodString.indexOf('{');
            return methodString.substring(0, index);
        }
        
        throw new RuntimeException("Not correct Node type.");
    }
}
