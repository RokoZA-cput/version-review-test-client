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
public class AnnotationChanges implements ChangesStrategy {

    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof AnnotationExpr)
            return true;
        
        return false;
    }

    @Override
    public List<FileChange> doCheck(Node oldNode, Node newNode) {
        List<FileChange> list = null;
        
        if(!isInstanceOf(oldNode) || !isInstanceOf(newNode))
            return list;
        
        CodeChecker checker;
        list = new ArrayList<>();
        // Get Method Changes
        // Set strategy
        checker = new CodeChecker(StrategyChooser.getCheckStrategy(newNode));
        
        AnnotationExpr oldAnn = (AnnotationExpr) oldNode;
        AnnotationExpr newAnn = (AnnotationExpr) newNode;
        
        if(!checker.doCheck(oldAnn, newAnn)) {
            list.add(FileChangeFactory.getFileChange(newAnn, getMemberHeader(newAnn), FileChange.TYPE_MOD));
        }
        
        return list;
    }

    @Override
    public String getMemberHeader(Node node) {
        if(node instanceof AnnotationExpr) {
            AnnotationExpr annotation = (AnnotationExpr) node;

            return annotation.getName().getName();
        }
        
        throw new RuntimeException("Not correct Node type.");
    }
    
}
