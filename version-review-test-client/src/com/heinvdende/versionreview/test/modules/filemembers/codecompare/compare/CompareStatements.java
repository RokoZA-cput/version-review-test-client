/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.compare;

import japa.parser.ast.Node;
import japa.parser.ast.stmt.Statement;

/**
 *
 * @author Heinrich
 */
public class CompareStatements implements CompareStrategy {

    @Override
    public boolean doCheck(Node oldNode, Node newNode) {
        if(oldNode == null || newNode == null)
            return false;
        
        Statement oldStmt = (Statement) oldNode;
        Statement newStmt = (Statement) newNode;

        String oldString = oldStmt.toString().replaceAll("\\s", "");
        String newString = newStmt.toString().replaceAll("\\s", "");

        int s = oldString.length();
        int d = newString.length();
        
        if(oldString.replaceAll("\\s", "").equals(newString.replaceAll("\\s", ""))) {
            return true;
        }
        
        return false;
    }

    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof Statement)
            return true;
        
        return false;   
    }
}
