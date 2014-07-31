/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.codechecker.strategy.check;

import japa.parser.ast.Node;
import japa.parser.ast.body.Parameter;

/**
 *
 * @author Heinrich
 */
public class CheckParameters implements CheckStrategy {

    @Override
    public boolean doCheck(Node oldNode, Node newNode) {
        if(oldNode == null || newNode == null)
            return false;
        
        Parameter oldParam = (Parameter) oldNode;
        Parameter newParam = (Parameter) newNode;
        
        if(newParam.getId().toString().equals(oldParam.getId().toString())) {
            if(newParam.getModifiers() == oldParam.getModifiers() && newParam.getType().toString().equals(oldParam.getType().toString()))
                return true;
        }
        
        return false;
    }

    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof Parameter)
            return true;
        
        return false;   
    }
}
