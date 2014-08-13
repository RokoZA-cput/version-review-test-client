/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.childmembers;

import japa.parser.ast.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class AnnotationChildrenService implements MemberChildrenService {

    @Override
    public List<Node> getChildMembers(Node node) {
        
        // No children ATM
        
        List<Node> memberNodes = new ArrayList<>();
        /*// Check if correct type of node
        if(isInstanceOf(node)) {
            AnnotationExpr body = (AnnotationExpr) node;
            
            // Get all children of the node & add to List
        }*/
        
        return memberNodes;
    }
    
}
