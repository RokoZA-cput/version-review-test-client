/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.codechecker.strategy.changes;

import com.heinvdende.versionreview.test.client.domain.FileChange;
import com.heinvdende.versionreview.test.client.services.codechecker.StrategyChooser;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.check.*;
import com.heinvdende.versionreview.test.factories.FileChangeFactory;
import japa.parser.ast.Node;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.stmt.BlockStmt;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class ConstructorChanges implements ChangesStrategy {

    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof ConstructorDeclaration)
            return true;
        
        return false;   
    }

    @Override
    public List<FileChange> doCheck(Node oldNode, Node newNode) {
        List<FileChange> list = null;
        
        if(!isInstanceOf(oldNode) || !isInstanceOf(newNode))
            return list;
        
        CodeChecker checker;
        
        // Get Constructor Changes
        // Set strategy
        checker = new CodeChecker(StrategyChooser.getCheckStrategy(newNode));
        
        ConstructorDeclaration oldCon = (ConstructorDeclaration) oldNode;
        ConstructorDeclaration newCon = (ConstructorDeclaration) newNode;
        
        if(checker.doCheck(oldCon, newCon)) {
            // Instantiate new List when node is found, used to check if node was found: (if node != null) then found
            list = new ArrayList<>();
            
            // Check parameters
            List<Parameter> oldParams = oldCon.getParameters();
            List<Parameter> newParams = newCon.getParameters();

            list.addAll(GetMembersChanges.getMemberListChanges(oldParams, newParams));
            
            // Check annotations
            List<AnnotationExpr> oldAnno = oldCon.getAnnotations();
            List<AnnotationExpr> newAnno = newCon.getAnnotations();
            
            list.addAll(GetMembersChanges.getMemberListChanges(oldAnno, newAnno));
            
            // Check body
            BlockStmt oldBody = oldCon.getBlock();
            BlockStmt newBody = newCon.getBlock();

            list.addAll(GetMembersChanges.getSingleMemberChanges(oldBody, newBody));
        
            // Check if any changes occurred to the method
            if(list.size() > 0) {
                list.add(FileChangeFactory.getFileChange(newCon, getMemberHeader(newCon), FileChange.TYPE_MOD));
            }
        }
        
        return list;
    }

    @Override
    public String getMemberHeader(Node node) {
        if(node instanceof ConstructorDeclaration) {
            ConstructorDeclaration method = (ConstructorDeclaration) node;
            
            // Use the ')' character to determine the end of the method header
            String methodString = method.toString();
            int index = methodString.indexOf(')') + 1;
            return methodString.substring(0, index);
        }
        
        throw new RuntimeException("Not correct Node type.");
    }

}
