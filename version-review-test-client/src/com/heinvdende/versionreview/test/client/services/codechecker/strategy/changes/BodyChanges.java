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
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class BodyChanges implements ChangesStrategy {


    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof BlockStmt)
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
        
        BlockStmt oldBody = (BlockStmt) oldNode;
        BlockStmt newBody = (BlockStmt) newNode;
        
        if(!checker.doCheck(oldBody, newBody)) {
            // Check each statement of the bodies
            List<Statement> oldStmts = oldBody.getStmts();
            List<Statement> newStmts = newBody.getStmts();

            list.addAll(GetMembersChanges.getMemberListChanges(oldStmts, newStmts));
            
            if(list.size() > 0)
                list.add(FileChangeFactory.getFileChange(newBody, getMemberHeader(newBody), FileChange.TYPE_MOD));
        }
        
        return list;
    }

    @Override
    public String getMemberHeader(Node node) {
        if(node instanceof BlockStmt) {
            //BlockStmt body = (BlockStmt) node;
            //return body.toString();
            
            // Just return empty to avoid large strings in database
            return "body statement";
        }
        
        throw new RuntimeException("Not correct Node type.");
    }

}
