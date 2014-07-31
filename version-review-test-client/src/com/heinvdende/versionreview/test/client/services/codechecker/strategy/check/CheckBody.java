/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.codechecker.strategy.check;

import japa.parser.ast.Node;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.stmt.BlockStmt;

/**
 *
 * @author Heinrich
 */
public class CheckBody implements CheckStrategy {

    @Override
    public boolean doCheck(Node oldNode, Node newNode) {
        if(oldNode == null || newNode == null)
            return false;
        
        BlockStmt oldBody = (BlockStmt) oldNode;
        BlockStmt newBody = (BlockStmt) newNode;
        
        String oldBodyStr = oldBody.toString().replaceAll("\\s", "");
        String newBodyStr = newBody.toString().replaceAll("\\s", "");

        int s = oldBodyStr.length();
        int d = newBodyStr.length();
        
        if(oldBodyStr.equals(newBodyStr)) {
            return true;
        }
        
        return false;
        
    }

    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof BlockStmt)
            return true;
        
        return false;   
    }

}
