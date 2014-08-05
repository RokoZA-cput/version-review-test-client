/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.compare;

import japa.parser.ast.Node;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.stmt.BlockStmt;

/**
 *
 * @author Heinrich
 */
public class CompareConstructor implements CompareStrategy {
    
    @Override
    public boolean doCheck(Node oldNode, Node newNode) {
        if(oldNode == null || newNode == null)
            return false;
        
        ConstructorDeclaration oldCon = (ConstructorDeclaration) oldNode;
        ConstructorDeclaration newCon = (ConstructorDeclaration) newNode;
        
        if(oldCon.getName().equals(newCon.getName()) && oldCon.getModifiers() == newCon.getModifiers())
            return true;
        
        return false;
        
    }

    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof ConstructorDeclaration)
            return true;
        
        return false;   
    }

}
