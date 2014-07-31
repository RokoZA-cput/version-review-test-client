/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.codechecker.strategy.changes;

import com.heinvdende.versionreview.test.client.domain.FileChange;
import com.heinvdende.versionreview.test.client.services.codechecker.StrategyChooser;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.check.CodeChecker;
import com.heinvdende.versionreview.test.factories.FileChangeFactory;
import japa.parser.ast.Node;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.stmt.BlockStmt;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Heinrich
 */
public class MethodChanges implements ChangesStrategy {
    
    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof MethodDeclaration)
            return true;
        
        return false;   
    }

    @Override
    public List<FileChange> doCheck(Node oldNode, Node newNode) {
        List<FileChange> list = null;
        
        if(!isInstanceOf(oldNode) || !isInstanceOf(newNode))
            return list;
        
        CodeChecker checker;
        
        if(newNode != null) {
            // Get Method Changes
            // Set strategy
            checker = new CodeChecker(StrategyChooser.getCheckStrategy(newNode));

            MethodDeclaration oldMethod = (MethodDeclaration) oldNode;
            MethodDeclaration newMethod = (MethodDeclaration) newNode;

            List<Parameter> oldParams = null;
            List<AnnotationExpr> oldAnno = null;
            BlockStmt oldBody = null;
            
            if(checker.doCheck(oldMethod, newMethod)) {
                // Instantiate new List when node is found, used to check if node was found: (if node != null) then found
                list = new ArrayList<>();

                // Check parameters
                oldParams = oldMethod.getParameters();

                // Check annotations
                oldAnno = oldMethod.getAnnotations();

                // Check body
                oldBody = oldMethod.getBody();
            }
            // All nodes should be added as changes
            /*else if(oldNode == null) {
                // Instantiate new List when node is found, used to check if node was found: (if node != null) then found
                list = new ArrayList<>();
            }*/
            
            if(list != null) {
                // Check parameters
                List<Parameter> newParams = oldMethod.getParameters();
                list.addAll(GetMembersChanges.getMemberListChanges(oldParams, newParams));

                // Check annotations
                List<AnnotationExpr> newAnno = newMethod.getAnnotations();
                list.addAll(GetMembersChanges.getMemberListChanges(oldAnno, newAnno));

                // Check body
                BlockStmt newBody = newMethod.getBody();
                list.addAll(GetMembersChanges.getSingleMemberChanges(oldBody, newBody));
                
                if(oldNode != null) {
                    // Check if any changes occurred to the method
                    if(list.size() > 0) {
                        list.add(FileChangeFactory.getFileChange(newMethod, getMemberHeader(newMethod), FileChange.TYPE_MOD));
                    }
                }
            }
        }
        
        return list;
    }

    @Override
    public String getMemberHeader(Node node) {
        if(node instanceof MethodDeclaration) {
            MethodDeclaration method = (MethodDeclaration) node;
            
            // Use the ')' character to determine the end of the method header
            String methodString = method.toString();
            int index = methodString.indexOf(')') + 1;
            return methodString.substring(0, index);
        }
        
        throw new RuntimeException("Not correct Node type.");
    }

}
