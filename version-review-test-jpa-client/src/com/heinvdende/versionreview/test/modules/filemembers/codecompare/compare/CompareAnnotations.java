/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.compare;

import japa.parser.ast.Node;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.expr.AnnotationExpr;

/**
 *
 * @author Heinrich
 */
public class CompareAnnotations implements CompareStrategy {

    @Override
    public boolean doCheck(Node oldNode, Node newNode) {
        if(oldNode == null || newNode == null)
            return false;
        
        if(!isInstanceOf(oldNode) || !(isInstanceOf(newNode)))
            return false;
        
        AnnotationExpr oldAnn = (AnnotationExpr) oldNode;
        AnnotationExpr newAnn = (AnnotationExpr) newNode;
        
        if(newAnn.getName().getName().equals(oldAnn.getName().getName())) {
            return true;
        }
        
        return false;
    }

    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof AnnotationExpr)
            return true;
        
        return false;
    }
    
}
