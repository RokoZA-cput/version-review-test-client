/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.childmembers;

import japa.parser.ast.Node;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.stmt.BlockStmt;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class BodyChildrenService implements MemberChildrenService {

    @Override
    public List<Node> getChildMembers(Node node) {
        // Check if correct type of node
        List<Node> memberNodes = new ArrayList<>();
        BlockStmt body = (BlockStmt) node;

        // Get all children of the node & add to List
        if(body.getStmts() != null)
            memberNodes.addAll(body.getStmts());
        
        return memberNodes;
    }
    
}
