/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.compare;

import japa.parser.ast.Node;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.MethodDeclaration;

/**
 *
 * @author Heinrich
 */
public class CompareMethods implements CompareStrategy {

    @Override
    public boolean doCheck(Node oldNode, Node newNode) {
        if(oldNode == null || newNode == null)
            return false;
        
        MethodDeclaration oldMethod = (MethodDeclaration) oldNode;
        MethodDeclaration newMethod = (MethodDeclaration) newNode;
        
        if(newMethod.getName().equals(oldMethod.getName())) {
            if(newMethod.getType().toString().equals(oldMethod.getType().toString()) && newMethod.getModifiers() == oldMethod.getModifiers())
                return true;
        }
        
        return false;
    }

    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof MethodDeclaration)
            return true;
        
        return false;   
    }

}
