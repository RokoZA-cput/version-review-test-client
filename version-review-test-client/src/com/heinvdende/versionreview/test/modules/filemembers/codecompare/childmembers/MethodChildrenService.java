/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.childmembers;

import japa.parser.ast.Node;
import japa.parser.ast.body.MethodDeclaration;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class MethodChildrenService implements MemberChildrenService {

    @Override
    public List<Node> getChildMembers(Node node) {
        // Check if correct type of node
        List<Node> memberNodes = new ArrayList<>();
        MethodDeclaration method = (MethodDeclaration) node;

        // Get all children of the node & add to List
        if(method.getAnnotations() != null)
            memberNodes.addAll(method.getAnnotations());
        
        if(method.getParameters() != null)
            memberNodes.addAll(method.getParameters());

        if(method.getBody() != null)
            memberNodes.add(method.getBody());

        return memberNodes;
    }
    
}
