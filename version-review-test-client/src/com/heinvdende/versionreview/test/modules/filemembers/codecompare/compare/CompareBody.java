/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.compare;

import japa.parser.ast.Node;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.stmt.BlockStmt;

/**
 *
 * @author Heinrich
 */
public class CompareBody implements CompareStrategy {

    @Override
    public boolean doCheck(Node oldNode, Node newNode) {
        if(oldNode == null || newNode == null)
            return false;
        
        return true;
        
        // Should not check body, because the body string covers all the statements
        // which some of may not have changed
        
        /*BlockStmt oldBody = (BlockStmt) oldNode;
        BlockStmt newBody = (BlockStmt) newNode;
        
        String oldBodyStr = oldBody.toString().replaceAll("\\s", "");
        String newBodyStr = newBody.toString().replaceAll("\\s", "");

        char[] oldChars = oldBodyStr.toCharArray();
        char[] newChars = newBodyStr.toCharArray();
        
        if(oldChars.length == newChars.length) {
            for(int i=0;i<oldChars.length;i++) {
                if(oldChars[i] != newChars[i])
                    return false;
            }
        }
        else
            return false;

        return true;*/
    }

    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof BlockStmt)
            return true;
        
        return false;   
    }

}
