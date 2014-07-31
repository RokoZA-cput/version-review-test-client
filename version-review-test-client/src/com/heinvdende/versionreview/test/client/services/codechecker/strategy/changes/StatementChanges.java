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
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.stmt.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class StatementChanges implements ChangesStrategy {

    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof Statement)
            return true;
        
        return false;
    }

    @Override
    public List<FileChange> doCheck(Node oldNode, Node newNode) {
        List<FileChange> list = null;
        
        if(!isInstanceOf(oldNode) || !isInstanceOf(newNode))
            return list;
        
        CodeChecker checker;
        
        // Get Method Changes
        // Set strategy
        checker = new CodeChecker(StrategyChooser.getCheckStrategy(newNode));
        
        Statement oldStmt = (Statement) oldNode;
        Statement newStmt = (Statement) newNode;
        
        if(checker.doCheck(oldStmt, newStmt)) {
            list = new ArrayList<>();
            //list.add(FileChangeFactory.getFileChange(newStmt, getMemberHeader(newStmt), FileChange.TYPE_MOD));
        }
        
        return list;
    }

    @Override
    public String getMemberHeader(Node node) {
        if(node instanceof Statement) {
            Statement stmt = (Statement) node;
            return stmt.toString();
        }
        
        throw new RuntimeException("Not correct Node type.");
    }
    
}
