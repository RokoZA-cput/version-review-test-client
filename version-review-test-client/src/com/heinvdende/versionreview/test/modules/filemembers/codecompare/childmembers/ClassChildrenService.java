/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.childmembers;

import japa.parser.ast.Node;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class ClassChildrenService implements MemberChildrenService {

    @Override
    public List<Node> getChildMembers(Node node) {
        // Check if correct type of node
        List<Node> memberNodes = new ArrayList<>();
        ClassOrInterfaceDeclaration myClass = (ClassOrInterfaceDeclaration) node;

        // Get all children of the node & add to List
        if(myClass.getMembers() != null)
            memberNodes.addAll(myClass.getMembers());
        
        return memberNodes;
    }
    
}
