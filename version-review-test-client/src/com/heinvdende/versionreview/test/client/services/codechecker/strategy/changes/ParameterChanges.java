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
import japa.parser.ast.body.Parameter;
import japa.parser.ast.expr.AnnotationExpr;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class ParameterChanges implements ChangesStrategy {

    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof Parameter)
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
        
        Parameter oldParam = (Parameter) oldNode;
        Parameter newParam = (Parameter) newNode;
        
        if(checker.doCheck(oldParam, newParam)) {
            // Just indicate that the annotation has been found
            list = new ArrayList<>();
            
            // Check annotations
            List<AnnotationExpr> oldAnno = oldParam.getAnnotations();
            List<AnnotationExpr> newAnno = newParam.getAnnotations();
            
            list.addAll(GetMembersChanges.getMemberListChanges(oldAnno, newAnno));
        }
        
        return list;
    }

    @Override
    public String getMemberHeader(Node node) {
        if(node instanceof Parameter) {
            Parameter param = (Parameter) node;

            return param.toString();
        }
        
        throw new RuntimeException("Not correct Node type.");
    }
}
