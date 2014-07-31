/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.codechecker.strategy.check;

import japa.parser.ast.Node;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.stmt.BlockStmt;

/**
 *
 * @author Heinrich
 */
public class CheckClass implements CheckStrategy {
    
    @Override
    public boolean doCheck(Node oldNode, Node newNode) {
        if(oldNode == null || newNode == null)
            return false;
        
        ClassOrInterfaceDeclaration oldClass = (ClassOrInterfaceDeclaration) oldNode;
        ClassOrInterfaceDeclaration newClass = (ClassOrInterfaceDeclaration) newNode;

        if(oldClass.getName().equals(newClass.getName()) && oldClass.getModifiers() == newClass.getModifiers())
            return true;
        
        return false;
        
    }

    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof ClassOrInterfaceDeclaration)
            return true;
        
        return false;   
    }

}
